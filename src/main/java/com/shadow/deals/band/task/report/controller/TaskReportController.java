package com.shadow.deals.band.task.report.controller;

import com.shadow.deals.band.task.report.dto.request.TaskReportRequestDTO;
import com.shadow.deals.band.task.report.service.TaskReportService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Controller("/task/report")
@RequiredArgsConstructor
public class TaskReportController {
    private final TaskReportService taskReportService;

    @Post
    @Secured(SecurityRule.IS_AUTHENTICATED)
//    @RolesAllowed({"Солдат"})
    public MutableHttpResponse<UUID> createTaskReport(@QueryValue("taskId") UUID taskId, @Body TaskReportRequestDTO dto) {
        return HttpResponse.status(HttpStatus.CREATED).body(taskReportService.createReport(taskId, dto));
    }
}
