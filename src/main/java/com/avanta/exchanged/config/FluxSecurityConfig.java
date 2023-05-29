package com.avanta.exchanged.config;

import com.avanta.exchanged.bean.AppRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class FluxSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity,SecurityContextRepository securityConextRepsository,
                                        ReactiveAuthenticationManager authenticationManager){

        return httpSecurity.csrf().disable()
                .authorizeExchange().pathMatchers("/svc/auth").permitAll()

                .pathMatchers(HttpMethod.GET,"/svc/exchangeTypes").hasRole(AppRoleEnum.USER.name())
                .pathMatchers(HttpMethod.POST, "/svc/exchangeTypes").hasRole(AppRoleEnum.USER.name())
                .pathMatchers(HttpMethod.PUT, "/svc/exchangeTypes/**").hasRole(AppRoleEnum.USER.name())
                .pathMatchers(HttpMethod.DELETE, "/svc/exchangeTypes/**").hasRole(AppRoleEnum.USER.name())

                .pathMatchers(HttpMethod.GET, "/svc/exchangeHistory").hasRole(AppRoleEnum.ADMIN.name())
                .pathMatchers(HttpMethod.POST, "/svc/doExchange").hasRole(AppRoleEnum.USER.name())

                .anyExchange().authenticated()
                .and().securityContextRepository(securityConextRepsository)
                .authenticationManager(authenticationManager)
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .build();
    }

}
