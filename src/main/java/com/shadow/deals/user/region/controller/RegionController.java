package com.shadow.deals.user.region.controller;

import com.shadow.deals.user.region.service.RegionService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;

import java.util.TreeSet;

/**
 * @author Kirill "Tamada" Simovin
 */
@Controller("/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @Get
    @PermitAll
    public TreeSet<String> getRegions(@QueryValue(value = "is_band_exist") boolean isBandExist) {
        return regionService.getRegions(isBandExist);
    }
}