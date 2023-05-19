package com.avanta.exchanged.controller;

import com.avanta.exchanged.response.ExchangeTypeDto;
import com.avanta.exchanged.response.UserDto;
import com.avanta.exchanged.response.converter.ExchangeTypeDtoMapper;
import com.avanta.exchanged.service.ExchangeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("svc/exchangetype")
public class ExchangeTypeController {
    private final ExchangeTypeService exchangeTypeService;

    @GetMapping("")
    Mono<ResponseEntity<List<ExchangeTypeDto>>> getAllExchangeTypes(){
        Mono<ResponseEntity<List<ExchangeTypeDto>>> wa = exchangeTypeService.loadAllExchangeTypes()
                .collectList()
                .flatMap(exchangeTypes -> {
                    List<ExchangeTypeDto> collect = exchangeTypes.stream()
                            .map(exchangeType -> {
                                return new ExchangeTypeDtoMapper().convert(exchangeType);
                            }).collect(Collectors.toList());
                    return Mono.just(ResponseEntity.ok(collect));
                })
                .onErrorResume(throwable -> {
                    Mono<ResponseEntity<List<ExchangeTypeDto>>> ok = Mono.just(ResponseEntity.ok(Arrays.asList(new ExchangeTypeDto())));
                    return ok;
                });

        return wa;

    }


}
