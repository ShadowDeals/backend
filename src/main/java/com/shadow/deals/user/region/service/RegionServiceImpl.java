package com.shadow.deals.user.region.service;

import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.region.entity.Region;
import com.shadow.deals.user.region.enums.RegionName;
import com.shadow.deals.user.region.repository.RegionRepository;
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
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Override
    public Region findById(UUID id) {
        return regionRepository.findById(id).orElseThrow(() -> new APIException(
                "Региона с id = %s не существует".formatted(id),
                HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region findByRegionName(RegionName regionName) {
        return regionRepository.findByRegionName(regionName).orElseThrow(() -> new APIException(
                "Региона с именем = %s не существует".formatted(regionName.getTitle()),
                HttpStatus.NOT_FOUND)
        );
    }
}
