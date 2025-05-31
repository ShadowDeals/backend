package com.shadow.deals.user.refresh.service;

import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.refresh.entity.RefreshToken;
import com.shadow.deals.user.refresh.repository.RefreshTokenRepository;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.generator.RefreshTokenGenerator;
import io.micronaut.security.token.validator.RefreshTokenValidator;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * This class contains methods that perform business logic related to the {@link RefreshToken} entity.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    /**
     * Instance of the repository interface that contains methods for interacting with table associated with
     * {@link RefreshToken} entity.
     */
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * An instance of the interface that provides methods for refresh token generating.
     */
    private final RefreshTokenGenerator refreshTokenGenerator;

    /**
     * An instance of the interface that provides methods for refresh token validating.
     */
    private final RefreshTokenValidator refreshTokenValidator;

    /**
     * This method allows to find an {@link RefreshToken} entity by its Id.
     *
     * @param id entity's {@link UUID} Id.
     * @return The {@link RefreshToken} entity corresponding to the given Id.
     * @throws APIException in case the requested instance of the entity does not exist. It throws with the
     *                      {@link HttpStatus#NOT_FOUND} code.
     */
    @Override
    public RefreshToken findById(UUID id) {
        return refreshTokenRepository.findById(id).orElseThrow(() -> new APIException(
                "Токена обновления с id = %s не существует".formatted(id),
                HttpStatus.NOT_FOUND)
        );
    }

    /**
     * This method allows to get all the elements from the table corresponding to the {@link RefreshToken}
     * entity described by the Generic class.
     *
     * @return All the elements from the table corresponding to the {@link RefreshToken} entity described
     * by the Generic class.
     */
    @Override
    public List<RefreshToken> findAll() {
        return refreshTokenRepository.findAll();
    }

    /**
     * This method allows to save an entity to the appropriate table.
     *
     * @param entity entity to save.
     * @return Saved entity with set ID and other auto generated values.
     */
    @Override
    public RefreshToken save(RefreshToken entity) {
        return refreshTokenRepository.save(entity);
    }

    /**
     * This method allows to delete the refresh token.
     *
     * @param refreshToken the entity of the refresh token that needs to be deleted.
     */
    @Override
    public void removeRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    /**
     * This method allows to create an object of the {@link RefreshToken} class and save it to the database.
     *
     * @param user            the user for whom the token is being created.
     * @param refreshTokenKey the key of the refresh token (UUID).
     */
    @Override
    public void createAndSaveRefreshToken(User user, String refreshTokenKey) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(refreshTokenKey);
        refreshToken.setDateCreated(Instant.now());
        refreshToken.setUser(user);
        save(refreshToken);
    }

    /**
     * This method allows to create a UUID key for the refresh token.
     *
     * @param authentication data of the user for whom the token is being generated.
     * @return A UUID key for the refresh token.
     */
    @Override
    public String createRefreshTokenKey(Authentication authentication) {
        return refreshTokenGenerator.createKey(authentication);
    }

    /**
     * This method allows to create an refresh token.
     *
     * @param authentication  data of the user for whom the token is being generated.
     * @param refreshTokenKey the key of the refresh token (UUID).
     * @return A refresh token.
     * @throws APIException in case the refresh token could not be generated. It throws with the
     *                      {@link HttpStatus#INTERNAL_SERVER_ERROR} code.
     * @see #createRefreshTokenKey(Authentication)
     */
    @Override
    public String generateRefreshToken(Authentication authentication, String refreshTokenKey) throws APIException {
        return refreshTokenGenerator.generate(authentication, refreshTokenKey).orElseThrow(() -> new APIException(
                "Произошла ошибка во время генерации токена обновления",
                HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }

    /**
     * This method allows to validate the refresh token.
     *
     * @param refreshToken the refresh token to validate.
     * @return The refresh token payload.
     * @throws APIException in case the refresh token is invalid. It throws with the
     *                      {@link HttpStatus#BAD_REQUEST} codee.
     */
    @Override
    public String validateRefreshToken(String refreshToken) throws APIException {
        return refreshTokenValidator.validate(refreshToken).orElseThrow(() -> new APIException(
                        "Токен обновления не является валидным",
                        HttpStatus.BAD_REQUEST
                )
        );
    }

    /**
     * This method allows to delete refresh token by user.
     *
     * @param user the user with whom the refresh token is associated.
     */
    @Override
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}