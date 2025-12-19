package com.shadow.deals.security;

import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.main.dto.response.BandStatsInfoResponseDTO;
import com.shadow.deals.band.request.dto.response.RequestResponseDTO;
import com.shadow.deals.base.BaseAuthTestContainerTest;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.role.enums.UserRoleName;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class BandServiceTest extends BaseAuthTestContainerTest {

    @Test
    public void testBandGetStatsRequestByNonDon() {
        String donAccessToken = createUser("email1@mail.ru", UserRoleName.DON, RegionName.VASILEOSTROVKIY_REGION);
        String adminAccessToken = createUser("email2@mail.ru", UserRoleName.ADMIN, RegionName.VASILEOSTROVKIY_REGION);

        acceptAdminToBand(donAccessToken, adminAccessToken);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/band/stats").bearerAuth(adminAccessToken),
                BandStatsInfoResponseDTO.class
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String soldier1AccessToken = createUser("email3@mail.ru", UserRoleName.SOLDIER, RegionName.VASILEOSTROVKIY_REGION);
        acceptSoldierToBand(donAccessToken, adminAccessToken, soldier1AccessToken);

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/band/stats").bearerAuth(soldier1AccessToken),
                BandStatsInfoResponseDTO.class
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testBandGetStatsRequestByDon() {
        String donAccessToken = createUser("email11@mail.ru", UserRoleName.DON, RegionName.MOSCOW_REGION);

        HttpResponse<BandStatsInfoResponseDTO> statsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/band/stats").bearerAuth(donAccessToken),
                BandStatsInfoResponseDTO.class
            )
        );

        BandStatsInfoResponseDTO statsDTO = getFromResponseBody(statsResponse);
        Assertions.assertEquals(new BandStatsInfoResponseDTO(0, 0, 0, 0, 0), statsDTO);
    }

    @Test
    public void testBandGetStatsRequestWithoutToken() {
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/band/stats"),
                BandStatsInfoResponseDTO.class
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void testBandGetStatsRequestByDonWithInvalidToken() {
        String donAccessToken = createUser("email11@mail.ru", UserRoleName.DON, RegionName.MOSCOW_REGION);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/band/stats").bearerAuth(donAccessToken + "b"),
                BandStatsInfoResponseDTO.class
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void testBandGetStatsRequestByUser() {
        String accessToken = createUser("email222@mail.ru", UserRoleName.USER, RegionName.MOSCOW_REGION);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/band/stats").bearerAuth(accessToken),
                BandStatsInfoResponseDTO.class
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testBlockedBandGetStats() {
        String accessToken = createUser("email111@mail.ru", UserRoleName.DON, RegionName.VIBORGSKY_REGION);

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/band/block",
                    new BlockBandRequestDTO("qweewq")
                ).bearerAuth(accessToken)
            )
        );

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET("/band/stats").bearerAuth(accessToken),
                BandStatsInfoResponseDTO.class
            )
        );

        Assertions.assertEquals(HttpStatus.LOCKED, exception.getStatus());
        Assertions.assertEquals("База данных заблокирована!", exception.getMessage());
    }

    private void acceptSoldierToBand(String donAccessToken, String adminAccessToken, String soldierAccessToken) {
        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST("/request?regionName=" + RegionName.VASILEOSTROVKIY_REGION.name(), null).bearerAuth(soldierAccessToken)
            )
        );

        HttpResponse<List<RequestResponseDTO>> requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken),
                Argument.listOf(RequestResponseDTO.class)
            )
        );
        List<RequestResponseDTO> requests = getFromResponseBody(requestsResponse);
        Assertions.assertEquals(1, requests.size());

        RequestResponseDTO request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.SOLDIER, request.getUserRole());

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.PUT("/request?requestId=" + request.getId(), null).bearerAuth(adminAccessToken)
            )
        );

        requestsResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken),
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
        Assertions.assertEquals(new BandStatsInfoResponseDTO(1, 1, 0, 0, 0), statsDTO);
    }

    private void acceptAdminToBand(String donAccessToken, String adminAccessToken) {
        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST("/request?regionName=" + RegionName.VASILEOSTROVKIY_REGION.name(), null).bearerAuth(adminAccessToken)
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
        Assertions.assertEquals(new BandStatsInfoResponseDTO(1, 0, 0, 0, 0), statsDTO);
    }
}
