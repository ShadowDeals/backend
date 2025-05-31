package com.shadow.deals.auth.provider;

import com.shadow.deals.auth.password.PasswordEncoder;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.activation.entity.ActivationCode;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.service.UserService;
import io.micronaut.core.annotation.Blocking;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * This class processes the authentication request.
 *
 * @param <B> the HTTP Request Body type
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class AuthProvider<B> implements HttpRequestAuthenticationProvider<B> {
    /**
     * An instance of the interface that provides the ability to encode a password.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * An instance of the interface that provides the ability to interact with the {@link User} entity.
     */
    private final UserService userService;

    /**
     * This method allows to process an authentication request.
     *
     * @param requestContext the context request (typically an HTTP Request).
     * @param authRequest    the credentials to authenticate.
     * @return If the user does not exist, or he's email not activated, or the credentials are incorrect,
     * {@link AuthenticationResponse#failure(String)} will be returned with the corresponding messages. If everything
     * is fine, {@link AuthenticationResponse#success(String, Collection)} will be returned.
     */
    @Override
    @NonNull
    @Blocking
    public AuthenticationResponse authenticate(@Nullable HttpRequest<B> requestContext,
                                               @NonNull @NotNull AuthenticationRequest<String, String> authRequest) {
        String email = authRequest.getIdentity();
        User user;
        try {
            user = userService.findByEmail(email);
        } catch (APIException e) {
            return AuthenticationResponse.failure(
                    "Пользователя с почтой %s не существует".formatted(email)
            );
        }

        ActivationCode activationCode = user.getActivationCode();
        if (activationCode == null || !activationCode.isActivated()) {
            return AuthenticationResponse.failure(
                    "Попытка аутентификации без подтвержденной почты."
            );
        }

        if (!passwordEncoder.matches(authRequest.getSecret(), user.getPassword())) {
            return AuthenticationResponse.failure(
                    "Данные не совпадают"
            );
        }
        return AuthenticationResponse.success(email, userService.getUserRoles(user));
    }
}