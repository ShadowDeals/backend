package com.shadow.deals.band.main.controller;

import com.shadow.deals.band.main.service.BandService;
import com.shadow.deals.user.main.dto.response.BandWorkerResponseDTO;
import com.shadow.deals.user.role.enums.UserRoleName;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
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
@Controller("/band")
@RequiredArgsConstructor
public class BandController {
    private final BandService bandService;

    @Get("/workers")
    @RolesAllowed({"Дон", "Администратор"})
    public TreeSet<BandWorkerResponseDTO> getWorkersByType(@QueryValue(value = "bandId") UUID bandId, @QueryValue(value = "userRole") UserRoleName userRole) {
        return bandService.getWorkersByType(bandId, userRole);
    }

    @Put("/kick")
    @RolesAllowed({"Дон", "Администратор"})
    public void kickFromBand(@QueryValue(value = "userId") UUID userId, HttpRequest<?> request) {
        bandService.kickFromBand(userId, request);
    }
}
