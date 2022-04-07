package com.rosmat.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("amiworking")
    public String getStatus() {
        return "I am working.";
    }
}
