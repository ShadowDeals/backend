package com.shadow.deals.function;

import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.base.BaseAuthTestContainerTest;
import com.shadow.deals.exception.APIException;
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
public class BlockedBandServiceTest extends BaseAuthTestContainerTest {

    @Inject
    private BlockedBandService blockedBandService;

    @Test
    public void testBlockedBandNotExists() {
        Assertions.assertFalse(blockedBandService.existsByBandId(UUID.randomUUID()));
    }

    @Test
    public void testBlockedBandExists() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createDon();

        blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        );

        Assertions.assertTrue(blockedBandService.existsByBandId(TestUtils.getBandId(accessToken)));
    }

    @Test
    public void testBandBlock() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createDon();

        Assertions.assertDoesNotThrow(() -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(accessToken)
        ));

        Assertions.assertTrue(blockedBandService.existsByBandId(TestUtils.getBandId(accessToken)));
    }

    @Test
    public void testBandAlreadyBlocked() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO("TEST");

        String accessToken = createDon();

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

        String accessToken = createDon();

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

        String accessToken = createDon();

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

        String accessToken = createDon();

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
        String accessToken = createDon();

        boolean result = blockedBandService.getDbState(
            HttpRequest.GET("/band/block").bearerAuth(accessToken)
        );

        Assertions.assertFalse(result);
    }
}
