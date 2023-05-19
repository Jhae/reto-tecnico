package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.ExchangeType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ExchangeTypeRepository extends ReactiveCrudRepository<ExchangeType,Long> {
}
