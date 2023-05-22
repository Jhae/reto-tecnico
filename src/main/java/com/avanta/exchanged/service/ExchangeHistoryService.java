package com.avanta.exchanged.service;

import com.avanta.exchanged.dto.ExchangeHistoryDto;

import com.avanta.exchanged.request.DoExchangeRequest;
import reactor.core.publisher.Mono;

public interface ExchangeHistoryService{

    Mono<ExchangeHistoryDto> doExchange(DoExchangeRequest requestBody);
}
