package com.avanta.exchanged.service.impl;

import com.avanta.exchanged.bean.AppUser;
import com.avanta.exchanged.entity.User;
import com.avanta.exchanged.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service @RequiredArgsConstructor @Slf4j
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<UserDetails> userApp = userRepository.findByUsername(username)
                .map(userNtt -> {
                        return AppUser.builder()
                                .userNtt(userNtt)
                                .build();
                });
        return userApp;
    }
}
