package com.avanta.exchanged.service.impl;

import com.avanta.exchanged.bean.AppUser;
import com.avanta.exchanged.dto.ExchangeHistoryDto;
import com.avanta.exchanged.dto.converter.ExchangeHistoryDtoMapper;
import com.avanta.exchanged.entity.ExchangeHistory;
import com.avanta.exchanged.repository.*;
import com.avanta.exchanged.request.DoExchangeRequest;
import com.avanta.exchanged.response.GetExchangeHistoryResponse;
import com.avanta.exchanged.response.converter.GetAllExchangeHistoryConverter;
import com.avanta.exchanged.service.ExchangeHistoryService;
import com.avanta.exchanged.util.PrincipalExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExchangeHistoryServiceImpl implements ExchangeHistoryService {

    private final ExchangeTypeRepository exchangeTypeRepository;

    private final ExchangeHistoryRepository exchangeHistoryRepository;
    private final CustomExchangeHistoryRepository customExchangeHistoryRepository;

    @Override
    public Mono<ExchangeHistoryDto> doExchange(DoExchangeRequest requestBody) {

        // Verificacion existencia de tipo de cambio
        return exchangeTypeRepository.findById(requestBody.getExchangeId())
                .switchIfEmpty(Mono.error(new Exception("No se encontro el tipo de cambio a usar")))
                .flatMap(exchangeTypeNtt -> {
                    // Verficacion de tasa de cambio
                    if(null == exchangeTypeNtt.getRate())
                    {
                        return Mono.error(new Exception("Mondas del camnio no disponibles"));
                    }
                    // Registrar el cambio
                    ExchangeHistory exchangeHistoryNtt = ExchangeHistory.builder()
                            .originAmount(requestBody.getOriginAmount())
                            .destinyAmount(
                                    BigDecimal.ZERO.add(
                                            exchangeTypeNtt.getRate().multiply(requestBody.getOriginAmount())
                                        )
                            )
                            .exchangeRate(exchangeTypeNtt.getRate())
                            .operationDate(new Date())
                            .originCurrency(exchangeTypeNtt.getOriginCurrency())
                            .destinyCurrency(exchangeTypeNtt.getDestinyCurrency())
                            .build();

                    return PrincipalExtractor.extractPrincipal(AppUser.class)
                                .flatMap(
                                        principal -> {
                                            exchangeHistoryNtt.setUser(principal.getUserNtt().getId());
                                            return exchangeHistoryRepository.save(exchangeHistoryNtt);
                                        }
                                );
                })
                .switchIfEmpty(Mono.error(new Exception("Error al hacer el cambio")))
                .flatMap(
                        savedExchangeHistory -> {
                            return Mono.just(new ExchangeHistoryDtoMapper().convert(savedExchangeHistory));
                        }
                );

    }

    @Override
    public Flux<GetExchangeHistoryResponse> loadExchangesHistoryWithCurrencyAndUser() {
        return customExchangeHistoryRepository.finAllWithCurrenciesAndWithUser()
                .flatMap(exchangeHistoryNtt -> Flux.just(new GetAllExchangeHistoryConverter().convert(exchangeHistoryNtt)));
    }


}
