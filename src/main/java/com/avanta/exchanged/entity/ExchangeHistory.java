package com.avanta.exchanged.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

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
    @Field(targetType = FieldType.OBJECT_ID)
    private String destinyCurrency;
    @Field(targetType = FieldType.OBJECT_ID)
    private String originCurrency;
    @Field(targetType = FieldType.OBJECT_ID)
    private String user;
}
