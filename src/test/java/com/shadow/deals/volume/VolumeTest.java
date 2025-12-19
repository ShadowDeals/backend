package com.shadow.deals.volume;

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
import com.shadow.deals.util.TestUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
class VolumeTest extends BaseAuthTestContainerTest {

    @Inject
    private TaskService taskService;

    @Inject
    private TaskReportService taskReportService;

    @Test
    void testTaskCreation() {
        Assertions.assertTrue(initDb());

        String customerAccessToken = authUser("mail950@mail.ru");
        CreateTaskRequestDTO createTaskRequestDTO = new CreateTaskRequestDTO(
            "Улица Льва Толстого, дом 58",
            "Нужно помочь убраться дома, если вы понимаете о чем я",
            TaskTypeEnum.ROBBERY,
            50000,
            RegionName.MOSCOW_REGION
        );

        UUID taskId = Assertions.assertDoesNotThrow(() -> taskService.createTask(
                createTaskRequestDTO,
                HttpRequest.POST("/task", createTaskRequestDTO).bearerAuth(customerAccessToken)
            )
        );

        Task task = Assertions.assertDoesNotThrow(() -> taskService.findById(taskId));
        Assertions.assertNotNull(task);
        Assertions.assertEquals(taskId, task.getId());
        Assertions.assertEquals(task.getAddress(), createTaskRequestDTO.getAddress());
        Assertions.assertEquals(task.getDescription(), createTaskRequestDTO.getDescription());
        Assertions.assertEquals(task.getType().getTaskType(), createTaskRequestDTO.getTaskType());
        Assertions.assertEquals(task.getPrice(), createTaskRequestDTO.getPrice());

        String donAccessToken = authUser("mail1@mail.ru");
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

        String soldier1AccessToken = authUser("mail300@mail.ru");
        UUID soldier1Id = getUserIdFromAccessToken(soldier1AccessToken);

        String soldier2AccessToken = authUser("mail301@mail.ru");
        UUID soldier2Id = getUserIdFromAccessToken(soldier2AccessToken);

        Assertions.assertDoesNotThrow(() -> taskService.setExecutors(
                taskId,
                soldier1Id,
                Set.of(soldier2Id)
            )
        );

        String adminAccessToken = authUser("mail12@mail.ru");
        TaskResponseDTO taskResponseDTO = Assertions.assertDoesNotThrow(() -> taskService.getTaskById(
                taskId,
                HttpRequest.GET("/task").bearerAuth(adminAccessToken)
            )
        );

        UUID customerId = getUserIdFromAccessToken(customerAccessToken);

        Assertions.assertNotNull(task);
        Assertions.assertEquals(taskId, taskResponseDTO.getTaskId());
        Assertions.assertEquals(task.getAddress(), taskResponseDTO.getAddress());
        Assertions.assertEquals(task.getDescription(), taskResponseDTO.getDescription());
        Assertions.assertEquals(task.getType().getTaskType(), taskResponseDTO.getTaskType());
        Assertions.assertEquals(taskResponseDTO.getPrice(), task.getPrice());
        Assertions.assertEquals(taskResponseDTO.getTaskStatus(), task.getStatus().getTaskStatus());
        Assertions.assertEquals(taskResponseDTO.getCustomerId(), customerId);
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

    private boolean initDb() {
        try (InputStream is = VolumeTest.class.getResourceAsStream("/volume-mock.sql")) {
            Assertions.assertNotNull(is);

            String sqlScript = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

            String[] statements = sqlScript.split(";");

            try (Statement statement = getConnection().createStatement()) {
                for (String sql : statements) {
                    statement.execute(sql + ";");
                }
            } catch (SQLException e) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private String authUser(String email) {
        return createUser(email, null, null);
    }
}
