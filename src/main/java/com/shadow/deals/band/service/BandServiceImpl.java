package com.shadow.deals.band.service;

import com.shadow.deals.band.entity.Band;
import com.shadow.deals.band.repository.BandRepository;
import com.shadow.deals.exception.APIException;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepository;

    @Override
    public Band save(Band entity) {
        return bandRepository.save(entity);
    }

    @Override
    public Band findById(UUID id) {
        return bandRepository.findById(id).orElseThrow(() -> new APIException(
                "Пользователя с id = %s не найдено".formatted(id),
                HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Band> findAll() {
        return bandRepository.findAll();
    }
}
