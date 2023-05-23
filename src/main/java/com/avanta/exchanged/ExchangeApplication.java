package com.avanta.exchanged;

import com.avanta.exchanged.entity.Country;
import com.avanta.exchanged.entity.Currency;
import com.avanta.exchanged.entity.ExchangeType;
import com.avanta.exchanged.entity.User;
import com.avanta.exchanged.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ExchangeApplication implements CommandLineRunner{
	private final ReactiveMongoTemplate mongoTemplate;

	private final UserRepository userRepository;
	private final ExchangeTypeRepository exchangeTypeRepository;
	private final CurrencyRepository currencyRepository;
	private final CountryRespository countryRespository;
	private final CustomExchangeTypeRepository customExchangeTypeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		countryRespository.findAll().subscribe(System.out::println);
		currencyRepository.findAll().subscribe(System.out::println);
//		exchangeTypeRepository.findAll().subscribe(System.out::println);
		customExchangeTypeRepository.findAllWithCurrencyWithCuontries().subscribe(System.out::println);

		System.out.println(("1 -------------------------"));
		Aggregation.lookup("country","countries","_id","countries");
				Aggregation.unwind("$countries");

//		MatchOperation id = Aggregation.match(Criteria.where("name").is("Euro"));
//		Aggregation aggregations = Aggregation.newAggregation(
//				Aggregation.match(Criteria.where("_id").nin("")),
//		LookupOperation.newLookup().from("currency").localField("originCurrency").foreignField("_id").as("originCurrency"),
//				Aggregation.unwind("$originCurrency"),
//		LookupOperation.newLookup().from("currency").localField("destinyCurrency").foreignField("_id").as("destinyCurrency"),
//				Aggregation.unwind("$destinyCurrency"),
//		LookupOperation.newLookup().from("country").localField("originCurrency.countries").foreignField("_id").as("originCurrency.countries"),
//		LookupOperation.newLookup().from("country").localField("destinyCurrency.countries").foreignField("_id").as("destinyCurrency.countries")
//		);
////		Aggregation aggg = Aggregation.newAggregation(id, aggregations);
//		mongoTemplate.aggregate(aggregations,"exchange_type", ExchangeType.class).
//		onErrorContinue((throwable, o) -> {
//			System.out.println("---------- ======================== *************************"+o.getClass());
//		}).subscribe(z->System.out.println("XXXXX"+z));

		countryRespository.save(
				Country.builder().name("USA").build())
				.subscribe(System.out::println);



	}
}
