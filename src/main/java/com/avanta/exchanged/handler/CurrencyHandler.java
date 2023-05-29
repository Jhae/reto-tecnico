package com.avanta.exchanged.handler;

import com.avanta.exchanged.response.GetCurrencyWithCountryResponse;
import com.avanta.exchanged.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyHandler {

    private final CurrencyService currencyService;

    public Mono<ServerResponse> getCurrenciesWithCountry(ServerRequest request){

        Flux<GetCurrencyWithCountryResponse> responseBody = currencyService.loadCurrenciesWithCountry();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody,GetCurrencyWithCountryResponse.class)
                .onErrorContinue(
                        (throwable, o) -> {
                            log.error(throwable.getMessage(), throwable);
                        }
                );

    }
}
