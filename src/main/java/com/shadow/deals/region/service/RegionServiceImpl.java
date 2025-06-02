package com.shadow.deals.region.service;

import com.shadow.deals.exception.APIException;
import com.shadow.deals.region.entity.Region;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.region.repository.RegionRepository;
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

    @Override
    public TreeSet<String> getRegions(boolean isBandExist) {
        return regionRepository.findRegionsByFlag(isBandExist)
                .stream()
                .map(region -> region.getRegionName().getTitle())
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
