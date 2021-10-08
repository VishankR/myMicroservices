package com.vishank.microservices.limitsService.contoller;

import com.vishank.microservices.limitsService.bean.Limits;
import com.vishank.microservices.limitsService.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @Autowired
    private Configuration config;
    @GetMapping("/limits")
    public Limits  retrieveLimits(){
        return new Limits(config.getMinimum(), config.getMaximum());
    }
}
