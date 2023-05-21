package com.avanta.exchanged.handler;

import com.avanta.exchanged.dto.ExchangeTypeDto;;
import com.avanta.exchanged.service.ExchangeTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class ExchangeTypeHandler {

    private final ExchangeTypeService exchangeTypeService;

    public Mono<ServerResponse> getAllExchangeTypes(ServerRequest request){
        Flux<ExchangeTypeDto> body = exchangeTypeService.loadAllExchangeTypes();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(body, ExchangeTypeDto.class)
                .onErrorContinue((ex, o) -> log.error(ex.getMessage(),ex));
    }

    public Mono<ServerResponse> saveExchangeType(ServerRequest request){
        Mono<ExchangeTypeDto> requestBody = request.bodyToMono(ExchangeTypeDto.class);
        Mono<ExchangeTypeDto> responseBody = requestBody
                .flatMap(
                        reqBody -> {
                            return exchangeTypeService.saveExchangetype(reqBody);
                        });
        return responseBody
                .switchIfEmpty(Mono.error(new Exception("No se devolvio resultado de insercion")))
                .flatMap(
                        exchangeTypeDto -> {
                            return ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(Mono.just(exchangeTypeDto), ExchangeTypeDto.class);
                        }
                ).onErrorResume(
                        ex -> {
                            log.error(ex.getMessage(),ex);
                            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        }
                );
    }

    public Mono<ServerResponse> updateExchangeType(ServerRequest request){
        String idPathParam = request.pathVariable("id");
        Mono<ExchangeTypeDto> bodyRequest = request.bodyToMono(ExchangeTypeDto.class);

        Mono<ExchangeTypeDto> responseBody = bodyRequest
                .flatMap(
                        bodyReq -> {
                            bodyReq.setId(idPathParam);
                            return exchangeTypeService.updateExchangeType(bodyReq);
                        }
                );
        return responseBody
                .flatMap(
                        exchangeTypeDto -> {
                            return ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(Mono.just(exchangeTypeDto), ExchangeTypeDto.class);
                        }
                )
                .onErrorResume(
                        ex -> {
                            log.error(ex.getMessage(),ex);
                            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        }
                );
    }

    public Mono<ServerResponse> deleteExchangeType(ServerRequest request){
        String idPathParam = request.pathVariable("id");
        Mono<Void> responseBody = exchangeTypeService.deleteExchangeType(idPathParam);

        return responseBody
                .flatMap(
                        unused -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.empty(),Void.class)
                ).onErrorResume(
                        ex -> {
                            log.error(ex.getMessage(),ex);
                            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        }
                );
    }
}
