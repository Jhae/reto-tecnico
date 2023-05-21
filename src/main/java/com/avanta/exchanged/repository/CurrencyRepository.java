package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.Currency;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends ReactiveMongoRepository<Currency,String> {
}
