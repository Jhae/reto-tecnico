package com.avanta.exchanged;

import com.avanta.exchanged.entity.Country;
import com.avanta.exchanged.entity.Currency;
import com.avanta.exchanged.entity.ExchangeType;
import com.avanta.exchanged.entity.User;
import com.avanta.exchanged.repository.CountryRespository;
import com.avanta.exchanged.repository.CurrencyRepository;
import com.avanta.exchanged.repository.ExchangeTypeRepository;
import com.avanta.exchanged.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ExchangeApplication {
	private final ReactiveMongoTemplate mongoTemplate;

	private final UserRepository userRepository;
	private final ExchangeTypeRepository exchangeTypeRepository;
	private final CurrencyRepository currencyRepository;
	private final CountryRespository countryRespository;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApplication.class, args);
	}

}
