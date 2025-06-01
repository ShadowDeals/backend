package com.shadow.deals.band.main.repository;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.user.region.entity.Region;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface BandRepository extends CommonRepository<Band> {
    Optional<Band> findByRegion(Region region);
}
