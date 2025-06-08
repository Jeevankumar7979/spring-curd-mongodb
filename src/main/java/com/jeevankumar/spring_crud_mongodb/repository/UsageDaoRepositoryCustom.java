package com.jeevankumar.spring_crud_mongodb.repository;

import com.jeevankumar.spring_crud_mongodb.model.UsageDTO;

import java.util.List;

public interface UsageDaoRepositoryCustom {

    UsageDTO insertUsage(UsageDTO usageDTO);
    List<UsageDTO> getUsageByHhid(String hhId);
    List<UsageDTO> getUsageByHhidAndOfferId(String hhId, String offerId);
}
