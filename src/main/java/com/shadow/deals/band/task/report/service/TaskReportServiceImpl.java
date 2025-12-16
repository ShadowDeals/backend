package com.shadow.deals.band.task.report.service;

import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.task.band.service.BandTaskService;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.band.task.main.service.TaskService;
import com.shadow.deals.band.task.report.dto.request.TaskReportRequestDTO;
import com.shadow.deals.band.task.report.dto.response.TaskReportResponseDTO;
import com.shadow.deals.band.task.report.entity.TaskReport;
import com.shadow.deals.band.task.report.mapper.TaskReportMapper;
import com.shadow.deals.band.task.report.repository.TaskReportRepository;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.band.task.status.service.TaskStatusService;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.service.UserService;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.CommonUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class TaskReportServiceImpl implements TaskReportService {
    private final TaskReportRepository taskReportRepository;

    private final TaskService taskService;

    private final UserService userService;

    private final BlockedBandService blockedBandService;

    private final BandTaskService bandTaskService;

    private final TaskStatusService taskStatusService;

    @Override
    public TaskReport findById(UUID id) {
        return taskReportRepository.findById(id).orElseThrow(() -> new APIException(
                "Отчета с id = %s не найдено".formatted(id),
                HttpStatus.NOT_FOUND));
    }

    @Override
    public List<TaskReport> findAll() {
        return taskReportRepository.findAll();
    }

    @Override
    public TaskReport save(TaskReport entity) {
        return taskReportRepository.save(entity);
    }

    @Override
    public UUID createReport(UUID taskId, TaskReportRequestDTO dto) {
        TaskReport taskReport = TaskReportMapper.INSTANCE.toEntity(dto);
        taskReport.setDateCreated(Instant.now());
        Task task = taskService.findById(taskId);

        TaskStatusEnum reportStatus = dto.getStatus();
        if (reportStatus != TaskStatusEnum.FAILED && reportStatus != TaskStatusEnum.FINISHED) {
            throw new APIException("Передан некорректный статус задания!", HttpStatus.BAD_REQUEST);
        }

        if (task.getStatus().getTaskStatus() != TaskStatusEnum.IN_WORK) {
            throw new APIException("Текущий статус задания не позволяет создать отчет!", HttpStatus.BAD_REQUEST);
        }

        Band band = bandTaskService.findAllByTask(task).getFirst().getBand();
        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        task.getExecutors().forEach(executor -> {
            executor.setTask(null);
            userService.update(executor);
        });

        task.setStatus(taskStatusService.findByTaskStatus(reportStatus));
        taskService.update(task);

        taskReport.setTask(task);
        return save(taskReport).getId();
    }

    @Override
    public TreeSet<TaskReportResponseDTO> getTaskReports(HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        UserRoleName userRoleName = user.getRole().getRoleName();
        if (userRoleName != UserRoleName.ADMIN) {
            return taskService.getOwnTask(request)
                    .stream()
                    .filter(response -> taskReportRepository.existsByTaskId(response.getTaskId()))
                    .map(response -> {
                        TaskReport taskReport = taskReportRepository.findByTaskId(response.getTaskId());
                        return TaskReportMapper.INSTANCE.toResponseDTO(taskReport);
                    })
                    .collect(Collectors.toCollection(TreeSet::new));
        }

        Band band = user.getBand();
        if (band == null) {
            throw new APIException("Нет банды!", HttpStatus.BAD_REQUEST);
        }

        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        return bandTaskService.findAllTasksByBand(band.getId())
                .stream()
                .filter(task -> taskReportRepository.existsByTaskId(task.getId()))
                .map(task -> {
                    TaskReport taskReport = taskReportRepository.findByTaskId(task.getId());
                    return TaskReportMapper.INSTANCE.toResponseDTO(taskReport);
                })
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public TaskReportResponseDTO getTaskReport(UUID taskId) {
        TaskReport taskReport = taskReportRepository.findByTaskId(taskId);
        Band band = bandTaskService.findAllByTask(taskService.findById(taskId)).getFirst().getBand();

        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        return TaskReportMapper.INSTANCE.toResponseDTO(taskReport);
    }
}
