package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.Country;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CountryRespository extends ReactiveMongoRepository<Country,String> {
    Flux<Country> findByNameLike(String name);
}
