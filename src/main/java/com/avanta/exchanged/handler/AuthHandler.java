package com.avanta.exchanged.handler;

import com.avanta.exchanged.bean.AppUser;
import com.avanta.exchanged.request.AuthRequest;
import com.avanta.exchanged.request.AuthResponse;
import com.avanta.exchanged.service.impl.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor @Slf4j
public class AuthHandler {
    private final ReactiveAuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public Mono<ServerResponse> authenticate(ServerRequest request){
        return request.bodyToMono(AuthRequest.class)
                .flatMap(
                        authRequest -> {
                            return authenticationManager.authenticate(
                                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
                        }
                ).flatMap(
                        authentication -> {
                            return jwtService.generateJWT((AppUser)authentication.getPrincipal());
                        }
                )
                .flatMap(
                        jwt -> {
                            AuthResponse authResponse = AuthResponse.builder()
                                    .access_token(jwt)
                                    .build();

                            return ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(Mono.just(authResponse), AuthResponse.class);
                        }
                ).log()
                .onErrorResume(
                        ex -> {
                            log.error(ex.getMessage(), ex);

                            if (ex instanceof UsernameNotFoundException)
                                return ServerResponse.notFound().build();
                            if (ex instanceof BadCredentialsException)
                                return ServerResponse.badRequest().build();
                            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        }
                );
    }
}
