package com.shadow.deals.band.task.type.service;

import com.shadow.deals.exception.APIException;
import com.shadow.deals.band.task.type.entity.TaskType;
import com.shadow.deals.band.task.type.enums.TaskTypeEnum;
import com.shadow.deals.band.task.type.repository.TaskTypeRepository;
import io.micronaut.data.annotation.Repository;
import io.micronaut.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
@RequiredArgsConstructor
public class TaskTypeServiceImpl implements TaskTypeService {
    private final TaskTypeRepository taskTypeRepository;

    @Override
    public TaskType findByTaskType(TaskTypeEnum taskType) {
        return taskTypeRepository.findByTaskType(taskType).orElseThrow(() -> new APIException(
                "Типа задания с именем = %s не существует".formatted(taskType.getTitle()),
                HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public TaskType findById(UUID id) {
        return taskTypeRepository.findById(id).orElseThrow(() -> new APIException(
                "Типа задания с id = %s не существует".formatted(id),
                HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public List<TaskType> findAll() {
        return taskTypeRepository.findAll();
    }
}
