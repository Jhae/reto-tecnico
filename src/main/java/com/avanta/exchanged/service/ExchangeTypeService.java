package com.avanta.exchanged.service;
import com.avanta.exchanged.dto.ExchangeTypeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeTypeService {

    Flux<ExchangeTypeDto> loadAllExchangeTypes();
    Mono<ExchangeTypeDto> saveExchangetype(ExchangeTypeDto exchangeTypeDto);
    Mono<ExchangeTypeDto> updateExchangeType(ExchangeTypeDto exchangeTypeDto);
    Mono<Void> deleteExchangeType(String id);
}
