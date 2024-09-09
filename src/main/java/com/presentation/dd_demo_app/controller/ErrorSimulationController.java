package com.presentation.dd_demo_app.controller;


import static lombok.AccessLevel.PRIVATE;

import io.netty.handler.timeout.ReadTimeoutException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/errors")
@RestController
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ErrorSimulationController {

    @GetMapping("/not-found")
    public ResponseEntity<String> simulateNotFound() {
        throw new EntityNotFoundException("Requested entity not found");
    }

    @GetMapping("/bad-request")
    public ResponseEntity<String> simulateBadRequest() {
        throw new IllegalArgumentException("Invalid request parameters");
    }

    @GetMapping("/read-timeout")
    public ResponseEntity<String> simulateReadTimeout() {
        throw new ReadTimeoutException("Read timeout exception");
    }

    @GetMapping("/server-error")
    public ResponseEntity<String> simulateServerError() {
        throw new RuntimeException("Internal server error occurred");
    }


}
