package com.avanta.exchanged.service;
import com.avanta.exchanged.dto.ExchangeTypeDto;
import com.avanta.exchanged.response.GetAllExchangeTypesResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeTypeService {

    Flux<GetAllExchangeTypesResponse> loadAllExchangeTypes();
    Mono<ExchangeTypeDto> saveExchangetype(ExchangeTypeDto exchangeTypeDto);
    Mono<ExchangeTypeDto> updateExchangeType(ExchangeTypeDto exchangeTypeDto);
    Mono<Void> deleteExchangeType(String id);
}
