package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlerRuntimeException(RuntimeException ex, Model model) {
        log.error("handlerRuntimeException: message: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "errorPage";
    }
}
