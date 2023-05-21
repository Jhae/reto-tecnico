package com.avanta.exchanged.service.impl;

import com.avanta.exchanged.entity.Currency;
import com.avanta.exchanged.entity.ExchangeType;
import com.avanta.exchanged.repository.CustomExchangeTypeRepository;
import com.avanta.exchanged.repository.ExchangeTypeRepository;
import com.avanta.exchanged.dto.ExchangeTypeDto;
import com.avanta.exchanged.dto.converter.ExchangeTypeDtoMapper;
import com.avanta.exchanged.service.ExchangeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeTypeServiceImpl implements ExchangeTypeService {

    private final CustomExchangeTypeRepository customExchangeTypeRepository;
    private final ExchangeTypeRepository exchangeTypeRepository;

    @Override
    public Flux<ExchangeTypeDto> loadAllExchangeTypes() {
        return customExchangeTypeRepository.findAllWithCurrencyWithCuontries()
                .flatMap(
                        exchangeTypeNtt -> {
                            return Mono.just(new ExchangeTypeDtoMapper().convert(exchangeTypeNtt));
                        }
                );
    }

    @Override
    public Mono<ExchangeTypeDto> saveExchangetype(ExchangeTypeDto exchangeTypeDto) {
        ExchangeType exchangeTypeNtt = ExchangeType.builder()
                .originCurrency(
                        Currency.builder()
                                .id(exchangeTypeDto.getOriginCurrency().getId())
                                .build()
                )
                .destinyCurrency(
                        Currency.builder()
                                .id(exchangeTypeDto.getDestinyCurrency().getId())
                                .build()
                )
                .rate(exchangeTypeDto.getRate())
                .build();
        return exchangeTypeRepository.save(exchangeTypeNtt)
                .flatMap(record -> Mono.just(new ExchangeTypeDtoMapper().convert(record)));
    }

    @Override
    public Mono<ExchangeTypeDto> updateExchangeType(ExchangeTypeDto exchangeTypeDto) {

        return exchangeTypeRepository.findById(exchangeTypeDto.getId())
                .switchIfEmpty(Mono.error(new Exception("No se encontro registro para actualizar")))
                .flatMap(
                        exchangeTypeNtt -> {
                            exchangeTypeNtt.setRate(exchangeTypeDto.getRate());
                            return exchangeTypeRepository.save(exchangeTypeNtt);
                        }
                )
                .flatMap(record -> Mono.just(new ExchangeTypeDtoMapper().convert(record)));
    }

    @Override
    public Mono<Void> deleteExchangeType(String id) {
        return exchangeTypeRepository.delete(ExchangeType.builder().id(id).build());
    }


}
