package com.shadow.deals.band.request.service;

import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.main.service.BandService;
import com.shadow.deals.band.request.dto.response.OwnRequestResponseDTO;
import com.shadow.deals.band.request.dto.response.RequestResponseDTO;
import com.shadow.deals.band.request.entity.Request;
import com.shadow.deals.band.request.mapper.RequestMapper;
import com.shadow.deals.band.request.repository.RequestRepository;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.service.UserServiceImpl;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.CommonUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    private final UserServiceImpl userService;

    private final BandService bandService;

    private final BlockedBandService blockedBandService;

    @Override
    public Request findById(UUID id) {
        return requestRepository.findById(id).orElseThrow(() -> new APIException(
                "Заявки с id = %s не найдено".formatted(id),
                HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public Request save(Request entity) {
        return requestRepository.save(entity);
    }

    @Override
    public void deleteRequest(UUID id) {
        requestRepository.deleteById(id);
    }

    @Override
    public void acceptRequest(UUID id) {
        Request bandRequest = findById(id);
        Band band = bandRequest.getBand();
        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        User user = bandRequest.getUser();
        requestRepository.deleteByUser(user);

        user.setBand(band);
        userService.update(user);
    }

    @Override
    public void createRequests(HttpRequest<?> request, RegionName region) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);
        createRequests(user, region);
    }

    @Override
    public void createRequests(User user, RegionName region) {
        if (region == null) {
            bandService.findAll().forEach(band -> {
                if (!requestRepository.existsByBandAndUser(band, user)) {
                    createRequest(user, band);
                }
            });
            return;
        }

        Band band = bandService.findByRegion(region);
        if (!requestRepository.existsByBandAndUser(band, user)) {
            createRequest(user, band);
        }
    }

    @Override
    public TreeSet<RequestResponseDTO> getRequests(HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        Band band;
        UserRoleName targetRole;
        if (user.getRole().getRoleName() == UserRoleName.DON) {
            band = user.getOwnBand();
            targetRole = UserRoleName.ADMIN;
        } else {
            band = user.getBand();
            targetRole = UserRoleName.SOLDIER;
        }

        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        return requestRepository.findByBand(band)
                .stream()
                .filter(req -> req.getUser().getRole().getRoleName() == targetRole)
                .map(r -> RequestMapper.INSTANCE.toResponseDTO(r, userService.getUserName(r.getUser())))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public TreeSet<OwnRequestResponseDTO> getOwnRequests(HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        return findAll().stream()
                .filter(req -> user.getId().equals(req.getUser().getId()))
                .map(RequestMapper.INSTANCE::toOwnResponseDTO)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private void createRequest(User user, Band band) {
        Request request = new Request();
        request.setDateCreated(Instant.now());
        request.setUser(user);
        request.setBand(band);
        requestRepository.save(request);
    }
}
