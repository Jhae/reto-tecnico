package com.avanta.exchanged.dto;

import com.avanta.exchanged.entity.Currency;
import com.avanta.exchanged.entity.User;
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
    private CurrencyDto destinyCurrency;
    private CurrencyDto originCurrency;
    private UserDto user;
}
