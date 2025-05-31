package com.shadow.deals.user.refresh.service;

import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.refresh.entity.RefreshToken;
import io.micronaut.security.authentication.Authentication;

/**
 * This interface contains the signatures of the methods that must be implemented by the corresponding service class.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface RefreshTokenService extends CommonEntityService<RefreshToken> {
    /**
     * This signature describes the method, that allows to delete the refresh token.
     *
     * @param refreshToken the entity of the refresh token that needs to be deleted.
     */
    void removeRefreshToken(RefreshToken refreshToken);

    /**
     * This signature describes the method, that allows to create an object of the {@link RefreshToken} class and save
     * it to the database.
     *
     * @param user            the user for whom the token is being created.
     * @param refreshTokenKey the key of the refresh token (UUID).
     */
    void createAndSaveRefreshToken(User user, String refreshTokenKey);

    /**
     * This signature describes the method, that allows to create a UUID key for the refresh token.
     *
     * @param authentication data of the user for whom the token is being generated.
     * @return A UUID key for the refresh token.
     */
    String createRefreshTokenKey(Authentication authentication);

    /**
     * This signature describes the method, that allows to create an refresh token.
     *
     * @param authentication  data of the user for whom the token is being generated.
     * @param refreshTokenKey the key of the refresh token (UUID).
     * @return A refresh token.
     * @see #createRefreshTokenKey(Authentication)
     */
    String generateRefreshToken(Authentication authentication, String refreshTokenKey);

    /**
     * This signature describes a method that allows to validate the refresh token.
     *
     * @param refreshToken the refresh token to validate.
     * @return The refresh token payload.
     */
    String validateRefreshToken(String refreshToken);

    /**
     * This signature describes a method that allows to delete refresh token by user.
     *
     * @param user the user with whom the refresh token is associated.
     */
    void deleteByUser(User user);
}