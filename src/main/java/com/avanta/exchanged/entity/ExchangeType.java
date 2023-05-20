package com.avanta.exchanged.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "exchange_type")
public class ExchangeType {
    @Id
    private Long id;
    private BigDecimal rate;
    @DBRef(lazy = false)
    private Currency originCurrency;
    @DBRef(lazy = false)
    private Currency destinyCurrency;

}
