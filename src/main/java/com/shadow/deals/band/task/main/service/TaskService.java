package com.shadow.deals.band.task.main.service;

import com.shadow.deals.band.task.main.dto.request.CancelTaskRequestDTO;
import com.shadow.deals.band.task.main.dto.request.CreateTaskRequestDTO;
import com.shadow.deals.band.task.main.dto.response.TaskResponseDTO;
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

    TreeSet<TaskResponseDTO> getTasksByStatus(UUID bandId, TaskStatusEnum taskStatus, HttpRequest<?> request);

    TreeSet<TaskResponseDTO> getOwnTask(HttpRequest<?> request);

    TaskResponseDTO getTaskById(UUID taskId, HttpRequest<?> request);

    void setExecutors(UUID taskId, UUID officerId, Set<UUID> executorsId);

    TreeSet<FreeExecutorResponseDTO> getFreeExecutors(UUID bandId);

    void deleteById(UUID taskId, HttpRequest<?> request);

    void cancelTask(UUID taskId, CancelTaskRequestDTO cancelTaskRequestDTO, HttpRequest<?> request);

    void updateTaskPrice(UUID taskId, UUID bandId, int price);
}
