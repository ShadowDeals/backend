package com.shadow.deals.band.task.main.controller;

import com.shadow.deals.band.task.dto.request.CreateTaskRequestDTO;
import com.shadow.deals.band.task.dto.response.TaskResponseDTO;
import com.shadow.deals.band.task.main.service.TaskService;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.user.main.dto.response.FreeExecutorResponseDTO;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Controller("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Post
    @RolesAllowed({"Пользователь"})
    public MutableHttpResponse<UUID> createTask(@Body CreateTaskRequestDTO createTaskDTO, HttpRequest<?> request) {
        return HttpResponse.status(HttpStatus.CREATED).body(taskService.createTask(createTaskDTO, request));
    }

    @Put
    @RolesAllowed({"Администратор", "Солдат"})
    public void updateTaskStatus(@QueryValue(value = "taskId") UUID taskId, @QueryValue(value = "bandId") UUID bandId, @QueryValue(value = "taskStatus") TaskStatusEnum taskStatus) {
        taskService.updateTaskStatus(taskId, bandId, taskStatus);
    }

    @Get
    @RolesAllowed({"Дон", "Администратор"})
    public TreeSet<TaskResponseDTO> getTasksByStatus(@QueryValue(value = "bandId") UUID bandId, @QueryValue(value = "taskStatus") TaskStatusEnum taskStatus) {
        return taskService.getTasksByStatus(bandId, taskStatus);
    }

    @Post("/executors")
    @RolesAllowed({"Администратор"})
    public void setExecutors(@QueryValue(value = "taskId") UUID taskId, @Body Set<UUID> executorsId) {
        taskService.setExecutors(taskId, executorsId);
    }

    @Get("/executors")
    @RolesAllowed({"Администратор"})
    public TreeSet<FreeExecutorResponseDTO> getFreeExecutors(@QueryValue(value = "bandId") UUID bandId) {
        return taskService.getFreeExecutors(bandId);
    }
}