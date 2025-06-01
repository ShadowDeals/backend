package com.shadow.deals.band.request.controller;

import com.shadow.deals.band.request.dto.response.RequestResponseDTO;
import com.shadow.deals.band.request.service.RequestService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import java.util.TreeSet;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Controller("/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @Get
    @RolesAllowed({"Дон", "Администратор"})
    public TreeSet<RequestResponseDTO> getRequests(HttpRequest<?> request) {
        return requestService.getRequests(request);
    }

    @Delete
    @RolesAllowed({"Дон", "Администратор"})
    public void deleteRequest(@QueryValue(value = "requestId") UUID requestId) {
        requestService.deleteRequest(requestId);
    }

    @Put
    @RolesAllowed({"Дон", "Администратор"})
    public void acceptRequest(@QueryValue(value = "requestId") UUID requestId) {
        requestService.acceptRequest(requestId);
    }
}
