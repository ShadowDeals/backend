package com.shadow.deals.band.task.report.controller;

import com.shadow.deals.band.task.report.dto.request.TaskReportRequestDTO;
import com.shadow.deals.band.task.report.dto.response.TaskReportResponseDTO;
import com.shadow.deals.band.task.report.service.TaskReportService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import java.util.TreeSet;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Controller("/task/report")
@RequiredArgsConstructor
public class TaskReportController {
    private final TaskReportService taskReportService;

    @Post
    @RolesAllowed({"Солдат"})
    public MutableHttpResponse<UUID> createTaskReport(@QueryValue("taskId") UUID taskId, @Body TaskReportRequestDTO dto) {
        return HttpResponse.status(HttpStatus.CREATED).body(taskReportService.createReport(taskId, dto));
    }

    @Get("/all")
    @RolesAllowed({"Администратор", "Солдат", "Пользователь"})
    public TreeSet<TaskReportResponseDTO> findTaskReports(HttpRequest<?> request) {
        return taskReportService.getTaskReports(request);
    }

    @Get
    @RolesAllowed({"Администратор", "Солдат"})
    public TaskReportResponseDTO findTaskReport(@QueryValue("reportId") UUID reportId) {
        return taskReportService.getTaskReport(reportId);
    }
}
