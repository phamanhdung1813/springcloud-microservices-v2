package com.anhdungpham.microservices.restwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private Configuration configuration;

    @GetMapping(path = "/limit")
    public LimitEntity retrieveLimit() {
        return new LimitEntity(configuration.getMinimum(), configuration.getMaximum());
    }
}
