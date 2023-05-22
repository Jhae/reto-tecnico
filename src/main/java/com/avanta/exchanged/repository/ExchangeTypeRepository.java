package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.ExchangeType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ExchangeTypeRepository extends ReactiveMongoRepository<ExchangeType,String> {
}
