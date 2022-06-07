package com.bmar1.resilientservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private RestTemplate restTemplate;

    int count=1;

    @GetMapping(value = "cb")
    @CircuitBreaker(name = "viaCircuitBreaker", fallbackMethod = "serviceCBFallback")
    public String serviceCB() {
        return restTemplate.getForObject("http://localhost:8082/b", String.class);
    }

    @GetMapping(value = "retry")
    @Retry(name = "viaRetry")
    public String serviceRetry() {
        System.out.println("Retry method called " + count++ + " times at " + new Date());
        return restTemplate.getForObject("http://localhost:8082/b", String.class);
    }

    public String serviceCBFallback(Exception e) {
        return "This is a fallback method for Service A";
    }
}
