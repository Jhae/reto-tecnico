package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.ExchangeHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository @RequiredArgsConstructor
public class CustomExchangeHistoryRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<ExchangeHistory> finAllWithCurrenciesAndWithUser(){
        Aggregation aggregations = Aggregation.newAggregation(
                Aggregation.lookup("currency","originCurrency","_id","originCurrency"),
                Aggregation.unwind("$originCurrency"),
                Aggregation.lookup("currency","destinyCurrency","_id","destinyCurrency"),
                Aggregation.unwind("$destinyCurrency"),
                Aggregation.lookup("country","originCurrency.countries","_id","originCurrency.countries"),
                Aggregation.lookup("country","destinyCurrency.countries","_id","destinyCurrency.countries"),
                Aggregation.lookup("user","user","_id","user"),
                Aggregation.unwind("$user")
        );
        return mongoTemplate.aggregate(aggregations, "exchange_history", ExchangeHistory.class);
    }
}
