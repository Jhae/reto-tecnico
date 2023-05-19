package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.ExchangeHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ExchangeHistoryRepository extends ReactiveCrudRepository<ExchangeHistory,Long> {
}
