package com.avanta.exchanged;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ExchangeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExchangeApplication.class, args);
	}

}
