package com.avanta.exchanged.config;

import com.avanta.exchanged.repository.UserRepository;
import com.avanta.exchanged.service.impl.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor @Slf4j
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private final ReactiveAuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final ReactiveUserDetailsService userDetailsService;

    private String BEARER_CHAIN="Bearer ";


    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String jwt = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(jwt == null || "".equals(jwt))
        {
            return Mono.just(new SecurityContextImpl(null));
        }
        else return Mono.just(jwt)
            .flatMap(
                        token -> {
                            if (! token.startsWith(BEARER_CHAIN))
                            {
                                return Mono.error(new Exception());
                            }
                            return jwtService.getSubjectFromToken(token.replace(BEARER_CHAIN,""));
                        })
                .flatMap(
                        subject -> {
                            return userDetailsService.findByUsername(subject);
                        })
                .flatMap(
                        appUser -> {
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities());
                            return Mono.just(new SecurityContextImpl(authentication));
                        });
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }
}
