package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.ExchangeHistory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeHistoryRepository extends ReactiveMongoRepository<ExchangeHistory,String> {
}
