package com.jeevankumar.spring_crud_mongodb.controller;

import com.jeevankumar.spring_crud_mongodb.dto.UsageRequest;
import com.jeevankumar.spring_crud_mongodb.dto.UsageResponse;
import com.jeevankumar.spring_crud_mongodb.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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

    @QueryMapping
    public List<UsageResponse> getUsageByHhid(@Argument String hhId) {
        if (StringUtils.isBlank(hhId)){
            throw new IllegalArgumentException("hhId cannot be null or empty");
        }
        return usageService.getUsageByHhid(hhId);
    }
    @QueryMapping
    public List<UsageResponse> getUsageByHhidAndOfferId(@Argument String hhId, @Argument String offerId) {
        if (StringUtils.isAnyBlank(hhId, offerId)) {
            throw new IllegalArgumentException("hhId and offerId cannot be null or empty");
        }
        return usageService.getUsageByHhidAndOfferId(hhId, offerId);
    }
}
