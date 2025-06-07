package com.jeevankumar.spring_crud_mongodb.controller;

import com.jeevankumar.spring_crud_mongodb.dto.UsageRequest;
import com.jeevankumar.spring_crud_mongodb.dto.UsageResponse;
import com.jeevankumar.spring_crud_mongodb.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UsageController {
    @Autowired
    private  UsageService usageService;

    @MutationMapping
    public UsageResponse insertUsage(@Argument UsageRequest usageRequest) {
        return usageService.insertUsage(usageRequest);
    }

    @QueryMapping
    public String healthCheck() {
        return "OK";
    }
}
