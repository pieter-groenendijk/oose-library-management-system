package com.github.pieter_groenendijk.controller; // Adjust package as needed

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api") // Base URL path
public class AccountController {

    @Operation(summary = "Get a greeting message", description = "Returns a simple greeting")
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    // Add more endpoints here
}