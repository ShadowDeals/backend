package com.shadow.deals.function;

import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.band.main.dto.response.BandStatsInfoResponseDTO;
import com.shadow.deals.band.main.service.BandService;
import com.shadow.deals.base.BaseAuthTestContainerTest;
import com.shadow.deals.exception.APIException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class BandServiceTest extends BaseAuthTestContainerTest {

    @Inject
    private BlockedBandService blockedBandService;

    @Inject
    private BandService bandService;

    @Test
    public void testBlockedBandGetStats() {
        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO(PASSWORD);

        String accessToken = createDon();

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
        String accessToken = createDon();

        BandStatsInfoResponseDTO responseDTO = Assertions.assertDoesNotThrow(() -> bandService.selectBandStatsInfo(
            HttpRequest.GET("/band/stats").bearerAuth(accessToken))
        );

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(new BandStatsInfoResponseDTO(0, 0, 0, 0, 0), responseDTO);
    }
}
