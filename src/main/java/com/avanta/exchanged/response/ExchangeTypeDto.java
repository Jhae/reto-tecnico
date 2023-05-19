package com.avanta.exchanged.response;

import com.avanta.exchanged.entity.Currency;
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
    private Long id;
    private BigDecimal rate;
    private CurrencyDto originCurrency;
    private CurrencyDto destinyCurrency;
}
