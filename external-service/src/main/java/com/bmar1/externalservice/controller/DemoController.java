package com.bmar1.externalservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b")
public class DemoController {

    @GetMapping
    public String serviceB() {
        return "Content from B";
    }
}
