package com.shadow.deals.user.region.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.user.region.entity.Region;
import com.shadow.deals.user.region.enums.RegionName;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface RegionRepository extends CommonRepository<Region> {
    Optional<Region> findByRegionName(RegionName regionName);
}
