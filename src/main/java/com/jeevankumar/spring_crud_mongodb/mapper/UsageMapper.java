package com.jeevankumar.spring_crud_mongodb.mapper;

import com.jeevankumar.spring_crud_mongodb.dto.UsageRequest;
import com.jeevankumar.spring_crud_mongodb.dto.UsageResponse;
import com.jeevankumar.spring_crud_mongodb.model.UsageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsageMapper {

    UsageDTO usageRequestToUsageDTO(UsageRequest usageRequest);
    UsageResponse usageDTOToUsageResponse(UsageDTO usageDTO);
}
