package com.shadow.deals.band.task.report.service;

import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.band.task.main.service.TaskService;
import com.shadow.deals.band.task.report.dto.request.TaskReportRequestDTO;
import com.shadow.deals.band.task.report.entity.TaskReport;
import com.shadow.deals.band.task.report.mapper.TaskReportMapper;
import com.shadow.deals.band.task.report.repository.TaskReportRepository;
import com.shadow.deals.exception.APIException;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class TaskReportServiceImpl implements TaskReportService {
    private final TaskReportRepository taskReportRepository;

    private final TaskService taskService;

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
        Task task = taskService.findById(taskId);
        taskReport.setTask(task);
        return save(taskReport).getId();
    }
}
