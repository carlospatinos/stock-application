package com.carlospatinos.paymentservice.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

@ControllerAdvice
public class NotificationExceptionHandler {
    @ExceptionHandler({ CallNotPermittedException.class })
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void handle() {
    }
}
