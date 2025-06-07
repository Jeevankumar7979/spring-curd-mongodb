package com.jeevankumar.spring_crud_mongodb.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsageRequest {

    private String hhId;
    private String offerId;
    private Long count;
    private String storeId;
    private String transactionId;
    private LocalDate transactionTimeStamp;
    private String status;
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;
    private LocalDate usageDate;
    private LocalDate offerEndDate;
    private String extOfferId;
    private Integer laneNumber;
    private String source;
    private List<String> clipIds;
    private Integer earnPoints;
}
