package com.shadow.deals.user.role.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.user.role.entity.UserRole;
import com.shadow.deals.user.role.enums.UserRoleName;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;

/**
 * This repository contains methods for interacting with table associated with entity {@link UserRole}.
 *
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface UserRoleRepository extends CommonRepository<UserRole> {
    /**
     * This method allows to find a role by its name.
     *
     * @param name role name.
     * @return {@link Optional} with {@link UserRole} entity by its name.
     */
    Optional<UserRole> findByRoleName(UserRoleName name);
}