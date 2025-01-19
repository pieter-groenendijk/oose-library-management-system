package com.github.pieter_groenendijk;

import com.github.pieter_groenendijk.model.Reservation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.pieter_groenendijk")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
