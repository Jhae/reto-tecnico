package com.avanta.exchanged.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "exchange_history")
public class ExchangeHistory {
    @Id
    private Long id;
    private BigDecimal originAmount;
    private BigDecimal destinyAmount;
    private BigDecimal exchangeRate;
    private Date operationDate;
    @DBRef(lazy = false)
    private Currency destinyCurrency;
    @DBRef(lazy = false)
    private Currency originCurrency;
    @DBRef(lazy = false)
    private User user;
}
