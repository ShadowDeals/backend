package com.shadow.deals.user.main.service;

import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.user.main.entity.User;

import java.util.Map;
import java.util.UUID;

/**
 * This interface contains the signatures of the methods that must be implemented by the corresponding service class.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface UserService extends CommonEntityService<User> {
    /**
     * This signature describes a method that allows to find an entity by the email value.
     *
     * @param email user's email.
     * @return The entity corresponding to the given email.
     */
    User findByEmail(String email);

    /**
     * This signature describes a method that allows to find an entity by the refresh token UUID-key value.
     *
     * @param refreshTokenKey refresh token UUID-key.
     * @return The entity corresponding to the given refresh token UUID-key.
     */
    User findByRefreshTokenKey(String refreshTokenKey);

    /**
     * This signature describes a method that allows to get set of user role names.
     *
     * @param user a user who needs to be assigned set of roles.
     * @return Set of user role names.
     */
    String getUserRole(User user);

    /**
     * This signature describes a method that allows to determine whether an entity exists with the given email.
     *
     * @param email user's email.
     * @return If user with given email exists - returns {@code true}, otherwise - {@code false}.
     */
    boolean existsByEmail(String email);

    Map<String, Object> getUserClaims(User user);

    String getUserName(User user);

    void deleteById(UUID id);
}
