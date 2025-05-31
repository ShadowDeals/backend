package com.shadow.deals.band.repository;

import com.shadow.deals.band.entity.Band;
import com.shadow.deals.base.repository.CommonRepository;
import io.micronaut.data.annotation.Repository;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface BandRepository extends CommonRepository<Band> {
}
