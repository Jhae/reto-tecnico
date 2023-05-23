package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.ExchangeType;
import com.avanta.exchanged.entity.aux.GetAllExchangeTypesNtt;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class CustomExchangeTypeRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<GetAllExchangeTypesNtt> findAllWithCurrencyWithCuontries(){
        Aggregation aggregations = Aggregation.newAggregation(
                Aggregation.lookup("currency","originCurrency","_id","originCurrency"),
                Aggregation.unwind("$originCurrency"),
                Aggregation.lookup("currency","destinyCurrency","_id","destinyCurrency"),
                Aggregation.unwind("$destinyCurrency"),
                Aggregation.lookup("country","originCurrency.countries","_id","originCurrency.countries"),
                Aggregation.lookup("country","destinyCurrency.countries","_id","destinyCurrency.countries")
        );
        return mongoTemplate.aggregate(aggregations, "exchange_type", GetAllExchangeTypesNtt.class);
    }
    public Flux<ExchangeType> findByIdWithCurrencies(String id){
        Aggregation aggregations = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("_id").is(new ObjectId(id))),
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
