package com.shadow.deals.band.task.status.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.band.task.status.entity.TaskStatus;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface TaskStatusRepository extends CommonRepository<TaskStatus> {
    Optional<TaskStatus> findByTaskStatus(TaskStatusEnum taskStatus);
}
