package com.shadow.deals.band.task.status.service;

import com.shadow.deals.exception.APIException;
import com.shadow.deals.band.task.status.entity.TaskStatus;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.band.task.status.repository.TaskStatusRepository;
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
public class TaskStatusServiceImpl implements TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public TaskStatus findByTaskStatus(TaskStatusEnum taskStatus) {
        return taskStatusRepository.findByTaskStatus(taskStatus).orElseThrow(() -> new APIException(
                "Статуса задания с именем = %s не существует".formatted(taskStatus.getTitle()),
                HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public TaskStatus findById(UUID id) {
        return taskStatusRepository.findById(id).orElseThrow(() -> new APIException(
                "Статуса задания с id = %s не существует".formatted(id),
                HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public List<TaskStatus> findAll() {
        return taskStatusRepository.findAll();
    }
}
