package com.avanta.exchanged.service;

import com.avanta.exchanged.entity.ExchangeType;
import reactor.core.publisher.Flux;

public interface ExchangeTypeService {

    Flux<ExchangeType> loadAllExchangeTypes();
}
