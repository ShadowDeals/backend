package com.shadow.deals.auth.persistence;

import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.service.UserService;
import com.shadow.deals.user.refresh.entity.RefreshToken;
import com.shadow.deals.user.refresh.service.RefreshTokenService;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;

import java.util.Set;

/**
 * The class is responsible for saving the refresh token and getting information about the user using the refresh token.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class RefreshTokenPersistenceImpl implements RefreshTokenPersistence {
//    /**
//     * The lifetime of the refresh token.
//     */
//    @Value("${shadow-deals.auth.refresh-token.expiration}")
//    private int refreshTokenExpiresIn;

    /**
     * An instance of the interface that provides the ability to interact with the {@link RefreshToken} entity.
     */
    private final RefreshTokenService refreshTokenService;

    /**
     * An instance of the interface that provides the ability to interact with the {@link User} entity.
     */
    private final UserService userService;

    /**
     * This method is called when creating an refresh token. The token is saved in it.
     *
     * @param event the refresh token generated event.
     */
    @Override
    public void persistToken(@NotNull RefreshTokenGeneratedEvent event) {
        User user = userService.findByEmail(event.getAuthentication().getName());
        if (user == null) {
            return;
        }

        RefreshToken refreshToken = user.getRefreshToken();
        if (refreshToken != null) {
            return;
        }
//        if (refreshToken != null &&
//                refreshToken.getDateCreated().getEpochSecond() + refreshTokenExpiresIn > Instant.now().getEpochSecond()) {
//            return;
//        }
//        if (refreshToken != null) {
//            refreshTokenService.removeRefreshToken(refreshToken);
//        }
        refreshTokenService.createAndSaveRefreshToken(user, event.getRefreshToken());
    }

    /**
     * This method is called when the access token is updated.
     *
     * @param refreshTokenKey the refresh token UUID-key.
     * @return The user details associated with the refresh token.
     * @throws APIException in case the refresh token with the requested key does not exist. It throws with the
     *                      {@link HttpStatus#BAD_REQUEST} code.
     */
    @Override
    public Publisher<Authentication> getAuthentication(String refreshTokenKey) {
        return Flowable.create(emitter -> {
            User user = userService.findByRefreshTokenKey(refreshTokenKey);
            if (user == null) {
                emitter.onError(new APIException("Пользователь не найден.", HttpStatus.BAD_REQUEST));
            } else {
                Authentication authentication = Authentication.build(user.getEmail(), Set.of(userService.getUserRole(user)));
                emitter.onNext(authentication);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }
}
