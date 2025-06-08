package com.shadow.deals.band.main.service;

import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.main.repository.BandRepository;
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
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepository;

    private final RegionService regionService;

    private final UserService userService;

    private final BlockedBandService blockedBandService;

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

        Band band = findById(bandId);

        return band.getWorkers()
                .stream()
                .filter(worker -> worker.getRole().getRoleName() == userRole)
                .map(UserMapper.INSTANCE::toBandWorkerResponseDTO)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public void kickFromBand(UUID userId, HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        Band userBand = user.getBand();
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

        UserRoleName userRoleName = user.getRole().getRoleName();
        UserRoleName userToKickRoleName = userToKick.getRole().getRoleName();
        if (userRoleName == UserRoleName.ADMIN && userToKickRoleName != UserRoleName.SOLDIER || userRoleName == UserRoleName.DON && userToKickRoleName != UserRoleName.ADMIN) {
            throw new APIException("Вы не можете исключить данного человека!", HttpStatus.BAD_REQUEST);
        }

        userToKick.setBand(null);
        userService.update(userToKick);
    }

    @Override
    public boolean existsByRegion(Region region) {
        return bandRepository.existsByRegion(region);
    }
}
