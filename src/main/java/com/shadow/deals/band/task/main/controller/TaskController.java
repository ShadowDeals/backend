package com.shadow.deals.band.task.main.controller;

import com.shadow.deals.band.task.main.dto.request.CancelTaskRequestDTO;
import com.shadow.deals.band.task.main.dto.request.CreateTaskRequestDTO;
import com.shadow.deals.band.task.main.dto.response.TaskResponseDTO;
import com.shadow.deals.band.task.main.service.TaskService;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.user.main.dto.response.FreeExecutorResponseDTO;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
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

    @Put("/price")
    @RolesAllowed({"Администратор"})
    public void updateTaskPrice(@QueryValue(value = "taskId") UUID taskId, @QueryValue(value = "bandId") UUID bandId, @QueryValue(value = "price") int price) {
        taskService.updateTaskPrice(taskId, bandId, price);
    }

    @Put("/cancel")
    @RolesAllowed({"Администратор", "Пользователь"})
    public void cancelTask(@QueryValue(value = "taskId") UUID taskId, @Body CancelTaskRequestDTO cancelTaskRequestDTO, HttpRequest<?> request) {
        taskService.cancelTask(taskId, cancelTaskRequestDTO, request);
    }

    @Get
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public TreeSet<TaskResponseDTO> getTasksByStatus(@QueryValue(value = "bandId", defaultValue = "null") UUID bandId, @QueryValue(value = "taskStatus") TaskStatusEnum taskStatus, HttpRequest<?> request) {
        return taskService.getTasksByStatus(bandId, taskStatus, request);
    }

    @Get("/one")
    @RolesAllowed({"Дон", "Администратор"})
    public TaskResponseDTO getTask(@QueryValue(value = "taskId") UUID taskId, HttpRequest<?> request) {
        return taskService.getTaskById(taskId, request);
    }

    @Delete
    @RolesAllowed({"Дон", "Администратор"})
    public void deleteTask(@QueryValue(value = "taskId") UUID taskId, HttpRequest<?> request) {
        taskService.deleteById(taskId, request);
    }

    @Get("/own")
    @RolesAllowed({"Солдат", "Пользователь"})
    public TreeSet<TaskResponseDTO> getOwnTask(HttpRequest<?> request) {
        return taskService.getOwnTask(request);
    }

    @Post("/executors")
    @RolesAllowed({"Администратор"})
    public void setExecutors(@QueryValue(value = "taskId") UUID taskId, @QueryValue(value = "officerId") UUID officerId, @Body Set<UUID> executorsId) {
        taskService.setExecutors(taskId, officerId, executorsId);
    }

    @Get("/executors")
    @RolesAllowed({"Администратор"})
    public TreeSet<FreeExecutorResponseDTO> getFreeExecutors(@QueryValue(value = "bandId") UUID bandId) {
        return taskService.getFreeExecutors(bandId);
    }
}