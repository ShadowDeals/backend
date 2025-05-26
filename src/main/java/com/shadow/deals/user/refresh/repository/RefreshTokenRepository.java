package com.shadow.deals.user.refresh.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.refresh.entity.RefreshToken;
import io.micronaut.data.annotation.Repository;

/**
 * This repository contains methods for interacting with table associated with entity {@link RefreshToken}.
 *
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface RefreshTokenRepository extends CommonRepository<RefreshToken> {
    /**
     * This signature describes a method that allows to delete refresh token by user.
     *
     * @param user the user with whom the refresh token is associated.
     */
    void deleteByUser(User user);
}