package com.shadow.deals.function;

import com.shadow.deals.BaseTestContainerTest;
import com.shadow.deals.auth.dto.request.SignUpRequestDTO;
import com.shadow.deals.auth.service.AuthService;
import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.band.main.dto.response.BandStatsInfoResponseDTO;
import com.shadow.deals.band.main.service.BandService;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.CommonUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class BandServiceTest extends BaseTestContainerTest {

    @Inject
    private AuthService authService;

    @Inject
    private BlockedBandService blockedBandService;

    @Inject
    private BandService bandService;

    @Test
    public void testBlockedBandGetStats() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createUser();

        blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        );

        APIException exception = Assertions.assertThrows(APIException.class, () -> bandService.selectBandStatsInfo(
            HttpRequest.GET("/band/stats"
            ).bearerAuth(accessToken))
        );

        Assertions.assertEquals(HttpStatus.LOCKED, exception.getCode());
        Assertions.assertEquals("База данных заблокирована!", exception.getMessage());
    }

    @Test
    public void testGetStats() {
        String accessToken = createUser();

        BandStatsInfoResponseDTO responseDTO = Assertions.assertDoesNotThrow(() -> bandService.selectBandStatsInfo(
            HttpRequest.GET("/band/stats").bearerAuth(accessToken))
        );

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(new BandStatsInfoResponseDTO(0, 0, 0, 0, 0), responseDTO);
    }

    private String createUser() {
        String email = "volce.chat@mail.ru";

        authService.signup(
            new SignUpRequestDTO(
                "TEST",
                "TEST",
                "TEST",
                "TEST",
                email,
                UserRoleName.DON,
                RegionName.MOSCOW_REGION
            )
        );

        return authService.confirmEmail(CommonUtils.generateUUIDFromString(email)).getAccessToken();
    }
}
