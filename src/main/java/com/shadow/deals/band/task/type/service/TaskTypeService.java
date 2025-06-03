package com.shadow.deals.band.task.type.service;

import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.band.task.type.entity.TaskType;
import com.shadow.deals.band.task.type.enums.TaskTypeEnum;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface TaskTypeService extends CommonEntityService<TaskType> {
    TaskType findByTaskType(TaskTypeEnum taskType);
}
