package com.shadow.deals.band.main.service;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.main.repository.BandRepository;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.region.entity.Region;
import com.shadow.deals.user.region.enums.RegionName;
import com.shadow.deals.user.region.service.RegionService;
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

    private final RegionService regionService;

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

    @Override
    public Band findByRegion(RegionName regionName) {
        Region regionEntity = regionService.findByRegionName(regionName);
        return bandRepository.findByRegion(regionEntity).orElseThrow(() -> new APIException(
                "Банды с регионом = %s не найдено".formatted(regionName.getTitle()),
                HttpStatus.NOT_FOUND));
    }
}
