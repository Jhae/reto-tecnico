package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.aux.GetCurrencyWithCountryNtt;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class CustomCurrencyRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<GetCurrencyWithCountryNtt> findAllWithCountry(){
        Aggregation aggregations = Aggregation.newAggregation(
            Aggregation.lookup("country","countries","_id","countries")
        );
        return mongoTemplate.aggregate(aggregations,"currency", GetCurrencyWithCountryNtt.class);
    }
}
