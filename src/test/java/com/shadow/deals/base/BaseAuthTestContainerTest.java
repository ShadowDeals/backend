package com.shadow.deals.base;

import com.shadow.deals.auth.dto.request.SignInRequestDTO;
import com.shadow.deals.auth.dto.request.SignUpRequestDTO;
import com.shadow.deals.auth.dto.response.TokenResponseDTO;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.main.service.UserService;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.CommonUtils;
import com.shadow.deals.util.TestUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;

/**
 * @author Kirill "Tamada" Simovin
 */
public abstract class BaseAuthTestContainerTest extends BaseTestContainerTest {

    @Inject
    @Client("/")
    protected HttpClient client;

    @Inject
    private UserService userService;

    protected final static String PASSWORD = "qweewq";

    protected String createDon() {
        return createUser("volce.chat@mail.ru", UserRoleName.DON, RegionName.MOSCOW_REGION);
    }

    protected String createUser(String email, UserRoleName role) {
        return createUser(email, role, RegionName.MOSCOW_REGION);
    }

    protected String createUser(String email, UserRoleName role, RegionName region) {
        try {
            HttpResponse<TokenResponseDTO> response = client.toBlocking().exchange(
                HttpRequest.POST(
                    "/auth/signin",
                    new SignInRequestDTO(
                        email,
                        PASSWORD,
                        false
                    )
                ),
                TokenResponseDTO.class
            );

            TokenResponseDTO tokenBody = getFromResponseBody(response);
            return tokenBody.getAccessToken();
        } catch (Exception e) {
            return createUserRequest(email, role, region);
        }
    }

    private String createUserRequest(String email, UserRoleName role, RegionName region) {

        SignUpRequestDTO dto = new SignUpRequestDTO(
            "TEST",
            "TEST",
            "TEST",
            PASSWORD,
            email,
            role,
            region
        );

        HttpResponse<String> emailResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST("/auth/signup", dto),
                String.class
            )
        );
        String emailBody = getFromResponseBody(emailResponse);

        String activationCode = CommonUtils.generateUUIDFromString(emailBody);

        HttpResponse<TokenResponseDTO> response = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.PUT("/auth/confirm/email?code=" + activationCode, null),
                TokenResponseDTO.class
            )
        );
        TokenResponseDTO tokenBody = getFromResponseBody(response);

        return tokenBody.getAccessToken();
    }

    protected <R> R getFromResponseBody(HttpResponse<R> response) {
        if (response == null) {
            Assertions.fail("response is null");
        }

        Optional<R> bodyOpt = response.getBody();
        Assertions.assertTrue(bodyOpt.isPresent());

        R body = bodyOpt.get();
        Assertions.assertNotNull(body);

        return body;
    }

    protected UUID getUserIdFromAccessToken(String accessToken) {
        String userEmail = TestUtils.getFromAccessToken(accessToken, "sub");
        return userService.findByEmail(userEmail).getId();
    }
}
