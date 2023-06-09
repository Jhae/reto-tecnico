package com.avanta.exchanged.config;

import com.avanta.exchanged.handler.AuthHandler;
import com.avanta.exchanged.handler.CurrencyHandler;
import com.avanta.exchanged.handler.ExchangeHistoryHandler;
import com.avanta.exchanged.handler.ExchangeTypeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AppConfig {

    @Bean
    RouterFunction<ServerResponse> router(ExchangeTypeHandler exchangeTypeHandler
                                        ,ExchangeHistoryHandler exchangeHistoryHandler
                                        ,AuthHandler authHandler
                                        ,CurrencyHandler currencyHandler){
        return RouterFunctions.route()
                .POST("/svc/auth",authHandler::authenticate)

                .GET("/svc/currency",currencyHandler::getCurrenciesWithCountry)

                .GET("/svc/exchangeTypes",exchangeTypeHandler::getAllExchangeTypes)
                .POST("/svc/exchangeTypes",exchangeTypeHandler::saveExchangeType)
                .PUT("/svc/exchangeTypes/{id}",exchangeTypeHandler::updateExchangeType)
                .DELETE("/svc/exchangeTypes/{id}",exchangeTypeHandler::deleteExchangeType)

                .GET("/svc/exchangeHistory",exchangeHistoryHandler::getAllExchangesHistory)
                .POST("/svc/doExchange",exchangeHistoryHandler::doExchange)

                .build();

    }
}
