package com.rosmat.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("amiworking")
    public String getStatus() {
        return "I am working.";
    }

    @GetMapping
    public String getInfo() {
        return "Simple Data Warehouse";
    }
}
