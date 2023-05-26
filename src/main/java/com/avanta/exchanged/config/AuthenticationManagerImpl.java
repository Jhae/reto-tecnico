package com.avanta.exchanged.config;

import com.avanta.exchanged.repository.UserRepository;
import com.avanta.exchanged.service.impl.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationManagerImpl implements ReactiveAuthenticationManager {

    private final ReactiveUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        return userDetailsService.findByUsername(authentication.getName())

                .flatMap(
                        appUser -> {
                            if( !passwordEncoder.matches(authentication.getCredentials().toString(),
                                                        appUser.getPassword()) )
                            {
                                return Mono.error(new BadCredentialsException(String.format("Credenciales no validas:[%s][%s]",authentication.getCredentials().toString(),appUser.getPassword()))) ;
                            }
                            return Mono.just(new UsernamePasswordAuthenticationToken(appUser,null ,appUser.getAuthorities()));
                        }
                );
    }
}
