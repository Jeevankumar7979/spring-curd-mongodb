package com.jeevankumar.spring_crud_mongodb.repository;

import com.jeevankumar.spring_crud_mongodb.model.UsageDTO;

public interface UsageDaoRepositoryCustom {

    UsageDTO insertUsage(UsageDTO usageDTO);
}
