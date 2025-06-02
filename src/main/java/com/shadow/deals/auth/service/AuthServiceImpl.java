package com.shadow.deals.auth.service;

import com.shadow.deals.auth.dto.request.ChangePasswordRequestDTO;
import com.shadow.deals.auth.dto.request.EmailRequestDTO;
import com.shadow.deals.auth.dto.request.RefreshTokenRequestDTO;
import com.shadow.deals.auth.dto.request.SignInRequestDTO;
import com.shadow.deals.auth.dto.request.SignUpRequestDTO;
import com.shadow.deals.auth.dto.request.UpdateEmailRequestDTO;
import com.shadow.deals.auth.dto.response.TokenResponseDTO;
import com.shadow.deals.auth.mapper.AuthMapper;
import com.shadow.deals.auth.password.PasswordEncoder;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.main.service.BandService;
import com.shadow.deals.band.request.service.RequestService;
import com.shadow.deals.email.service.MailService;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.exception.APIExceptionResponse;
import com.shadow.deals.user.activation.entity.ActivationCode;
import com.shadow.deals.user.activation.service.ActivationCodeServiceImpl;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.mapper.UserMapper;
import com.shadow.deals.user.main.service.UserServiceImpl;
import com.shadow.deals.user.refresh.service.RefreshTokenService;
import com.shadow.deals.region.service.RegionService;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.user.role.service.UserRoleService;
import com.shadow.deals.util.CommonUtils;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.Authenticator;
import io.micronaut.security.token.generator.AccessTokenConfiguration;
import io.micronaut.security.token.generator.TokenGenerator;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * This class contains methods that perform business logic related to the authentication.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    /**
     * An instance of the interface that allows to generate an access token.
     */
    private final TokenGenerator tokenGenerator;

    /**
     * An instance of the interface that provides access to the access token configuration.
     */
    private final AccessTokenConfiguration accessTokenConfiguration;

    /**
     * An instance of the interface that provides the ability to encode a password.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * An object of the class that provides the ability to interact with the {@link User} entity.
     */
    private final UserServiceImpl userService;

    /**
     * An instance of the interface that provides the ability to interact with the {@link com.shadow.deals.user.refresh.entity.RefreshToken}
     * entity.
     */
    private final RefreshTokenService refreshTokenService;

    /**
     * An instance of the interface that provides the ability to interact with the {@link com.shadow.deals.user.role.entity.UserRole}
     * entity.
     */
    private final UserRoleService userRoleService;

    /**
     * An object of the {@link Authenticator} class that allows authentication of the request with the given login data.
     */
    private final Authenticator<HttpRequest<?>> authenticator;

    /**
     * Instance of the service interface that allows to persist refresh tokens and retrieve user details by a refresh token.
     */
    private final RefreshTokenPersistence refreshTokenPersistence;

    /**
     * An instance of an interface that provides methods for sending email messages.
     */
    private final MailService mailService;

    /**
     * An object of the class that provides the ability to interact with the {@link ActivationCode} entity.
     */
    private final ActivationCodeServiceImpl activationCodeService;

    private final BandService bandService;

    private final RegionService regionService;

    private final RequestService requestService;

    /**
     * The address of the client application.
     */
    @Value("${shadow-deals.client.address}")
    private String clientAddress;

    /**
     * The path where the password change will be displayed on the client.
     */
    @Value("${shadow-deals.client.path.change-password}")
    private String clientChangePasswordPath;

    /**
     * The path where the email confirm will be displayed on the client.
     */
    @Value("${shadow-deals.client.path.confirm-email}")
    private String clientConfirmEmailPath;

    /**
     * The identifier that corresponds to the Shadow Deals logo in the sent email.
     */
    private final static String LOGO_EMAIL_CID = "logo";

    /**
     * The path to the logo file.
     */
    private final static String LOGO_EMAIL_PATH = "image/logo.png";

    /**
     * For how many days to issue a token, if necessary, to remember the user.
     */
    private final static int REMEMBER_ME_FOR_DAYS = 7;

    /**
     * This method allows to add a new user to the database.
     *
     * @param signUpRequestDTO data for registering a new user.
     * @return The user's email.
     * @throws APIException in following cases:
     *                      <li>• User with such an email already exists. It throws with the {@link HttpStatus#BAD_REQUEST}.</li>
     *                      <li>• The requested country does not exist.</li>
     *                      <li>• The role {@link UserRoleName#USER} does not exist in the database.</li>
     *                      <li>• The refresh token could not be generated.</li>
     *                      <li>• The access token could not be generated.</li>
     */
    @Override
    public String signup(@NotNull SignUpRequestDTO signUpRequestDTO) throws APIException {
        String userEmail = signUpRequestDTO.getEmail();
        if (userService.existsByEmail(userEmail)) {
            throw new APIException(
                    "Пользователь с почтой %s уже существует".formatted(userEmail), HttpStatus.BAD_REQUEST);
        }
        String activationCodeVal = generateUUIDFromString(userEmail);
        sendSignUpEmail(userEmail, signUpRequestDTO.getNickname(), activationCodeVal);

        User user = mapSignUpRequestDTOToUser(signUpRequestDTO);

        UserRoleName userRoleName = signUpRequestDTO.getRole();
        if (userRoleName == UserRoleName.DON) {
            createAndSaveBand(user, signUpRequestDTO);
        } else if (userRoleName != UserRoleName.USER) {
            requestService.createRequests(user, signUpRequestDTO.getRegion());
        }

        createAndSaveActivationCode(user, activationCodeVal);

        return user.getEmail();
    }

    /**
     * This method allows user authentication.
     *
     * @param signInRequestDTO authentication data.
     * @param request          the request corresponding to authentication.
     * @return {@link Publisher}, containing the authentication result - successful or with an error.
     */
    @Override
    public Publisher<MutableHttpResponse<?>> signin(@NotNull SignInRequestDTO signInRequestDTO, HttpRequest<?> request) {
        String authUnexpectedError = "Непредвиденная ошибка во время аутентификации";
        int rememberForDays = signInRequestDTO.isRememberMe() ? REMEMBER_ME_FOR_DAYS : 1;
        return Flux.from(authenticator.authenticate(request, AuthMapper.INSTANCE.signInRequestDTOToUsernamePasswordCredentials(signInRequestDTO)))
                .map(authenticationResponse -> {
                    if (!authenticationResponse.isAuthenticated() || authenticationResponse.getAuthentication().isEmpty()) {
                        String message = authenticationResponse.getMessage().orElse(authUnexpectedError);
                        return createUnauthorizedResponse(message);
                    }
                    Authentication authentication = authenticationResponse.getAuthentication().get();
                    User user = userService.findByEmail(authentication.getName());
                    return HttpResponse.ok(generateTokenResponse(user, authentication, rememberForDays * getAccessTokenExpiration()));
                }).switchIfEmpty(Mono.defer(() -> Mono.just(createUnauthorizedResponse(authUnexpectedError))));
    }

    /**
     * This method allows to refresh the user's access token.
     *
     * @param refreshTokenRequestDTO the request body containing the refresh token.
     * @return {@link Publisher}, containing the refresh token result - successful or with an error.
     * @throws APIException in case an invalid refresh token is passed.
     */
    @Override
    public Publisher<MutableHttpResponse<?>> refreshToken(@NotNull RefreshTokenRequestDTO refreshTokenRequestDTO) throws APIException {
        String refreshToken = refreshTokenRequestDTO.getRefreshToken();
        String validRefreshToken = refreshTokenService.validateRefreshToken(refreshToken);
        return Mono.from(refreshTokenPersistence.getAuthentication(validRefreshToken))
                .map(authentication -> HttpResponse.ok(generateTokenResponse(refreshToken, authentication, getAccessTokenExpiration())));
    }

    /**
     * This method allows to get the lifetime of the access token in seconds.
     *
     * @return The lifetime of the access token in seconds.
     */
    @Override
    public int getAccessTokenExpiration() {
        return accessTokenConfiguration.getExpiration();
    }

    /**
     * This method allows to verify the user's email by setting the activation code to {@code null} for the corresponding
     * user.
     *
     * @param activationCodeVal the activation code for email confirmation.
     * @return An object containing information about the user's tokens.
     * @throws APIException in case a non-existent activation code is passed.
     */
    @Override
    public TokenResponseDTO confirmEmail(String activationCodeVal) throws APIException {
        ActivationCode activationCode = activationCodeService.findByActivationCode(activationCodeVal);
        activationCode.setActivationCode(null);
        activationCode.setActivated(true);
        activationCodeService.update(activationCode);

        User user = activationCode.getUser();
        Authentication authentication = Authentication.build(user.getEmail(), Set.of(userService.getUserRole(user)));

        return generateTokenResponse(user, authentication, getAccessTokenExpiration());
    }

    /**
     * This method allows to change the user's email address and send a message to a new email address to confirm it.
     *
     * @param updateEmailRequestDTO an object containing data for updating the user's mail.
     * @return The user's new email address.
     * @throws APIException in following cases:
     *                      <li>• The user with the old email does not exist.</li>
     *                      <li>• The credentials don't match.</li>
     *                      <li>• The new mail is already taken.</li>
     */
    @Override
    public String changeEmail(@NotNull UpdateEmailRequestDTO updateEmailRequestDTO) throws APIException {
        User user = userService.findByEmail(updateEmailRequestDTO.getOldEmail());

        if (!passwordEncoder.matches(updateEmailRequestDTO.getPassword(), user.getPassword())) {
            throw new APIException(
                    "Данные пользователя не совпадают",
                    HttpStatus.BAD_REQUEST
            );
        }
        String newEmail = updateEmailRequestDTO.getNewEmail();
        if (userService.existsByEmail(newEmail)) {
            throw new APIException(
                    "Пользователь с почтой %s уже существует".formatted(newEmail),
                    HttpStatus.BAD_REQUEST);
        }

        ActivationCode activationCode = user.getActivationCode();

        String activationCodeVal;
        if (activationCode == null) {
            activationCodeVal = generateUUIDFromString(newEmail);
            createAndSaveActivationCode(user, activationCodeVal);
        } else {
            activationCodeVal = activationCode.getActivationCode();
            if (activationCodeVal == null) {
                activationCodeVal = generateUUIDFromString(newEmail);
                activationCode.setActivationCode(activationCodeVal);
                activationCodeService.update(activationCode);
            }
        }

        user.setEmail(newEmail);
        userService.update(user);
        sendSignUpEmail(newEmail, user.getNickname(), activationCodeVal);
        return newEmail;
    }

    /**
     * This method allows to send an email to the user to change the password.
     *
     * @param emailRequestDTO an object containing the user's email address to which need to send
     *                        an email to change the password.
     * @throws APIException in case the user with this email is not found.
     */
    @Override
    public void sendChangePasswordEmail(@NotNull EmailRequestDTO emailRequestDTO) throws APIException {
        String email = emailRequestDTO.getEmail();
        User user = userService.findByEmail(email);

        mailService.sendMessage(email,
                "Смена пароля в Shadow Deals",
                createChangePasswordEmail(user.getNickname(), email),
                Map.of(LOGO_EMAIL_CID, LOGO_EMAIL_PATH)
        );
    }

    /**
     * This method allows to set a new password for the user.
     *
     * @param changePasswordRequestDTO an object containing data for updating the user's password.
     * @throws APIException in following cases:
     *                      <li>• The user with this email is not found.</li>
     *                      <li>• The given code does not match the given email.</li>
     */
    @Override
    public void changePassword(@NotNull ChangePasswordRequestDTO changePasswordRequestDTO) throws APIException {
        String email = changePasswordRequestDTO.getEmail();
        if (!generateUUIDFromString(email).equals(changePasswordRequestDTO.getChangePasswordCode().toString())) {
            throw new APIException(
                    "Код смены пароля не соответствует почте %s".formatted(email),
                    HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByEmail(email);
        user.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));
        userService.update(user);
    }

    /**
     * This method allows to logout user from the system.
     *
     * @param request the request corresponding to logout.
     * @throws APIException in case if the user with this email is not found.
     */
    @Override
    public void logout(@NotNull HttpRequest<?> request) throws APIException {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);
        refreshTokenService.deleteByUser(user);
    }

    /**
     * This method allows to create an HTML representation of the message body, which is sent to users during
     * password changing.
     *
     * @param nickname the nickname of the recipient.
     * @param email    the email of the recipient.
     * @return HTML representation of the message body, which is sent to users during password changing.
     */
    private String createChangePasswordEmail(@NotBlank String nickname, String email) {
        String boldText = "Здравствуй, %s".formatted(nickname);
        String link = clientAddress + clientChangePasswordPath.formatted(generateUUIDFromString(email), email);
        String buttonText = "Смените Ваш пароль.";
        String regularText = "Если вы не пытались изменить пароль своего аккаунта, то можете проигнорировать это письмо. Возможно, другой пользователь ввёл свою информацию неправильно.";
        return mailService.fillEmailTemplate(boldText, link, buttonText, regularText);
    }

    /**
     * This method allows to send an email confirmation message when registering a new user.
     *
     * @param to             the recipient of the email.
     * @param nickname       the nickname of the recipient.
     * @param activationCode the activation code for email confirmation.
     */
    private void sendSignUpEmail(String to, String nickname, String activationCode) {
        mailService.sendMessage(to,
                "Подтверждение почты",
                createSignUpEmail(nickname, activationCode),
                Map.of(LOGO_EMAIL_CID, LOGO_EMAIL_PATH)
        );
    }

    /**
     * This method allows to create an HTML representation of the message body, which is sent to users during
     * registration to confirm the email address.
     *
     * @param nickname       the nickname of the recipient.
     * @param activationCode the activation code for email confirmation.
     * @return HTML representation of the message body, which is sent to users during registration to confirm the email
     * address.
     */
    @NotNull
    private String createSignUpEmail(String nickname, String activationCode) {
        String boldText = "Здравствуй, %s".formatted(nickname);
        String link = clientAddress + clientConfirmEmailPath.formatted(activationCode);
        String buttonText = "Подтвердите почту";
        String regularText = boldText +
                "\n" +
                "\n" +
                "\n" +
                "Мы рады, что ты присоединился к нам! Для завершения регистрации осталось подтвердить адрес электронной почты, нажав на кнопку выше.";
        return mailService.fillEmailTemplate(boldText, link, buttonText, regularText);
    }

    /**
     * This method allows to create and save the entity of {@link ActivationCode}.
     *
     * @param user              the user's entity that the activation code is linked to.
     * @param activationCodeVal the activation code for email confirmation.
     */
    private void createAndSaveActivationCode(User user, String activationCodeVal) {
        ActivationCode activationCode = new ActivationCode();
        activationCode.setActivationCode(activationCodeVal);
        activationCode.setUser(user);
        activationCodeService.save(activationCode);
    }

    /**
     * This method allows to create a server response in case of unsuccessful authorization.
     *
     * @param message exception message.
     * @return The server response with the body {@link APIExceptionResponse} and the code {@link HttpStatus#UNAUTHORIZED}.
     */
    private MutableHttpResponse<APIExceptionResponse> createUnauthorizedResponse(String message) {
        APIExceptionResponse apiExceptionResponse = new APIExceptionResponse(message, HttpStatus.UNAUTHORIZED.getCode());
        return HttpResponse.status(HttpStatus.UNAUTHORIZED).body(apiExceptionResponse);
    }

    /**
     * This method allows to form the server response body as an object of the {@link TokenResponseDTO} class.
     *
     * @param user                  the entity of the user for whom authentication is being performed.
     * @param authentication        data of the authenticated user.
     * @param accessTokenExpiration the access token lifetime.
     * @return The server response body as an object of the {@link TokenResponseDTO} class.
     */
    @Contract("_, _, _ -> new")
    @NotNull
    private TokenResponseDTO generateTokenResponse(User user, Authentication authentication, int accessTokenExpiration) {
        return generateTokenResponse(generateRefreshToken(authentication, user), authentication, accessTokenExpiration);
    }

    /**
     * This method allows to form the server response body as an object of the {@link TokenResponseDTO} class.
     *
     * @param refreshToken          the refresh token.
     * @param authentication        data of the authenticated user.
     * @param accessTokenExpiration the access token lifetime.
     * @return The server response body as an object of the {@link TokenResponseDTO} class.
     */
    @NotNull
    private TokenResponseDTO generateTokenResponse(String refreshToken, Authentication authentication, int accessTokenExpiration) {
        return new TokenResponseDTO(
                generateAccessToken(authentication, accessTokenExpiration),
                accessTokenExpiration,
                refreshToken,
                authentication.getName()
        );
    }

    /**
     * This method allows to convert {@link SignUpRequestDTO} into {@link User} entity.
     *
     * @param signUpRequestDTO data for registering a new user.
     * @return Entity of new user.
     * @throws APIException in following cases:
     *                      <li>• The requested country does not exist.</li>
     *                      <li>• The role {@link UserRoleName#USER} does not exist in the database.</li>
     */
    private User mapSignUpRequestDTOToUser(SignUpRequestDTO signUpRequestDTO) throws APIException {
        User user = UserMapper.INSTANCE.toUserFromSignUpRequestDTO(signUpRequestDTO);
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        user.setRole(userRoleService.findUserRoleByName(signUpRequestDTO.getRole()));

        return userService.save(user);
    }

    private void createAndSaveBand(User user, @NotNull SignUpRequestDTO signUpRequestDTO) {
        Band band = new Band();
        band.setDon(user);
        band.setRegion(regionService.findByRegionName(signUpRequestDTO.getRegion()));
        bandService.save(band);
    }

    /**
     * This method allows to generate a refresh token.
     *
     * @param authentication data of the user for whom the token is being generated.
     * @param user           the user for whom the token is being created.
     * @return A refresh token.
     * @throws APIException in case the refresh token could not be generated.
     */
    private String generateRefreshToken(Authentication authentication, @NotNull User user) throws APIException {
        if (user.getRefreshToken() != null) {
            return refreshTokenService.generateRefreshToken(authentication, user.getRefreshToken().getRefreshToken());
        }
        String refreshTokenKey = refreshTokenService.createRefreshTokenKey(authentication);
        refreshTokenService.createAndSaveRefreshToken(user, refreshTokenKey);
        return refreshTokenService.generateRefreshToken(authentication, refreshTokenKey);
    }

    /**
     * This method allows to generate an access token.
     *
     * @param authentication data of the user for whom the token is being generated.
     * @param expiration     the lifetime of the access token in seconds.
     * @return An access token.
     * @throws APIException in case it was not possible to generate an access token. It throws with the
     *                      {@link HttpStatus#INTERNAL_SERVER_ERROR} code.
     */
    @NotNull
    private String generateAccessToken(Authentication authentication, int expiration) throws APIException {
        Optional<String> optionalAccessToken = tokenGenerator.generateToken(authentication, expiration);
        if (optionalAccessToken.isEmpty()) {
            throw new APIException("Ошибка во время генерации токена доступа", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return optionalAccessToken.get();
    }

    /**
     * This method allows to generate a string representation of UUID from a string.
     *
     * @param str the string to use to generate the UUID.
     * @return The string representation of UUID.
     */
    private String generateUUIDFromString(@NotNull String str) {
        return UUID.nameUUIDFromBytes(str.getBytes()).toString();
    }
}