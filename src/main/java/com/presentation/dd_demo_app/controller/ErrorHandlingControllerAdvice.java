package com.presentation.dd_demo_app.controller;

import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ERROR;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricTag.TYPE;
import static lombok.AccessLevel.PRIVATE;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.persistence.EntityNotFoundException;
import java.net.SocketTimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@Slf4j
@ControllerAdvice
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ErrorHandlingControllerAdvice {

    MeterRegistry meterRegistry;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        MDC.put("http.status_code", "404");
        MDC.put("dd.error", "true");
        MDC.put("status", "error");
        log.error("Entity not found: {}", ex.getMessage(), ex);
        meterRegistry.counter(ERROR, TYPE, ex.getClass().getSimpleName()).increment();
        MDC.clear();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex, WebRequest request) {
        MDC.put("http.status_code", "400");
        MDC.put("dd.error", "true");
        MDC.put("status", "error");
        log.error("Bad request: {}", ex.getMessage(), ex);
        meterRegistry.counter(ERROR, TYPE, ex.getClass().getSimpleName()).increment();
        MDC.clear();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<String> handleServerError(SocketTimeoutException ex, WebRequest request) {
        MDC.put("http.status_code", "500");
        MDC.put("dd.error", "true");
        MDC.put("status", "error");
        log.error("Read timeout exception: {}", ex.getMessage(), ex);
        meterRegistry.counter(ERROR, TYPE, ex.getClass().getSimpleName()).increment();
        MDC.clear();
        return new ResponseEntity<>("Read timeout exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleServerError(Exception ex, WebRequest request) {
        MDC.put("http.status_code", "500");
        MDC.put("dd.error", "true");
        MDC.put("status", "error");
        log.error("Internal server error: {}", ex.getMessage(), ex);
        meterRegistry.counter(ERROR, TYPE, ex.getClass().getSimpleName()).increment();
        MDC.clear();
        return new ResponseEntity<>("An internal error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

