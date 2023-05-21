package com.avanta.exchanged.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "exchange_history")
public class ExchangeHistory {
    @Id
    private String id;
    private BigDecimal originAmount;
    private BigDecimal destinyAmount;
    private BigDecimal exchangeRate;
    private Date operationDate;
    private Currency destinyCurrency;
    private Currency originCurrency;
    private User user;
}
