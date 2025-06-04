package com.shadow.deals.band.task.report.repository;

import com.shadow.deals.band.task.report.entity.TaskReport;
import com.shadow.deals.base.repository.CommonRepository;
import io.micronaut.data.annotation.Repository;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface TaskReportRepository extends CommonRepository<TaskReport> {
}
