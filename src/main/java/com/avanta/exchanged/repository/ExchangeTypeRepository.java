package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.ExchangeType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ExchangeTypeRepository extends ReactiveMongoRepository<ExchangeType,String> {
    @Aggregation(pipeline = {
            "{ $lookup: { 'from': 'currency', 'foreignField': '_id', 'localField': 'originCurrency', 'as': 'originCurrency' } }",
            "{$unwind: '$originCurrency'}",
            "{$lookup: { 'from': 'currency', 'foreignField': '_id', 'localField': 'destinyCurrency', 'as': 'destinyCurrency' } }",
            "{$unwind: '$destinyCurrency'}",
            "{$lookup: { 'from': 'country', 'foreignField': '_id', 'localField': 'originCurrency.countries', 'as': 'originCurrency.countries' } }",
            "{$lookup: { 'from': 'country', 'foreignField': '_id', 'localField': 'destinyCurrency.countries', 'as': 'destinyCurrency.countries' } }"
    })
    Flux<ExchangeType> pipde();
}
