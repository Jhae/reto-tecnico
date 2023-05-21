package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.ExchangeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class CustomExchangeTypeRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<ExchangeType> findAllWithCurrencyWithCuontries(){
        Aggregation aggregations = Aggregation.newAggregation(
                Aggregation.lookup("currency","originCurrency","_id","originCurrency"),
                Aggregation.unwind("$originCurrency"),
                Aggregation.lookup("currency","destinyCurrency","_id","destinyCurrency"),
                Aggregation.unwind("$destinyCurrency"),
                Aggregation.lookup("country","originCurrency.countries","_id","originCurrency.countries"),
                Aggregation.lookup("country","destinyCurrency.countries","_id","destinyCurrency.countries")
        );
        return mongoTemplate.aggregate(aggregations, "exchange_type", ExchangeType.class);
    }
}
