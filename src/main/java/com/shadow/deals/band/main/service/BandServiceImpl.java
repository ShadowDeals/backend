package com.shadow.deals.band.main.service;

import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.band.main.dto.response.BandInfoResponseDTO;
import com.shadow.deals.band.main.dto.response.BandStatsInfoResponseDTO;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.main.mapper.BandMapper;
import com.shadow.deals.band.main.repository.BandRepository;
import com.shadow.deals.band.task.band.service.BandTaskService;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.region.entity.Region;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.region.service.RegionService;
import com.shadow.deals.user.main.dto.response.BandWorkerResponseDTO;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.mapper.UserMapper;
import com.shadow.deals.user.main.service.UserService;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.CommonUtils;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
public class BandServiceImpl implements BandService {

    private final BandRepository bandRepository;

    private final RegionService regionService;

    private final UserService userService;

    private final BlockedBandService blockedBandService;

    private final BandTaskService bandTaskService;

    private final Counter bandStatsCounter;

    public BandServiceImpl(
        BandRepository bandRepository, RegionService regionService, UserService userService, BlockedBandService blockedBandService,
        BandTaskService bandTaskService, MeterRegistry meterRegistry
    ) {
        this.bandRepository = bandRepository;
        this.regionService = regionService;
        this.userService = userService;
        this.blockedBandService = blockedBandService;
        this.bandTaskService = bandTaskService;
        this.bandStatsCounter = meterRegistry.counter("select_band_stats_requests");
    }

    @Override
    public Band save(Band entity) {
        return bandRepository.save(entity);
    }

    @Override
    public Band findById(UUID id) {
        return bandRepository.findById(id).orElseThrow(() -> new APIException(
            "Банды с id = %s не найдено".formatted(id),
            HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Band> findAll() {
        return bandRepository.findAll();
    }

    @Override
    public Band findByRegion(RegionName regionName) {
        Region regionEntity = regionService.findByRegionName(regionName);
        return bandRepository.findByRegion(regionEntity).orElseThrow(() -> new APIException(
            "Банды с регионом = %s не найдено".formatted(regionName.getTitle()),
            HttpStatus.NOT_FOUND));
    }

    @Override
    public TreeSet<BandWorkerResponseDTO> getWorkersByType(UUID bandId, UserRoleName userRole) {
        if (blockedBandService.existsByBandId(bandId)) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        return userService.findAllByBand(findById(bandId))
            .stream()
            .filter(worker -> worker.getRole().getRoleName() == userRole)
            .map(UserMapper.INSTANCE::toBandWorkerResponseDTO)
            .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public void kickFromBand(UUID userId, HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        Band userBand;
        UserRoleName userRoleName = user.getRole().getRoleName();
        if (userRoleName == UserRoleName.DON) {
            userBand = user.getOwnBand();
        } else {
            userBand = user.getBand();
        }

        if (userBand == null) {
            throw new APIException("Банды не совпадают!", HttpStatus.BAD_REQUEST);
        }

        if (blockedBandService.existsByBandId(userBand.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        User userToKick = userService.findById(userId);
        Band userToKickBand = userToKick.getBand();
        if (userToKickBand == null) {
            throw new APIException("Банды не совпадают!", HttpStatus.BAD_REQUEST);
        }

        if (!userBand.getId().equals(userToKickBand.getId())) {
            throw new APIException("Банды не совпадают!", HttpStatus.BAD_REQUEST);
        }

        UserRoleName userToKickRoleName = userToKick.getRole().getRoleName();
        if (userRoleName == UserRoleName.ADMIN && userToKickRoleName != UserRoleName.SOLDIER
            || userRoleName == UserRoleName.DON && userToKickRoleName != UserRoleName.ADMIN) {
            throw new APIException("Вы не можете исключить данного человека!", HttpStatus.BAD_REQUEST);
        }

        userToKick.setBand(null);
        userService.update(userToKick);
    }

    @Override
    public boolean existsByRegion(Region region) {
        return bandRepository.existsByRegion(region);
    }

    @Override
    public BandInfoResponseDTO selectBandInfo(UUID bandId) {
        Band band = findById(bandId);
        if (blockedBandService.existsByBandId(bandId)) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        BandInfoResponseDTO bandInfoResponseDTO = BandMapper.INSTANCE.toResponseDTO(band);
        bandInfoResponseDTO.setDonName(userService.getUserName(band.getDon()));
        bandInfoResponseDTO.setWorkers(
            findBandWorkers(bandId)
                .stream()
                .map(UserMapper.INSTANCE::toBandWorkerResponseDTO)
                .toList()
        );
        bandInfoResponseDTO.setDonId(userService.findDonByBandId(bandId));
        return bandInfoResponseDTO;
    }

    @Override
    public BandStatsInfoResponseDTO selectBandStatsInfo(HttpRequest<?> request) {
        bandStatsCounter.increment();

        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);
        Band band = user.getOwnBand();

        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        List<User> workers = userService.findAllByBand(band);
        long adminsCount = workers.stream()
            .filter(worker -> worker.getRole().getRoleName() == UserRoleName.ADMIN)
            .count();

        long soldiersCount = workers.stream()
            .filter(worker -> worker.getRole().getRoleName() == UserRoleName.SOLDIER)
            .count();

        List<Task> tasks = bandTaskService.findAllTasksByBand(band.getId());
        Set<Task> completedTasks = tasks.stream()
            .filter(task -> task.getStatus().getTaskStatus() == TaskStatusEnum.FINISHED)
            .collect(Collectors.toSet());

        int totalTasksPrice = completedTasks
            .stream()
            .map(Task::getPrice)
            .reduce(0, Integer::sum);

        return new BandStatsInfoResponseDTO(adminsCount, soldiersCount, tasks.size(), completedTasks.size(), totalTasksPrice);
    }

    @Override
    public List<User> findBandWorkers(UUID bandId) {
        return userService.findAllByBand(findById(bandId));
    }
}
