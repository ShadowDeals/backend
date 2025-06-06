package com.shadow.deals.band.task.main.service;

import com.shadow.deals.band.task.dto.request.CreateTaskRequestDTO;
import com.shadow.deals.band.task.dto.response.TaskResponseDTO;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.user.main.dto.response.FreeExecutorResponseDTO;
import io.micronaut.http.HttpRequest;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface TaskService extends CommonEntityService<Task> {
    UUID createTask(CreateTaskRequestDTO createTaskDTO, HttpRequest<?> request);

    void updateTaskStatus(UUID taskId, UUID bandId, TaskStatusEnum taskStatus);

    TreeSet<TaskResponseDTO> getTasksByStatus(UUID bandId, TaskStatusEnum taskStatus);

    void setExecutors(UUID taskId, Set<UUID> executorsId);

    TreeSet<FreeExecutorResponseDTO> getFreeExecutors(UUID bandId);
}
