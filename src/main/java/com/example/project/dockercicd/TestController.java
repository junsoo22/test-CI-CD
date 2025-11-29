package com.example.project.dockercicd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final String version="v1.0.6";

    @GetMapping("/test")
    public String home() {
        return "Hello CICD with Docker! version: "+version;
    }
}
