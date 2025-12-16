package com.shadow.deals.band.task.report.repository;

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
        SELECT r FROM TaskReport r WHERE r.task.id = :taskId
        """)
    TaskReport findByTaskId(UUID taskId);

    @Query(value = """
        SELECT COUNT(r) > 0 FROM TaskReport r WHERE r.task.id = :taskId
        """)
    boolean existsByTaskId(UUID taskId);
}
