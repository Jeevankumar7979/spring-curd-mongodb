package com.jeevankumar.spring_crud_mongodb.repository;

import com.jeevankumar.spring_crud_mongodb.model.UsageDTO;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsageDaoRepository extends MongoRepository<UsageDTO,String> ,
        UsageDaoRepositoryCustom {

}
