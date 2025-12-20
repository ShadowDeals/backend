package com.shadow.deals.function;

import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.band.main.dto.response.BandStatsInfoResponseDTO;
import com.shadow.deals.band.main.service.BandService;
import com.shadow.deals.band.request.dto.response.OwnRequestResponseDTO;
import com.shadow.deals.band.request.dto.response.RequestResponseDTO;
import com.shadow.deals.band.request.service.RequestService;
import com.shadow.deals.base.BaseAuthTestContainerTest;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.main.dto.response.BandWorkerResponseDTO;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.TestUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import java.util.TreeSet;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class RequestServiceTest extends BaseAuthTestContainerTest {

    @Inject
    private RequestService requestService;

    @Inject
    private BandService bandService;

    @Inject
    private BlockedBandService blockedBandService;

    @Test
    public void testCreateAdminRequest() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<OwnRequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getOwnRequests(
                HttpRequest.GET("/request/own").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());
        Assertions.assertEquals(TestUtils.getBandId(donAccessToken), requests.getFirst().getBandId());
        Assertions.assertEquals(RegionName.MOSCOW_REGION, requests.getFirst().getBandRegion());
    }

    @Test
    public void testCreateAdminRequestNonExistentRegion() {
        createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        RegionName regionName = RegionName.VIBORGSKY_REGION;

        APIException exception = Assertions.assertThrows(APIException.class, () -> requestService.createRequests(
            HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
            regionName
        ));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getCode());
        Assertions.assertEquals("Банды с регионом = %s не найдено".formatted(regionName.getTitle()), exception.getMessage());
    }

    @Test
    public void testCreateAdminRequestWithNoRegion() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                null
            )
        );

        TreeSet<OwnRequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getOwnRequests(
                HttpRequest.GET("/request/own").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertFalse(requests.isEmpty());
        Assertions.assertEquals(TestUtils.getBandId(donAccessToken), requests.getFirst().getBandId());
        Assertions.assertEquals(RegionName.MOSCOW_REGION, requests.getFirst().getBandRegion());
    }

    @Test
    public void testCreateSoldierRequest() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String soldierAccessToken = createUser("ksimovin@br.ru", UserRoleName.SOLDIER);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<OwnRequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getOwnRequests(
                HttpRequest.GET("/request/own").bearerAuth(soldierAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());
        Assertions.assertEquals(TestUtils.getBandId(donAccessToken), requests.getFirst().getBandId());
        Assertions.assertEquals(RegionName.MOSCOW_REGION, requests.getFirst().getBandRegion());
    }

    @Test
    public void testCreateSoldierRequestNonExistentRegion() {
        createUser("volce.chat@mail.ru", UserRoleName.DON);

        String soldierAccessToken = createUser("ksimovin@br.ru", UserRoleName.SOLDIER);

        RegionName regionName = RegionName.VIBORGSKY_REGION;

        APIException exception = Assertions.assertThrows(APIException.class, () -> requestService.createRequests(
            HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
            regionName
        ));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getCode());
        Assertions.assertEquals("Банды с регионом = %s не найдено".formatted(regionName.getTitle()), exception.getMessage());
    }

    @Test
    public void testCreateSoldierRequestWithNoRegion() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String soldierAccessToken = createUser("ksimovin@br.ru", UserRoleName.SOLDIER);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                null
            )
        );

        TreeSet<OwnRequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getOwnRequests(
                HttpRequest.GET("/request/own").bearerAuth(soldierAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertFalse(requests.isEmpty());
        Assertions.assertEquals(TestUtils.getBandId(donAccessToken), requests.getFirst().getBandId());
        Assertions.assertEquals(RegionName.MOSCOW_REGION, requests.getFirst().getBandRegion());
    }

    @Test
    public void testDeclineAdminRequest() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());

        RequestResponseDTO request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.ADMIN, request.getUserRole());

        Assertions.assertDoesNotThrow(() -> requestService.deleteRequest(request.getId()));

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertTrue(requests.isEmpty());
    }

    @Test
    public void testAcceptAdminRequest() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());

        RequestResponseDTO request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.ADMIN, request.getUserRole());

        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(request.getId()));

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertTrue(requests.isEmpty());

        BandStatsInfoResponseDTO responseDTO = Assertions.assertDoesNotThrow(() -> bandService.selectBandStatsInfo(
                HttpRequest.GET("/band/stats").bearerAuth(donAccessToken)
            )
        );

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(new BandStatsInfoResponseDTO(1, 0, 0, 0, 0), responseDTO);
    }

    @Test
    public void testAcceptAdminRequestForBlockedBand() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());

        RequestResponseDTO request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.ADMIN, request.getUserRole());

        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO(PASSWORD);
        Assertions.assertDoesNotThrow(() -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(donAccessToken)
        ));

        APIException exception = Assertions.assertThrows(APIException.class, () -> requestService.acceptRequest(request.getId()));

        Assertions.assertEquals(HttpStatus.LOCKED, exception.getCode());
        Assertions.assertEquals("База данных заблокирована!", exception.getMessage());

        exception = Assertions.assertThrows(APIException.class, () -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.LOCKED, exception.getCode());
        Assertions.assertEquals("База данных заблокирована!", exception.getMessage());
    }

    @Test
    public void testDeclineSoldierRequest() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO request = requests.getFirst();
        RequestResponseDTO finalRequest = request;
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest.getId()));

        String soldierAccessToken = createUser("volcechatc@gmail.com", UserRoleName.SOLDIER);
        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());

        request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.SOLDIER, request.getUserRole());

        RequestResponseDTO finalRequest1 = request;
        Assertions.assertDoesNotThrow(() -> requestService.deleteRequest(finalRequest1.getId()));

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertTrue(requests.isEmpty());
    }

    @Test
    public void testAcceptSoldierRequest() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO request = requests.getFirst();
        RequestResponseDTO finalRequest = request;
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest.getId()));

        String soldierAccessToken = createUser("volcechatc@gmail.com", UserRoleName.SOLDIER);
        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());

        request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.SOLDIER, request.getUserRole());

        RequestResponseDTO finalRequest1 = request;
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest1.getId()));

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertTrue(requests.isEmpty());

        BandStatsInfoResponseDTO responseDTO = Assertions.assertDoesNotThrow(() -> bandService.selectBandStatsInfo(
                HttpRequest.GET("/band/stats").bearerAuth(donAccessToken)
            )
        );

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(new BandStatsInfoResponseDTO(1, 1, 0, 0, 0), responseDTO);
    }

    @Test
    public void testAcceptSoldierRequestForBlockedBand() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO request = requests.getFirst();
        RequestResponseDTO finalRequest = request;
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest.getId()));

        String soldierAccessToken = createUser("volcechatc@gmail.com", UserRoleName.SOLDIER);
        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());

        request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.SOLDIER, request.getUserRole());

        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO(PASSWORD);
        Assertions.assertDoesNotThrow(() -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(donAccessToken)
        ));

        RequestResponseDTO finalRequest1 = request;
        APIException exception = Assertions.assertThrows(APIException.class, () -> requestService.acceptRequest(finalRequest1.getId()));

        Assertions.assertEquals(HttpStatus.LOCKED, exception.getCode());
        Assertions.assertEquals("База данных заблокирована!", exception.getMessage());

        exception = Assertions.assertThrows(APIException.class, () -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertEquals(HttpStatus.LOCKED, exception.getCode());
        Assertions.assertEquals("База данных заблокирована!", exception.getMessage());
    }

    @Test
    public void testKickAdminFromBand() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO request = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(request.getId()));

        TreeSet<BandWorkerResponseDTO> workers = Assertions.assertDoesNotThrow(
            () -> bandService.getWorkersByType(TestUtils.getBandId(donAccessToken), UserRoleName.ADMIN));

        Assertions.assertNotNull(workers);
        Assertions.assertEquals(1, workers.size());

        BandWorkerResponseDTO worker = workers.getFirst();

        Assertions.assertDoesNotThrow(() -> bandService.kickFromBand(
            worker.getWorkerId(),
            HttpRequest.PUT("/band", null).bearerAuth(donAccessToken)
        ));

        workers = Assertions.assertDoesNotThrow(() -> bandService.getWorkersByType(
                TestUtils.getBandId(donAccessToken),
                UserRoleName.ADMIN
            )
        );
        Assertions.assertNotNull(workers);
        Assertions.assertTrue(workers.isEmpty());
    }

    @Test
    public void testKickAdminFromBlockedBand() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO request = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(request.getId()));

        TreeSet<BandWorkerResponseDTO> workers = Assertions.assertDoesNotThrow(
            () -> bandService.getWorkersByType(TestUtils.getBandId(donAccessToken), UserRoleName.ADMIN));

        Assertions.assertNotNull(workers);
        Assertions.assertEquals(1, workers.size());

        BandWorkerResponseDTO worker = workers.getFirst();

        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO(PASSWORD);
        Assertions.assertDoesNotThrow(() -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(donAccessToken)
        ));

        APIException exception = Assertions.assertThrows(APIException.class, () -> bandService.kickFromBand(
            worker.getWorkerId(),
            HttpRequest.PUT("/band", null).bearerAuth(donAccessToken)
        ));

        Assertions.assertEquals(HttpStatus.LOCKED, exception.getCode());
        Assertions.assertEquals("База данных заблокирована!", exception.getMessage());
    }

    @Test
    public void testKickAdminFromOtherBand() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO request = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(request.getId()));

        TreeSet<BandWorkerResponseDTO> workers = Assertions.assertDoesNotThrow(
            () -> bandService.getWorkersByType(TestUtils.getBandId(donAccessToken), UserRoleName.ADMIN));

        Assertions.assertNotNull(workers);
        Assertions.assertEquals(1, workers.size());

        BandWorkerResponseDTO worker = workers.getFirst();

        String newDonAccessToken = createUser("volce@mail.ru", UserRoleName.DON, RegionName.VASILEOSTROVKIY_REGION);

        APIException exception = Assertions.assertThrows(APIException.class, () -> bandService.kickFromBand(
            worker.getWorkerId(),
            HttpRequest.PUT("/band", null).bearerAuth(newDonAccessToken)
        ));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        Assertions.assertEquals("Банды не совпадают!", exception.getMessage());
    }

    @Test
    public void testKickSoldierByDon() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO finalRequest = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest.getId()));

        String soldierAccessToken = createUser("volcechatc@gmail.com", UserRoleName.SOLDIER);
        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        RequestResponseDTO finalRequest1 = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest1.getId()));

        TreeSet<BandWorkerResponseDTO> workers = Assertions.assertDoesNotThrow(() -> bandService.getWorkersByType(
                TestUtils.getBandId(donAccessToken),
                UserRoleName.SOLDIER
            )
        );

        Assertions.assertNotNull(workers);
        Assertions.assertEquals(1, workers.size());

        BandWorkerResponseDTO worker = workers.getFirst();
        APIException exception = Assertions.assertThrows(APIException.class, () -> bandService.kickFromBand(
            worker.getWorkerId(),
            HttpRequest.PUT("/band", null).bearerAuth(donAccessToken)
        ));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        Assertions.assertEquals("Вы не можете исключить данного человека!", exception.getMessage());
    }

    @Test
    public void testKickSoldier() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO finalRequest = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest.getId()));

        String soldierAccessToken = createUser("volcechatc@gmail.com", UserRoleName.SOLDIER);
        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        RequestResponseDTO finalRequest1 = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest1.getId()));

        UUID bandId = TestUtils.getBandId(donAccessToken);

        TreeSet<BandWorkerResponseDTO> workers = Assertions.assertDoesNotThrow(() -> bandService.getWorkersByType(
                bandId,
                UserRoleName.SOLDIER
            )
        );

        Assertions.assertNotNull(workers);
        Assertions.assertEquals(1, workers.size());

        BandWorkerResponseDTO worker = workers.getFirst();
        Assertions.assertDoesNotThrow(() -> bandService.kickFromBand(
            worker.getWorkerId(),
            HttpRequest.PUT("/band", null).bearerAuth(adminAccessToken)
        ));

        workers = Assertions.assertDoesNotThrow(() -> bandService.getWorkersByType(
                bandId,
                UserRoleName.SOLDIER
            )
        );
        Assertions.assertNotNull(workers);
        Assertions.assertTrue(workers.isEmpty());
    }

    @Test
    public void testKickSoldierFromBlockedBand() {
        String donAccessToken = createUser("volce.chat@mail.ru", UserRoleName.DON);

        String adminAccessToken = createUser("ksimovin@br.ru", UserRoleName.ADMIN);

        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(adminAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(donAccessToken)
            )
        );

        RequestResponseDTO finalRequest = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest.getId()));

        String soldierAccessToken = createUser("volcechatc@gmail.com", UserRoleName.SOLDIER);
        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        RequestResponseDTO finalRequest1 = requests.getFirst();
        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(finalRequest1.getId()));

        UUID bandId = TestUtils.getBandId(donAccessToken);

        TreeSet<BandWorkerResponseDTO> workers = Assertions.assertDoesNotThrow(() -> bandService.getWorkersByType(
                bandId,
                UserRoleName.SOLDIER
            )
        );

        Assertions.assertNotNull(workers);
        Assertions.assertEquals(1, workers.size());

        BlockBandRequestDTO blockBandRequestDTO = new BlockBandRequestDTO(PASSWORD);
        Assertions.assertDoesNotThrow(() -> blockedBandService.blockDb(
            blockBandRequestDTO,
            HttpRequest.POST("/band/block", blockBandRequestDTO).bearerAuth(donAccessToken)
        ));

        BandWorkerResponseDTO worker = workers.getFirst();
        APIException exception = Assertions.assertThrows(APIException.class, () -> bandService.kickFromBand(
            worker.getWorkerId(),
            HttpRequest.PUT("/band", null).bearerAuth(adminAccessToken)
        ));

        Assertions.assertEquals(HttpStatus.LOCKED, exception.getCode());
        Assertions.assertEquals("База данных заблокирована!", exception.getMessage());
    }
}
