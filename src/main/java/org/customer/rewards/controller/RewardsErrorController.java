package org.customer.rewards.controller;

import org.customer.rewards.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsErrorController implements ErrorController {

    @RequestMapping("/error")
    public ErrorResponse handleError(HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, "API not found");
        return errorResponse;
    }
}
