package com.avanta.exchanged.repository;

import com.avanta.exchanged.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {

    Mono<User> findByUsername(String username);

    @Query("SELECT new User(u.username, u.password) FROM User u WHERE username=:username AND password=:password;")
    Mono<User> fetchUsernameAndPassword(@Param("username") String username);
}