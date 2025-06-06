package com.shadow.deals.band.main.service;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.main.repository.BandRepository;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.region.entity.Region;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.region.service.RegionService;
import com.shadow.deals.user.main.dto.response.BandWorkerResponseDTO;
import com.shadow.deals.user.main.mapper.UserMapper;
import com.shadow.deals.user.role.enums.UserRoleName;
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
        Band band = findById(bandId);

        return band.getWorkers()
                .stream()
                .filter(worker -> worker.getRole().getRoleName() == userRole)
                .map(UserMapper.INSTANCE::toBandWorkerResponseDTO)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public boolean existsByRegion(Region region) {
        return bandRepository.existsByRegion(region);
    }
}
