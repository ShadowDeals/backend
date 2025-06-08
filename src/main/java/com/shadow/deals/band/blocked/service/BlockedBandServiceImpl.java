package com.shadow.deals.band.blocked.service;

import com.shadow.deals.auth.password.PasswordEncoder;
import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.blocked.entity.BlockedBand;
import com.shadow.deals.band.blocked.repository.BlockedBandRepository;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.service.UserService;
import com.shadow.deals.util.CommonUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class BlockedBandServiceImpl implements BlockedBandService {
    private final BlockedBandRepository blockedBandRepository;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public BlockedBand findById(UUID id) {
        return blockedBandRepository.findById(id).orElseThrow(() -> new APIException(
                "Заблокированной банды с id = %s не найдено".formatted(id),
                HttpStatus.NOT_FOUND));
    }

    @Override
    public List<BlockedBand> findAll() {
        return blockedBandRepository.findAll();
    }

    @Override
    public BlockedBand save(BlockedBand entity) {
        return blockedBandRepository.save(entity);
    }

    @Override
    public boolean existsByBandId(UUID bandId) {
        return blockedBandRepository.existsByBandId(bandId);
    }

    @Override
    public void blockDb(BlockBandRequestDTO blockBandRequestDTO, HttpRequest<?> request) {
        Band band = check(blockBandRequestDTO, request);

        BlockedBand blockedBand = new BlockedBand();
        blockedBand.setBand(band);
        save(blockedBand);
    }

    @Override
    public void unblockDb(BlockBandRequestDTO blockBandRequestDTO, HttpRequest<?> request) {
        Band band = check(blockBandRequestDTO, request);

        blockedBandRepository.deleteByBand(band);
    }

    private @NotNull Band check(BlockBandRequestDTO blockBandRequestDTO, HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        Band band = user.getBand();
        if (existsByBandId(band.getId())) {
            throw new APIException("База данных уже заблокирована!", HttpStatus.BAD_REQUEST);
        }

        if (!passwordEncoder.matches(blockBandRequestDTO.getPassword(), user.getPassword())) {
            throw new APIException("Некорректный пароль!", HttpStatus.UNAUTHORIZED);
        }

        return band;
    }
}
