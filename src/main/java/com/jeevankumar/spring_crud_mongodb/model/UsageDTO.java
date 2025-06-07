package com.jeevankumar.spring_crud_mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "usage")
public class UsageDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 56890987654L;

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
