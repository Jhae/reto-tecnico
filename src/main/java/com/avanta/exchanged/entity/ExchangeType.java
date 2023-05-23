package com.avanta.exchanged.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "exchange_type")
public class ExchangeType {
    @Id
    private String id;
    private BigDecimal rate;
    @Field(targetType = FieldType.OBJECT_ID)
    private String originCurrency;
    @Field(targetType = FieldType.OBJECT_ID)
    private String destinyCurrency;

}
