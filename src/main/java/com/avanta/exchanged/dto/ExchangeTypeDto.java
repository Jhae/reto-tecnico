package com.avanta.exchanged.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExchangeTypeDto {
    private String id;
    private BigDecimal rate;
    private CurrencyDto originCurrency;
    private CurrencyDto destinyCurrency;
}
