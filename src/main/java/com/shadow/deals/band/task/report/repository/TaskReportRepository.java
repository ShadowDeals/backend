package com.shadow.deals.band.task.report.repository;

import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.band.task.report.entity.TaskReport;
import com.shadow.deals.base.repository.CommonRepository;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;

import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface TaskReportRepository extends CommonRepository<TaskReport> {
    @Query(value = """
            SELECT * FROM sd_task_report WHERE task_id = :taskId;
            """,
            nativeQuery = true)
    TaskReport findByTaskId(UUID taskId);

    @Query(value = """
            SELECT COUNT(*) > 0 FROM sd_task_report WHERE task_id = :taskId;
            """,
            nativeQuery = true)
    boolean existsByTaskId(UUID taskId);
}
