package com.avanta.exchanged.service.impl;

import com.avanta.exchanged.dto.ExchangeHistoryDto;
import com.avanta.exchanged.dto.converter.ExchangeHistoryDtoMapper;
import com.avanta.exchanged.dto.converter.ExchangeTypeDtoMapper;
import com.avanta.exchanged.entity.Currency;
import com.avanta.exchanged.entity.ExchangeHistory;
import com.avanta.exchanged.repository.*;
import com.avanta.exchanged.request.DoExchangeRequest;
import com.avanta.exchanged.service.ExchangeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExchangeHistoryServiceImpl implements ExchangeHistoryService {
    private final ExchangeHistoryRepository exchangeHistoryRepository;
    private final CustomExchangeTypeRepository customExchangeTypeRepository;


    @Override
    public Mono<ExchangeHistoryDto> doExchange(DoExchangeRequest requestBody) {

        // Verificacion existencia de tipo de cambio
        return customExchangeTypeRepository.findByIdWithCurrencies(requestBody.getExchangeId())
                .filter(
                        exchangeType -> {
                            return requestBody.getExchangeId().equals(exchangeType.getId());
                        }
                )
                .next()
                .switchIfEmpty(Mono.error(new Exception("No se encontro el tipo de cambio a usar")))
                .flatMap(exchangeType -> {
                    // Verficacion de moendas de tipo de cambio
                    if(null == exchangeType.getDestinyCurrency() || null == exchangeType.getDestinyCurrency())
                    {
                        return Mono.error(new Exception("Mondas del camnio no disponibles"));
                    }
                    // Registrar el cambio
                    ExchangeHistory exchangeHistoryNtt = ExchangeHistory.builder()
                            .originAmount(requestBody.getOriginAmount())
                            .destinyAmount(
                                    BigDecimal.ZERO.add(
                                            exchangeType.getRate().multiply(requestBody.getOriginAmount())
                                        )
                            )
                            .exchangeRate(exchangeType.getRate())
                            .operationDate(new Date())
                            .originCurrency(exchangeType.getOriginCurrency())
                            .destinyCurrency(exchangeType.getDestinyCurrency())
                            .build();
                    return exchangeHistoryRepository.save(exchangeHistoryNtt);
                })
                .switchIfEmpty(Mono.error(new Exception("Error al hacer el cambio")))
                .flatMap(
                        savedExchangeHistory -> {
                            return Mono.just(new ExchangeHistoryDtoMapper().convert(savedExchangeHistory));
                        }
                );

    }
}
