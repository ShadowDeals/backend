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
public class BlockedBandServiceTest extends BaseAuthTestContainerTest {

    @Test
    public void testBandBlockByDon() {
        String accessToken = createDon();

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/band/block",
                    new BlockBandRequestDTO(PASSWORD)
                ).bearerAuth(accessToken)
            )
        );

        HttpResponse<Boolean> stateResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
            HttpRequest.GET(
                "/band/block"
            ).bearerAuth(accessToken),
            Boolean.class
        ));
        Assertions.assertTrue(getFromResponseBody(stateResponse));

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                    "/band/block",
                    new BlockBandRequestDTO(PASSWORD)
                ).bearerAuth(accessToken)
            )
        );
    }

    @Test
    public void testBandBlockByNotDon() {
        String donAccessToken = createUser("email1@mail.ru", UserRoleName.DON, RegionName.VASILEOSTROVKIY_REGION);
        String adminAccessToken = createUser("email2@mail.ru", UserRoleName.ADMIN, RegionName.VASILEOSTROVKIY_REGION);

        acceptAdminToBand(donAccessToken, adminAccessToken);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/band/block",
                    new BlockBandRequestDTO(PASSWORD)
                ).bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String soldier1AccessToken = createUser("emailSoldier@mail.ru", UserRoleName.SOLDIER, RegionName.VASILEOSTROVKIY_REGION);
        acceptSoldierToBand(donAccessToken, adminAccessToken, soldier1AccessToken);

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/band/block",
                    new BlockBandRequestDTO(PASSWORD)
                ).bearerAuth(soldier1AccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String userAccessToken = createUser("someUser@email.ru", UserRoleName.USER);
        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/band/block",
                    new BlockBandRequestDTO(PASSWORD)
                ).bearerAuth(userAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testBandBlockWithNoToken() {
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/band/block",
                    new BlockBandRequestDTO(PASSWORD)
                )
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void testBandAlreadyBlocked() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO(PASSWORD);

        String accessToken = createUser("dklwakdlak@mail.ru", UserRoleName.DON, RegionName.VIBORGSKY_REGION);

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/band/block",
                    blockBandRequestDTO
                ).bearerAuth(accessToken)
            )
        );

        HttpResponse<Boolean> stateResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
            HttpRequest.GET(
                "/band/block"
            ).bearerAuth(accessToken),
            Boolean.class
        ));
        Assertions.assertTrue(getFromResponseBody(stateResponse));

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.POST(
                    "/band/block",
                    blockBandRequestDTO
                ).bearerAuth(accessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        Assertions.assertEquals("База данных уже заблокирована!", exception.getMessage());

        stateResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
            HttpRequest.GET(
                "/band/block"
            ).bearerAuth(accessToken),
            Boolean.class
        ));
        Assertions.assertTrue(getFromResponseBody(stateResponse));
    }

    @Test
    public void testUnblockBand() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO(PASSWORD);

        String accessToken = createDon();

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
            HttpRequest.POST(
                "/band/block",
                blockBandRequestDTO
            ).bearerAuth(accessToken)
        ));

        Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
            HttpRequest.DELETE(
                "/band/block",
                blockBandRequestDTO
            ).bearerAuth(accessToken)
        ));

        HttpResponse<Boolean> stateResponse = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
            HttpRequest.GET(
                "/band/block"
            ).bearerAuth(accessToken),
            Boolean.class
        ));
        Assertions.assertFalse(getFromResponseBody(stateResponse));
    }

    @Test
    public void testUnblockBandByNotDon() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO(PASSWORD);

        createUser("email1@mail.ru", UserRoleName.DON, RegionName.VASILEOSTROVKIY_REGION);
        String adminAccessToken = createUser("email2@mail.ru", UserRoleName.ADMIN, RegionName.VASILEOSTROVKIY_REGION);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                    "/band/block",
                    blockBandRequestDTO
                ).bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String soldier1AccessToken = createUser("emailSoldier@mail.ru", UserRoleName.SOLDIER, RegionName.VASILEOSTROVKIY_REGION);

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                    "/band/block",
                    blockBandRequestDTO
                ).bearerAuth(soldier1AccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String userAccessToken = createUser("someUser@email.ru", UserRoleName.USER);
        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                    "/band/block",
                    blockBandRequestDTO
                ).bearerAuth(userAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testBandUnBlockWithNoToken() {
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.DELETE(
                    "/band/block",
                    new BlockBandRequestDTO(PASSWORD)
                )
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void testGetBandBlockStateByDon() {
        String accessToken = createDon();

        HttpResponse<Boolean> response = Assertions.assertDoesNotThrow(() -> client.toBlocking().exchange(
            HttpRequest.GET(
                "/band/block"
            ).bearerAuth(accessToken),
            Boolean.class
        ));

        Assertions.assertFalse(getFromResponseBody(response));
    }

    @Test
    public void testGetBandBlockStateByNotDon() {
        createUser("email1@mail.ru", UserRoleName.DON, RegionName.VASILEOSTROVKIY_REGION);
        String adminAccessToken = createUser("email2@mail.ru", UserRoleName.ADMIN, RegionName.VASILEOSTROVKIY_REGION);

        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET(
                    "/band/block"
                ).bearerAuth(adminAccessToken),
                Boolean.class
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String soldier1AccessToken = createUser("emailSoldier@mail.ru", UserRoleName.SOLDIER, RegionName.VASILEOSTROVKIY_REGION);

        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET(
                    "/band/block"
                ).bearerAuth(soldier1AccessToken),
                Boolean.class
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());

        String userAccessToken = createUser("someUser@email.ru", UserRoleName.USER);
        exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET(
                    "/band/block"
                ).bearerAuth(userAccessToken),
                Boolean.class
            )
        );

        Assertions.assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    }

    @Test
    public void testGetBandStateWithNoToken() {
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class,
            () -> client.toBlocking().exchange(
                HttpRequest.GET(
                    "/band/block"
                ),
                Boolean.class
            )
        );

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
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
