package com.shadow.deals.band.task.type.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.band.task.type.entity.TaskType;
import com.shadow.deals.band.task.type.enums.TaskTypeEnum;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface TaskTypeRepository extends CommonRepository<TaskType> {
    Optional<TaskType> findByTaskType(TaskTypeEnum taskType);
}
