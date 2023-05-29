package com.avanta.exchanged.service;

import com.avanta.exchanged.dto.ExchangeHistoryDto;

import com.avanta.exchanged.entity.aux.GetExchangesHistoryNtt;
import com.avanta.exchanged.request.DoExchangeRequest;
import com.avanta.exchanged.response.GetExchangeHistoryResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeHistoryService{

    Mono<ExchangeHistoryDto> doExchange(DoExchangeRequest requestBody);
    Flux<GetExchangeHistoryResponse> loadExchangesHistoryWithCurrencyAndUser();

}
