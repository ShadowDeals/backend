package com.shadow.deals.auth.service;

import com.shadow.deals.auth.dto.request.ChangePasswordRequestDTO;
import com.shadow.deals.auth.dto.request.EmailRequestDTO;
import com.shadow.deals.auth.dto.request.RefreshTokenRequestDTO;
import com.shadow.deals.auth.dto.request.SignInRequestDTO;
import com.shadow.deals.auth.dto.request.SignUpRequestDTO;
import com.shadow.deals.auth.dto.request.UpdateEmailRequestDTO;
import com.shadow.deals.auth.dto.response.TokenResponseDTO;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import org.reactivestreams.Publisher;

/**
 * This interface contains the signatures of the methods that must be implemented by the corresponding service class.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface AuthService {
    /**
     * This signature describes the method, that allows to register and add a new user to the database.
     *
     * @param signUpRequestDTO data for registering a new user.
     * @return The user's email.
     */
    String signup(SignUpRequestDTO signUpRequestDTO);

    /**
     * This signature describes a method that allows user authentication.
     *
     * @param signInRequestDTO authentication data.
     * @param request          the request corresponding to authentication.
     * @return {@link Publisher}, containing the authentication result - successful or with an error.
     */
    Publisher<MutableHttpResponse<?>> signin(SignInRequestDTO signInRequestDTO, HttpRequest<?> request);

    /**
     * This signature describes a method that allows to refresh the user's access token.
     *
     * @param refreshTokenRequestDTO the request body containing the refresh token.
     * @return {@link Publisher}, containing the refresh token result - successful or with an error.
     */
    Publisher<MutableHttpResponse<?>> refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    /**
     * This signature describes the method, that allows to get the lifetime of the access token.
     *
     * @return The lifetime of the access token.
     */
    int getAccessTokenExpiration();

    /**
     * This signature describes a method that allows to verify the user's email.
     *
     * @param activationCode the activation code for email confirmation.
     * @return An object containing information about the user's tokens.
     */
    TokenResponseDTO confirmEmail(String activationCode);

    /**
     * This signature describes a method that allows to change the user's email address and send a message to a new
     * email address to confirm it.
     *
     * @param updateEmailRequestDTO an object containing data for updating the user's mail.
     * @return The user's new email address.
     */
    String changeEmail(UpdateEmailRequestDTO updateEmailRequestDTO);

    /**
     * This signature describes a method that allows to send an email to the user to change the password.
     *
     * @param emailRequestDTO an object containing the user's email address to which need to send
     *                        an email to change the password.
     */
    void sendChangePasswordEmail(EmailRequestDTO emailRequestDTO);

    /**
     * This signature describes a method that allows to set a new password for the user.
     *
     * @param changePasswordRequestDTO an object containing data for updating the user's password.
     */
    void changePassword(ChangePasswordRequestDTO changePasswordRequestDTO);

    /**
     * This signature describes a method that allows to logout user from the system.
     *
     * @param request the request corresponding to logout.
     */
    void logout(HttpRequest<?> request);
}