package com.lazy.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LazyRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(LazyRestApplication.class, args);
    }
} 