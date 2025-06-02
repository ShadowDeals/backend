package com.shadow.deals.region.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.region.entity.Region;
import com.shadow.deals.region.enums.RegionName;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface RegionRepository extends CommonRepository<Region> {
    Optional<Region> findByRegionName(RegionName regionName);

    @Query(
            value = """
                    SELECT r.* FROM sd_region r
                    WHERE r.id IN (SELECT b.region_id FROM sd_band b) = :isBandExist
                    """,
            nativeQuery = true
    )
    List<Region> findRegionsByFlag(boolean isBandExist);
}
