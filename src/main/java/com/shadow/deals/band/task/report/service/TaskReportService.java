package com.shadow.deals.band.task.report.service;

import com.shadow.deals.band.task.report.dto.request.TaskReportRequestDTO;
import com.shadow.deals.band.task.report.entity.TaskReport;
import com.shadow.deals.base.service.CommonEntityService;

import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface TaskReportService extends CommonEntityService<TaskReport> {
    UUID createReport(UUID taskId, TaskReportRequestDTO dto);
}
