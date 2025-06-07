package com.jeevankumar.spring_crud_mongodb.service;

import com.jeevankumar.spring_crud_mongodb.dto.UsageRequest;
import com.jeevankumar.spring_crud_mongodb.dto.UsageResponse;
import com.jeevankumar.spring_crud_mongodb.mapper.UsageMapper;
import com.jeevankumar.spring_crud_mongodb.repository.UsageDaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsageService {
    @Autowired
    private UsageDaoRepository usageDaoRepository;
    @Autowired
    private  UsageMapper usageMapper;

    public UsageResponse insertUsage(UsageRequest usageRequest) {
        log.info("UsageService: insertUsage called with request: {}", usageRequest);

        UsageResponse usageResponse = usageMapper.usageDTOToUsageResponse(
                usageDaoRepository.insertUsage(
                        usageMapper.usageRequestToUsageDTO(usageRequest))
        );
        log.info("UsageService: insertUsage completed with response: {}", usageResponse);
        return usageResponse;
    }
}
