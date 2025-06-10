package com.shadow.deals.band.blocked.controller;

import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.blocked.service.BlockedBandService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

/**
 * @author Kirill "Tamada" Simovin
 */
@Controller("/band/block")
@RequiredArgsConstructor
public class BlockedBandController {
    private final BlockedBandService blockedBandService;

    @Get
    @RolesAllowed({"Дон"})
    public boolean getDbState(HttpRequest<?> request) {
        return blockedBandService.getDbState(request);
    }

    @Post
    @RolesAllowed({"Дон"})
    public void blockDb(@Body BlockBandRequestDTO blockBandRequestDTO, HttpRequest<?> request) {
        blockedBandService.blockDb(blockBandRequestDTO, request);
    }

    @Delete
    @RolesAllowed({"Дон"})
    public void unblockDb(@Body BlockBandRequestDTO blockBandRequestDTO, HttpRequest<?> request) {
        blockedBandService.unblockDb(blockBandRequestDTO, request);
    }
}
