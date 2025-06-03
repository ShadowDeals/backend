package com.shadow.deals.band.task.main.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.band.task.main.entity.Task;
import io.micronaut.data.annotation.Repository;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface TaskRepository extends CommonRepository<Task> {
}
