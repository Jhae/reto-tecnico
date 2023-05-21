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
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ExchangeApplication implements CommandLineRunner {
	private final ReactiveMongoTemplate mongoTemplate;

	private final UserRepository userRepository;
	private final ExchangeTypeRepository exchangeTypeRepository;
	private final CurrencyRepository currencyRepository;
	private final CountryRespository countryRespository;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Country> paises=Arrays.asList(
				Country.builder().name("Peru").build(),
				Country.builder().name("USA").build(),
				Country.builder().name("Espana").build(),
				Country.builder().name("Alemania").build(),
				Country.builder().name("Argentina").build()
		);
//		countryRespository.saveAll(paises).subscribe();

		List<Currency> currencies = Arrays.asList(
				Currency.builder().name("Sol").build(),
				Currency.builder().name("Dolar").build(),
				Currency.builder().name("Euro").build(),
				Currency.builder().name("Peso").build()
		);
//		currencyRepository.saveAll(currencies).subscribe();
//		currencyRepository.findById("6469950348f9711bc31b3155")
//						.flatMap(
//								currency -> {
//									 return countryRespository.findByNameLike("an")
//											 .collectList()
//											 .flatMap(x->{currency.setCountries(x);
//												 return Mono.just(currency);
//											 });
//								}
//						).flatMap(
//								currency -> currencyRepository.save(currency)
//				)
//				.subscribe();
//		currencyRepository.findById("6469950348f9711bc31b3155")
//						.flatMap(
//								currency -> {
//									return Mono.just(currency.getCountries());
//								}
//						).subscribe(System.out::println);

//		exchangeTypeRepository.pipde().subscribe(System.out::println);
		Aggregation aggregation = Aggregation.newAggregation(//Aggregation.lookup("currency", "destinyCurrency", "_id", "destinyCurrency"),
//															Aggregation.lookup("country","destinyCurrency.countries","_id","destinyCurrency.countries"));
				Aggregation.lookup("country","countries","_id","countries"));
//		mongoTemplate.aggregate(aggregation, "currency", Currency.class).subscribe(System.out::println);
		Aggregation aggs = Aggregation.newAggregation(
				Aggregation.lookup("currency","originCurrency","_id","originCurrency"),
				Aggregation.unwind("$originCurrency"),
				Aggregation.lookup("currency","destinyCurrency","_id","destinyCurrency"),
				Aggregation.unwind("$destinyCurrency"),
				Aggregation.lookup("country","originCurrency.countries","_id","originCurrency.countries"),
				Aggregation.lookup("country","destinyCurrency.countries","_id","destinyCurrency.countries")
	);
		mongoTemplate.aggregate(aggs, "exchange_type", ExchangeType.class).subscribe(System.out::println);

		userRepository.save(User.builder()
								.name("Lucas")
								.lastName("Gonzae")
								.email("lucrecio@gmail.com")
								.enabled(true)
								.password("dasdasd").build());


	}
}
