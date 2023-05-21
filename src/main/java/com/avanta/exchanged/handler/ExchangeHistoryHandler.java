package com.avanta.exchanged.handler;

import com.avanta.exchanged.dto.ExchangeHistoryDto;
import com.avanta.exchanged.request.DoExchangeRequest;
import com.avanta.exchanged.service.ExchangeHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor @Slf4j
public class ExchangeHistoryHandler {
    private final ExchangeHistoryService exchangeHistoryService;

    public Mono<ServerResponse> doExchange(ServerRequest request){
        return request.bodyToMono(DoExchangeRequest.class)
                .flatMap(
                        DoExchangeRequest -> {
                            return exchangeHistoryService.doExchange(DoExchangeRequest);
                        }
                )
                .flatMap(
                        exchangeHistoryDto -> {
                            return ServerResponse.ok()
                                    .body(Mono.just(exchangeHistoryDto),ExchangeHistoryDto.class);
                        }
                )
                .onErrorResume(
                        ex -> {
                            log.error(ex.getMessage(),ex);
                            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        }
                );
    }

}
