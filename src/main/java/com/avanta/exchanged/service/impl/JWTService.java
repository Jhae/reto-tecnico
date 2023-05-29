package com.avanta.exchanged.service.impl;

import com.avanta.exchanged.bean.AppUser;
import com.avanta.exchanged.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JWTService {
    /* Duracion 30 segundos*/
    public static final long JWT_LIFE_TIME = 1000 * 60*30;
    public static final String JWT_SECRET_KEY = "@McQfTjWnZq4t7w!z%C*F-JaNdRgUkXp";

    public Mono<String> generateJWT(UserDetails user){
        return Mono.just(Jwts.builder()
                            .setSubject(user.getUsername())
                            .setExpiration( new Date(System.currentTimeMillis() + JWT_LIFE_TIME) )
                            .claim("roles", user.getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                            .signWith(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()))
                            .compact());
    }

    public Mono<String> getSubjectFromToken(String jwt){
        return Mono.just(jwt)
                .map(
                        token -> {
                            return Jwts.parserBuilder()
                                    .setSigningKey(JWT_SECRET_KEY.getBytes())
                                    .build()
                                    .parseClaimsJws(jwt)
                                    .getBody();
                        }
                ).flatMap(
                        claims -> {
                            Date current = new Date(System.currentTimeMillis());
                            boolean isTokenExpired = claims.getExpiration().before(current);
                            if (isTokenExpired)
                            {
                                return Mono.error(new Exception("Jwt expirado"+claims.getExpiration()+"|"+current));
                            }
                            return Mono.just(claims.getSubject());
                        }
                );
    }

    public Mono<Authentication> getAuthFromJwt(String jwt){
        return Mono.just(jwt)
                .map(
                        token -> {
                            return Jwts.parserBuilder()
                                    .setSigningKey(JWT_SECRET_KEY.getBytes())
                                    .build()
                                    .parseClaimsJws(jwt)
                                    .getBody();
                        }
                ).flatMap(
                        claims -> {
                            boolean isTokenExpired = claims.getExpiration().after(new Date(System.currentTimeMillis()));
                            if (isTokenExpired)
                            {
                                return Mono.error(new Exception("Jwt expirado"));
                            }
                            return Mono.just(AppUser.builder()
                                                .userNtt(
                                                    User.builder()
                                                    .name(claims.getSubject())
                                                    .roles(claims.get("roles",ArrayList.class))
                                                    .build()
                                                ).build());
                        }
                ).flatMap(
                        appUser -> Mono.just(new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities()))
                );
    }

}
