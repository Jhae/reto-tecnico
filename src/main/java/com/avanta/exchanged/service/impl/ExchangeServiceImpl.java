package com.avanta.exchanged.service.impl;

import com.avanta.exchanged.entity.ExchangeType;
import com.avanta.exchanged.repository.ExchangeTypeRepository;
import com.avanta.exchanged.service.ExchangeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeTypeService {

    private final ExchangeTypeRepository exchangeTypeRepository;

    @Override
    public Flux<ExchangeType> loadAllExchangeTypes() {

        return exchangeTypeRepository.findAll();
    }
}
