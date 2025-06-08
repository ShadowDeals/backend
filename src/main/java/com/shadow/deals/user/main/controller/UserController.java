package com.shadow.deals.user.main.controller;

import com.shadow.deals.user.main.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

/**
 * @author Kirill "Tamada" Simovin
 */
@Controller("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Put("/leave")
    @RolesAllowed({"Администратор", "Солдат"})
    public void leave(HttpRequest<?> request) {
        userService.leaveBand(request);
    }
}
