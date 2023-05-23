package com.avanta.exchanged.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExchangeHistoryDto {
    private String id;
    private BigDecimal originAmount;
    private BigDecimal destinyAmount;
    private BigDecimal exchangeRate;
    private Date operationDate;
    private String destinyCurrency;
    private String originCurrency;
    private String user;
}
