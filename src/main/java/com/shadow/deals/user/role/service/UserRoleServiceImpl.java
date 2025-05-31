package com.shadow.deals.user.role.service;

import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.role.entity.UserRole;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.user.role.repository.UserRoleRepository;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This class contains methods that perform business logic related to the {@link UserRole} entity.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    /**
     * Instance of the repository interface that contains methods for interacting with table associated with
     * {@link UserRole} entity.
     */
    private final UserRoleRepository userRoleRepository;

    /**
     * This method allows to find an {@link UserRole} entity by its Id.
     *
     * @param id entity's {@link UUID} Id.
     * @return The {@link UserRole} entity corresponding to the given Id.
     * @throws APIException in case the requested instance of the entity does not exist. It throws with the
     *                      {@link HttpStatus#NOT_FOUND} code.
     */
    @Override
    public UserRole findById(UUID id) {
        return userRoleRepository.findById(id).orElseThrow(() -> new APIException(
                "Роли с id = %s не существует".formatted(id),
                HttpStatus.NOT_FOUND)
        );
    }

    /**
     * This method allows to get all the elements from the table corresponding to the {@link UserRole}
     * entity described by the Generic class.
     *
     * @return All the elements from the table corresponding to the {@link UserRole} entity described
     * by the Generic class.
     */
    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    /**
     * This method allows to find a role by its name.
     *
     * @param name role name.
     * @return {@link Optional} with {@link UserRole} entity by its name.
     * @throws APIException in case the requested instance of the entity does not exist. It throws with the
     *                      {@link HttpStatus#NOT_FOUND} code.
     */
    @Override
    public UserRole findUserRoleByName(UserRoleName name) throws APIException {
        return userRoleRepository.findByRoleName(name).orElseThrow(() -> new APIException(
                "Роли с именем %s не существует".formatted(name.getTitle()),
                HttpStatus.NOT_FOUND)
        );
    }
}