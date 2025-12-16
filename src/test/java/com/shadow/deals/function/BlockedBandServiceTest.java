package com.shadow.deals.function;

import com.shadow.deals.BaseTestContainerTest;
import com.shadow.deals.auth.dto.request.SignUpRequestDTO;
import com.shadow.deals.auth.service.AuthService;
import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.CommonUtils;
import com.shadow.deals.util.TestUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class BlockedBandServiceTest extends BaseTestContainerTest {

    @Inject
    private BlockedBandService blockedBandService;

    @Inject
    private AuthService authService;

    @Test
    public void testBlockedBandNotExists() {
        Assertions.assertFalse(blockedBandService.existsByBandId(UUID.randomUUID()));
    }

    @Test
    public void testBlockedBandExists() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createUser();

        blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        );

        Assertions.assertTrue(blockedBandService.existsByBandId(TestUtils.getBandId(accessToken)));
    }

    @Test
    public void testBandBlock() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createUser();

        Assertions.assertDoesNotThrow(() -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        ));

        Assertions.assertTrue(blockedBandService.existsByBandId(TestUtils.getBandId(accessToken)));
    }

    @Test
    public void testBandAlreadyBlocked() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createUser();

        blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        );

        APIException exception = Assertions.assertThrows(APIException.class, () -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        ));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        Assertions.assertEquals("База данных уже заблокирована!", exception.getMessage());
        Assertions.assertTrue(blockedBandService.existsByBandId(TestUtils.getBandId(accessToken)));
    }

    @Test
    public void testUnblockNonBlockedBand() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createUser();

        APIException exception = Assertions.assertThrows(APIException.class, () -> blockedBandService.unblockDb(
            blockBandRequestDTO,
            HttpRequest.DELETE("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        ));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        Assertions.assertEquals("База данных не заблокирована!", exception.getMessage());
        Assertions.assertFalse(blockedBandService.existsByBandId(TestUtils.getBandId(accessToken)));
    }

    @Test
    public void testUnblockBand() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createUser();

        Assertions.assertDoesNotThrow(() -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        ));

        Assertions.assertDoesNotThrow(() -> blockedBandService.unblockDb(
            blockBandRequestDTO,
            HttpRequest.DELETE("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        ));

        Assertions.assertFalse(blockedBandService.existsByBandId(TestUtils.getBandId(accessToken)));
    }

    @Test
    public void testIsBandBlocked() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createUser();

        Assertions.assertDoesNotThrow(() -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        ));

        boolean result = blockedBandService.getDbState(
            HttpRequest.GET("/band/block").bearerAuth(accessToken)
        );

        Assertions.assertTrue(result);
    }

    @Test
    public void testIsBandNotBlocked() {
        String accessToken = createUser();

        boolean result = blockedBandService.getDbState(
            HttpRequest.GET("/band/block").bearerAuth(accessToken)
        );

        Assertions.assertFalse(result);
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
