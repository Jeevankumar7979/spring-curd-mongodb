package com.jeevankumar.spring_crud_mongodb.repository.impl;

import com.jeevankumar.spring_crud_mongodb.model.UsageDTO;
import com.jeevankumar.spring_crud_mongodb.repository.UsageDaoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class UsageDaoRepositoryImpl implements UsageDaoRepositoryCustom {

    @Autowired
    private  MongoTemplate mongoTemplate;
    @Autowired
    private  RetryTemplate retryTemplate;

    @Override
    public UsageDTO insertUsage(UsageDTO usageDTO) {
      return retryTemplate.execute(retryContext -> {
           usageDTO.setCreatedDate(LocalDate.now());
            return mongoTemplate.insert(usageDTO);
       });

    }

}
