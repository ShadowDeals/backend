package com.shadow.deals.band.blocked.repository;

import com.shadow.deals.band.blocked.entity.BlockedBand;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.base.repository.CommonRepository;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;

import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface BlockedBandRepository extends CommonRepository<BlockedBand> {
    @Query(value = "SELECT COUNT(bb) > 0 FROM BlockedBand bb WHERE bb.band.id = :bandId")
    boolean existsByBandId(UUID bandId);

    void deleteByBand(Band band);
}
