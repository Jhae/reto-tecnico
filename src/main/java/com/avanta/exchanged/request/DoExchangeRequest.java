package com.avanta.exchanged.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DoExchangeRequest {

    private String exchangeId;
    private BigDecimal originAmount;
}
