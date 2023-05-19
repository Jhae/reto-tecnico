package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User,Long> {

    Mono<User> findByUsername(String username);
}
