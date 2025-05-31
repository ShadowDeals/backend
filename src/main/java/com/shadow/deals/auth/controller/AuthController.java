package com.shadow.deals.auth.controller;

import com.shadow.deals.auth.dto.request.ChangePasswordRequestDTO;
import com.shadow.deals.auth.dto.request.EmailRequestDTO;
import com.shadow.deals.auth.dto.request.RefreshTokenRequestDTO;
import com.shadow.deals.auth.dto.request.SignInRequestDTO;
import com.shadow.deals.auth.dto.request.SignUpRequestDTO;
import com.shadow.deals.auth.dto.request.UpdateEmailRequestDTO;
import com.shadow.deals.auth.dto.response.TokenResponseDTO;
import com.shadow.deals.auth.service.AuthService;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.role.enums.UserRoleName;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

/**
 * This controller is responsible for processing authentication-related requests.
 *
 * @author Kirill "Tamada" Simovin
 */
@Controller("/auth")
@RequiredArgsConstructor
@ExecuteOn(TaskExecutors.BLOCKING)
public class AuthController {
    /**
     * An instance of an interface that provides logic for processing authentication-related requests.
     */
    private final AuthService authService;

    /**
     * This method allows to process a new user registration request.
     *
     * @param signUpRequestDTO data for registering a new user.
     * @return A response with the {@link HttpStatus#CREATED} status and a body with the user's email.
     * @throws APIException in following cases:
     *                      <li>• User with such an email already exists.</li>
     *                      <li>• The requested country does not exist.</li>
     *                      <li>• The role {@link UserRoleName#USER} does not exist in the database.</li>
     *                      <li>• The refresh token could not be generated.</li>
     *                      <li>• The access token could not be generated.</li>
     */
    @Post("/signup")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public MutableHttpResponse<String> signup(@Valid @Body SignUpRequestDTO signUpRequestDTO) {
        return HttpResponse.status(HttpStatus.CREATED).body(authService.signup(signUpRequestDTO));
    }

    /**
     * This method allows to process login requests.
     *
     * @param signInRequestDTO authentication data.
     * @param request          the request corresponding to authentication.
     * @return {@link Publisher}, containing the authentication result - successful or with an error.
     */
    @Post("/signin")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher<MutableHttpResponse<?>> login(@Valid @Body SignInRequestDTO signInRequestDTO, HttpRequest<?> request) {
        return authService.signin(signInRequestDTO, request);
    }

    /**
     * This method allows to process requests aimed at refreshing the user's token.
     *
     * @param refreshTokenRequestDTO the request body containing the refresh token.
     * @return {@link Publisher}, containing the refresh token result - successful or with an error.
     * @throws APIException in case an invalid refresh token is passed.
     */
    @Post("/refresh")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher<MutableHttpResponse<?>> refreshToken(@Valid @Body RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return authService.refreshToken(refreshTokenRequestDTO);
    }

    /**
     * This method allows to process requests aimed at confirming the user's email.
     *
     * @param activationCode the activation code for email confirmation.
     * @return An object containing information about the user's tokens.
     * @throws APIException in case a non-existent activation code is passed.
     */
    @Put("/confirm/email")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public TokenResponseDTO confirmEmail(@QueryValue(value = "code") @NotBlank String activationCode) {
        return authService.confirmEmail(activationCode);
    }

    /**
     * This method handles requests to update the user's email.
     *
     * @param updateEmailRequestDTO an object containing data for updating the user's mail.
     * @return The user's new email address.
     * @throws APIException in following cases:
     *                      <li>• The user with the old email does not exist.</li>
     *                      <li>• The credentials don't match.</li>
     *                      <li>• The new mail is already taken.</li>
     */
    @Put("/change/email")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String changeEmail(@Valid @Body UpdateEmailRequestDTO updateEmailRequestDTO) {
        return authService.changeEmail(updateEmailRequestDTO);
    }

    /**
     * This method processes requests aimed at sending emails to users to change their password.
     *
     * @param emailRequestDTO an object containing the user's email address to which needed to send the email to change
     *                        the password.
     * @throws APIException in case the user with this email is not found.
     */
    @Post("/change/password/email")
    @PermitAll
    public void sendChangePasswordEmail(@Valid @Body EmailRequestDTO emailRequestDTO) {
        authService.sendChangePasswordEmail(emailRequestDTO);
    }

    /**
     * This method handles requests to update the user's password.
     *
     * @param changePasswordRequestDTO an object containing data for updating the user's password.
     * @throws APIException in following cases:
     *                      <li>• The user with this email is not found.</li>
     *                      <li>• The given code does not match the given email.</li>
     */
    @Put("/change/password")
    @PermitAll
    public void changePassword(@Valid @Body ChangePasswordRequestDTO changePasswordRequestDTO) {
        authService.changePassword(changePasswordRequestDTO);
    }

    /**
     * This method handles requests to log out user.
     *
     * @throws APIException in case if the user with this email is not found.
     */
    @Delete("/logout")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public void logout(HttpRequest<?> request) {
        authService.logout(request);
    }
}