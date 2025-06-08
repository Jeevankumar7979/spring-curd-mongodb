package com.jeevankumar.spring_crud_mongodb.repository.impl;

import com.jeevankumar.spring_crud_mongodb.model.UsageDTO;
import com.jeevankumar.spring_crud_mongodb.repository.UsageDaoRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UsageDaoRepositoryImpl implements UsageDaoRepositoryCustom {


    private final MongoTemplate mongoTemplate;
    private final RetryTemplate retryTemplate;

    @Override
    public UsageDTO insertUsage(UsageDTO usageDTO) {
      return retryTemplate.execute(retryContext -> {
           usageDTO.setCreatedDate(LocalDate.now());
            return mongoTemplate.insert(usageDTO);
       });

    }

    @Override
    public List<UsageDTO> getUsageByHhid(String hhId) {
       log.info("UsageDaoRepositoryImpl: getUsageByHhid called with hhId: {}", hhId);
        Query query = Query.query(Criteria.where("hhId").is(hhId));
        return Optional.ofNullable(retryTemplate)
                .map(
                        template -> template.execute(retryContext -> {
                                    log.debug("UsageDaoRepositoryImpl: Executing query to find usage by hhId: {}", hhId);
                                    return mongoTemplate.find(query, UsageDTO.class);
                                }

                        )
                )
                .orElseGet(() -> {
                            log.error("UsageDaoRepositoryImpl: RetryTemplate is not available, executing query without retry, hhId: {}", hhId);
                            return mongoTemplate.find(query, UsageDTO.class);
                        }
                );
    }

    @Override
    public List<UsageDTO> getUsageByHhidAndOfferId(String hhId, String offerId) {
        log.info("UsageDaoRepositoryImpl: getUsageByHhidAndOfferId called with hhId: {}, offerId: {}", hhId, offerId);
        var query = Query.query(Criteria.where("hhId").is(hhId).and("offerId").is(offerId));
        return Optional.ofNullable(retryTemplate)
                .map(
                        template -> template.execute(retryContext -> {
                                    log.debug("UsageDaoRepositoryImpl: Executing query to find usage by hhId: {}, offerId: {}", hhId, offerId);
                                    return mongoTemplate.find(query, UsageDTO.class);
                                }
                        )
                )
                .orElseGet(() -> {
                            log.error("UsageDaoRepositoryImpl: RetryTemplate is not available, executing query without retry, hhId: {}, offerId: {}", hhId, offerId);
                            return mongoTemplate.find(query, UsageDTO.class);
                        }
                );
    }

}
