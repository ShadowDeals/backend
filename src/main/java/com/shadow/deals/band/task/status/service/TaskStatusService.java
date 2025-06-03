package com.shadow.deals.band.task.status.service;

import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.band.task.status.entity.TaskStatus;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface TaskStatusService extends CommonEntityService<TaskStatus> {
    TaskStatus findByTaskStatus(TaskStatusEnum taskStatus);
}
