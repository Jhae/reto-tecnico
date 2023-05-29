package com.avanta.exchanged.util;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

public class PrincipalExtractor {
    public static <T> Mono<T> extractPrincipal(Class<T>clazz){
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(
                        securityContext -> {
                            Object principal = securityContext.getAuthentication().getPrincipal();
//                            log.info("ROLES: +" + principal.getAuthorities());
                            return Mono.just((T)principal);
                        });
    }
}
