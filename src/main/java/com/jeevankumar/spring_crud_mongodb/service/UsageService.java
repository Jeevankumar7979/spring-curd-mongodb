package com.jeevankumar.spring_crud_mongodb.service;

import com.jeevankumar.spring_crud_mongodb.dto.UsageRequest;
import com.jeevankumar.spring_crud_mongodb.dto.UsageResponse;
import com.jeevankumar.spring_crud_mongodb.mapper.UsageMapper;
import com.jeevankumar.spring_crud_mongodb.repository.UsageDaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsageService {

    private final UsageDaoRepository repository;
    private final UsageMapper usageMapper;

    public UsageResponse insertUsage(UsageRequest request) {
        log.info("UsageService: insertUsage called with request: {}", request);

        UsageResponse usageResponse = usageMapper.usageDTOToUsageResponse(
                repository.insertUsage(
                        usageMapper.usageRequestToUsageDTO(request))
        );
        log.info("UsageService: insertUsage completed with response: {}", usageResponse);
        return usageResponse;
    }

    public List<UsageResponse> getUsageByHhid(String hhId) {
        log.info("UsageService: getUsageByHhid called with hhId: {}", hhId);
        List<UsageResponse> usageResponses = repository.getUsageByHhid(hhId)
                .stream()
                .map(usageMapper::usageDTOToUsageResponse)
                .toList();
        log.info("UsageService: getUsageByHhid completed with {} responses", usageResponses.size());
        return usageResponses;
    }

    public List<UsageResponse> getUsageByHhidAndOfferId(String hhId, String offerId) {
        log.info("UsageService: getUsageByHhidAndOfferId called with hhId: {}, offerId: {}", hhId, offerId);
        List<UsageResponse> usageResponses = repository.getUsageByHhidAndOfferId(hhId, offerId)
                .stream()
                .map(usageMapper::usageDTOToUsageResponse)
                .toList();
        log.info("UsageService: getUsageByHhidAndOfferId completed with {} responses", usageResponses.size());
        return usageResponses;
    }
}
