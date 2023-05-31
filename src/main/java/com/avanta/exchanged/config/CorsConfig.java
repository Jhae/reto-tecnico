package com.avanta.exchanged.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux @Setter
public class CorsConfig implements WebFluxConfigurer {

    @Value("${cors.allowed.originPatterns}")
    String[] allowedOriginPatterns;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET","POST","OPTIONS","PUT","DELETE")
                .allowedHeaders("*")
                .exposedHeaders("Access-Control-Allow-Origin",
                                "Access-Control-Allow-Methods",
                                "Access-Control-Allow-Headers")
                .allowedOriginPatterns(allowedOriginPatterns)
                .allowCredentials(true);
    }
}
