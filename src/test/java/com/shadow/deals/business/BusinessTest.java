package com.shadow.deals.business;

import com.shadow.deals.band.main.dto.response.BandStatsInfoResponseDTO;
import com.shadow.deals.band.main.service.BandService;
import com.shadow.deals.band.request.dto.response.RequestResponseDTO;
import com.shadow.deals.band.request.service.RequestService;
import com.shadow.deals.band.task.main.dto.request.CreateTaskRequestDTO;
import com.shadow.deals.band.task.main.dto.response.TaskResponseDTO;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.band.task.main.service.TaskService;
import com.shadow.deals.band.task.report.dto.request.TaskReportRequestDTO;
import com.shadow.deals.band.task.report.dto.response.TaskReportResponseDTO;
import com.shadow.deals.band.task.report.service.TaskReportService;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.band.task.type.enums.TaskTypeEnum;
import com.shadow.deals.base.BaseAuthTestContainerTest;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.TestUtils;
import io.micronaut.http.HttpRequest;
import jakarta.inject.Inject;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class BusinessTest extends BaseAuthTestContainerTest {

    @Inject
    private RequestService requestService;

    @Inject
    private BandService bandService;

    @Inject
    private TaskService taskService;

    @Inject
    private TaskReportService taskReportService;

    @Test
    public void testFullTaskCycle() {
        String donAccessToken = createUser("email1@mail.ru", UserRoleName.DON);
        String adminAccessToken = createUser("email2@mail.ru", UserRoleName.ADMIN);

        acceptAdminToBand(donAccessToken, adminAccessToken);

        String soldier1AccessToken = createUser("email3@mail.ru", UserRoleName.SOLDIER);
        UUID soldier1Id = getUserIdFromAccessToken(soldier1AccessToken);
        acceptSoldierToBand(donAccessToken, adminAccessToken, soldier1AccessToken, 1);

        String soldier2AccessToken = createUser("email4@mail.ru", UserRoleName.SOLDIER);
        UUID soldier2Id = getUserIdFromAccessToken(soldier2AccessToken);
        acceptSoldierToBand(donAccessToken, adminAccessToken, soldier2AccessToken, 2);

        String userAccessToken = createUser("email5@mail.ru", UserRoleName.USER);
        UUID userId = getUserIdFromAccessToken(userAccessToken);

        CreateTaskRequestDTO createTaskRequestDTO = new CreateTaskRequestDTO(
            "Улица Льва Толстого, дом 58",
            "Нужно помочь убраться дома, если вы понимаете о чем я",
            TaskTypeEnum.ROBBERY,
            50000,
            RegionName.MOSCOW_REGION
        );

        UUID taskId = Assertions.assertDoesNotThrow(() -> taskService.createTask(
                createTaskRequestDTO,
                HttpRequest.POST("/task", createTaskRequestDTO).bearerAuth(userAccessToken)
            )
        );

        Task task = Assertions.assertDoesNotThrow(() -> taskService.findById(taskId));
        Assertions.assertNotNull(task);
        Assertions.assertEquals(taskId, task.getId());
        Assertions.assertEquals(task.getAddress(), createTaskRequestDTO.getAddress());
        Assertions.assertEquals(task.getDescription(), createTaskRequestDTO.getDescription());
        Assertions.assertEquals(task.getType().getTaskType(), createTaskRequestDTO.getTaskType());
        Assertions.assertEquals(task.getPrice(), createTaskRequestDTO.getPrice());

        UUID bandId = TestUtils.getBandId(donAccessToken);

        Assertions.assertDoesNotThrow(() -> taskService.updateTaskPrice(
                taskId,
                bandId,
                5010
            )
        );

        task = Assertions.assertDoesNotThrow(() -> taskService.findById(taskId));
        Assertions.assertNotNull(task);
        Assertions.assertEquals(taskId, task.getId());
        Assertions.assertEquals(task.getAddress(), createTaskRequestDTO.getAddress());
        Assertions.assertEquals(task.getDescription(), createTaskRequestDTO.getDescription());
        Assertions.assertEquals(task.getType().getTaskType(), createTaskRequestDTO.getTaskType());
        Assertions.assertEquals(5010, task.getPrice());

        Assertions.assertDoesNotThrow(() -> taskService.payment(
                taskId
            )
        );

        task = Assertions.assertDoesNotThrow(() -> taskService.findById(taskId));
        Assertions.assertEquals(TaskStatusEnum.WAITING_FOR_ASSIGNMENT, task.getStatus().getTaskStatus());

        Assertions.assertDoesNotThrow(() -> taskService.setExecutors(
                taskId,
                soldier1Id,
                Set.of(soldier2Id)
            )
        );

        TaskResponseDTO taskResponseDTO = Assertions.assertDoesNotThrow(() -> taskService.getTaskById(
                taskId,
                HttpRequest.GET("/task").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(task);
        Assertions.assertEquals(taskId, taskResponseDTO.getTaskId());
        Assertions.assertEquals(task.getAddress(), taskResponseDTO.getAddress());
        Assertions.assertEquals(task.getDescription(), taskResponseDTO.getDescription());
        Assertions.assertEquals(task.getType().getTaskType(), taskResponseDTO.getTaskType());
        Assertions.assertEquals(taskResponseDTO.getPrice(), task.getPrice());
        Assertions.assertEquals(taskResponseDTO.getTaskStatus(), task.getStatus().getTaskStatus());
        Assertions.assertEquals(taskResponseDTO.getCustomerId(), userId);
        Assertions.assertEquals(taskResponseDTO.getOfficer().getId(), soldier1Id);
        Assertions.assertEquals(1, taskResponseDTO.getExecutors().size());
        Assertions.assertEquals(taskResponseDTO.getExecutors().getFirst().getId(), soldier2Id);

        Assertions.assertDoesNotThrow(() -> taskService.updateTaskStatus(
                taskId,
                bandId,
                TaskStatusEnum.IN_WORK
            )
        );

        TaskReportRequestDTO taskReportRequestDTO = new TaskReportRequestDTO(
            TaskStatusEnum.FINISHED,
            "Как и просили - помогли убрать все подчистую",
            2
        );

        UUID reportId = Assertions.assertDoesNotThrow(() -> taskReportService.createReport(
                taskId,
                taskReportRequestDTO
            )
        );
        Assertions.assertNotNull(reportId);

        TaskReportResponseDTO reportDTO = Assertions.assertDoesNotThrow(() -> taskReportService.getTaskReport(taskId));
        Assertions.assertNotNull(reportDTO);
        Assertions.assertEquals(reportDTO.getDescription(), taskReportRequestDTO.getDescription());
        Assertions.assertEquals(reportDTO.getTaskStatus(), taskReportRequestDTO.getStatus());
        Assertions.assertEquals(reportDTO.getTimeSpent(), taskReportRequestDTO.getTimeSpent());
        Assertions.assertEquals(reportDTO.getReportId(), reportId);
        Assertions.assertEquals(reportDTO.getTaskId(), taskId);
    }

    private void acceptAdminToBand(String donAccessToken, String adminAccessToken) {
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

    private void acceptSoldierToBand(String donAccessToken, String adminAccessToken, String soldierAccessToken, int soldiersCount) {
        Assertions.assertDoesNotThrow(() -> requestService.createRequests(
                HttpRequest.POST("/request", null).bearerAuth(soldierAccessToken),
                RegionName.MOSCOW_REGION
            )
        );

        TreeSet<RequestResponseDTO> requests = Assertions.assertDoesNotThrow(() -> requestService.getRequests(
                HttpRequest.GET("/request").bearerAuth(adminAccessToken)
            )
        );

        Assertions.assertNotNull(requests);
        Assertions.assertEquals(1, requests.size());

        RequestResponseDTO request = requests.getFirst();

        Assertions.assertEquals("TEST", request.getName());
        Assertions.assertEquals(UserRoleName.SOLDIER, request.getUserRole());

        Assertions.assertDoesNotThrow(() -> requestService.acceptRequest(request.getId()));

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
        Assertions.assertEquals(new BandStatsInfoResponseDTO(1, soldiersCount, 0, 0, 0), responseDTO);
    }
}
