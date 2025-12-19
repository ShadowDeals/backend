package com.shadow.deals.security;

import com.shadow.deals.band.main.dto.response.BandStatsInfoResponseDTO;
import com.shadow.deals.band.request.dto.response.OwnRequestResponseDTO;
import com.shadow.deals.band.request.dto.response.RequestResponseDTO;
import com.shadow.deals.base.BaseAuthTestContainerTest;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.TestUtils;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class RequestServiceTest extends BaseAuthTestContainerTest {

    @Test
    public void testCreateAdminRequest() {
        String donAccessToken = createDon();
        String adminAccessToken = createUser("email1@mail.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST(
                        "/request?regionName=" + RegionName.MOSCOW_REGION.name(),
                        null
                    )
                    .bearerAuth(adminAccessToken)
            )
        );

        HttpResponse<List<OwnRequestResponseDTO>> requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request/own").bearerAuth(adminAccessToken),
                Argument.listOf(OwnRequestResponseDTO.class)
            )
        );

        List<OwnRequestResponseDTO> requests = getFromResponseBody(requestsResponse);

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());
        Assertions.assertEquals(TestUtils.getBandId(donAccessToken), requests.getFirst().getBandId());
        Assertions.assertEquals(RegionName.MOSCOW_REGION, requests.getFirst().getBandRegion());
    }

    @Test
    public void testCreateSoldierRequest() {
        String donAccessToken = createDon();
        String soldierAccessToken = createUser("email2@mail.ru", UserRoleName.SOLDIER);

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST(
                        "/request?regionName=" + RegionName.MOSCOW_REGION.name(),
                        null
                    )
                    .bearerAuth(soldierAccessToken)
            )
        );

        HttpResponse<List<OwnRequestResponseDTO>> requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request/own").bearerAuth(soldierAccessToken),
                Argument.listOf(OwnRequestResponseDTO.class)
            )
        );

        List<OwnRequestResponseDTO> requests = getFromResponseBody(requestsResponse);

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());
        Assertions.assertEquals(TestUtils.getBandId(donAccessToken), requests.getFirst().getBandId());
        Assertions.assertEquals(RegionName.MOSCOW_REGION, requests.getFirst().getBandRegion());
    }

    @Test
    public void testCreateDonRequest() {
        String donAccessToken = createDon();

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.POST(
                        "/request?regionName=" + RegionName.MOSCOW_REGION.name(),
                        null
                    )
                    .bearerAuth(donAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/request/own").bearerAuth(donAccessToken),
                Argument.listOf(OwnRequestResponseDTO.class)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testCreateUserRequest() {
        String userAccessToken = createUser("email3@mail.ru", UserRoleName.USER);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.POST(
                        "/request?regionName=" + RegionName.MOSCOW_REGION.name(),
                        null
                    )
                    .bearerAuth(userAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/request/own").bearerAuth(userAccessToken),
                Argument.listOf(OwnRequestResponseDTO.class)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testCreateRequestWithNoToken() {
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/request?regionName=" + RegionName.MOSCOW_REGION.name(),
                    null
                )
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/request/own"),
                Argument.listOf(OwnRequestResponseDTO.class)
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void testGetRequests() {
        String donAccessToken = createUser("email4@mail.ru", UserRoleName.DON, RegionName.VIBORGSKY_REGION);

        HttpResponse<List<RequestResponseDTO>> requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(donAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );

        getFromResponseBody(requestsResponse);

        String adminAccessToken = createUser("email5@mail.ru", UserRoleName.ADMIN, RegionName.VIBORGSKY_REGION);
        acceptAdminToBand(donAccessToken, adminAccessToken);
        requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );

        getFromResponseBody(requestsResponse);
    }

    @Test
    public void testGetRequestsWithNoAccess() {
        createUser("email4@mail.ru", UserRoleName.DON, RegionName.VIBORGSKY_REGION);
        String soldierAccessToken = createUser("email6@mail.ru", UserRoleName.SOLDIER, RegionName.VIBORGSKY_REGION);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(soldierAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String userAccessToken = createUser("email3@mail.ru", UserRoleName.USER);

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(userAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testGetRequestsWithNoToken() {
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/request"),
                Argument.listOf(RequestResponseDTO.class)
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void testDeleteAdminRequest() {
        String donAccessToken = createDon();
        String adminAccessToken = createUser("email11@mail.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST(
                        "/request?regionName=" + RegionName.MOSCOW_REGION.name(),
                        null
                    )
                    .bearerAuth(adminAccessToken)
            )
        );

        HttpResponse<List<RequestResponseDTO>> requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(donAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );

        List<RequestResponseDTO> requests = getFromResponseBody(requestsResponse);
        Assertions.assertFalse(requests.isEmpty());

        RequestResponseDTO request = requests.getFirst();
        Assertions.assertNotNull(request);

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                        "/request?requestId=" + request.getId(),
                        null
                    )
                    .bearerAuth(donAccessToken)
            )
        );
    }

    @Test
    public void testDeleteSoldierRequest() {
        String donAccessToken = createUser("email4@mail.ru", UserRoleName.DON, RegionName.VIBORGSKY_REGION);
        String adminAccessToken = createUser("email12@mail.ru", UserRoleName.ADMIN, RegionName.VIBORGSKY_REGION);

        acceptAdminToBand(donAccessToken, adminAccessToken);

        String soldierAccessToken = createUser("email13@mail.ru", UserRoleName.SOLDIER, RegionName.VIBORGSKY_REGION);

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST(
                        "/request?regionName=" + RegionName.VIBORGSKY_REGION.name(),
                        null
                    )
                    .bearerAuth(soldierAccessToken)
            )
        );

        HttpResponse<List<RequestResponseDTO>> requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );

        List<RequestResponseDTO> requests = getFromResponseBody(requestsResponse);
        Assertions.assertFalse(requests.isEmpty());

        RequestResponseDTO request = requests.getFirst();
        Assertions.assertNotNull(request);

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                        "/request?requestId=" + request.getId(),
                        null
                    )
                    .bearerAuth(adminAccessToken)
            )
        );
    }

    @Test
    public void testDeleteRequestWithNoAccess() {
        createDon();
        String soldierAccessToken = createUser("email21@mail.ru", UserRoleName.SOLDIER);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                        "/request?requestId=" + UUID.randomUUID(),
                        null
                    )
                    .bearerAuth(soldierAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String userAccessToken = createUser("email22@mail.ru", UserRoleName.USER);

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                        "/request?requestId=" + UUID.randomUUID(),
                        null
                    )
                    .bearerAuth(userAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testDeleteRequestWithNoToken() {
        createDon();
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                    "/request?requestId=" + UUID.randomUUID(),
                    null
                )
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void testAcceptRequestWithNoAccess() {
        createDon();
        String soldierAccessToken = createUser("email21@mail.ru", UserRoleName.SOLDIER);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.PUT(
                        "/request?requestId=" + UUID.randomUUID(),
                        null
                    )
                    .bearerAuth(soldierAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String userAccessToken = createUser("email22@mail.ru", UserRoleName.USER);

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.PUT(
                        "/request?requestId=" + UUID.randomUUID(),
                        null
                    )
                    .bearerAuth(userAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testAcceptRequestWithNoToken() {
        createDon();
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.PUT(
                    "/request?requestId=" + UUID.randomUUID(),
                    null
                )
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    private void acceptAdminToBand(String donAccessToken, String adminAccessToken) {
        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST("/request?regionName=" + RegionName.VIBORGSKY_REGION.name(), null).bearerAuth(adminAccessToken)
            )
        );

        HttpResponse<List<RequestResponseDTO>> requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(donAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );
        List<RequestResponseDTO> requests = getFromResponseBody(requestsResponse);
        Assertions.assertEquals(1, requests.size());

        RequestResponseDTO request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.ADMIN, request.getUserRole());

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.PUT("/request?requestId=" + request.getId(), null).bearerAuth(donAccessToken)
            )
        );

        requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(donAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );

        requests = getFromResponseBody(requestsResponse);

        Assertions.assertTrue(requests.isEmpty());

        HttpResponse<BandStatsInfoResponseDTO> statsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/band/stats").bearerAuth(donAccessToken),
                BandStatsInfoResponseDTO.class
            )
        );

        BandStatsInfoResponseDTO statsDTO = getFromResponseBody(statsResponse);
        Assertions.assertTrue(statsDTO.getAdminsCount() != 0);
    }
}
