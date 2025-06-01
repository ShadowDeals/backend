package com.shadow.deals.band.request.repository;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.request.entity.Request;
import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.user.main.entity.User;
import io.micronaut.data.annotation.Repository;

import java.util.List;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface RequestRepository extends CommonRepository<Request> {
    List<Request> findByBand(Band band);

    void deleteByUser(User user);
}
