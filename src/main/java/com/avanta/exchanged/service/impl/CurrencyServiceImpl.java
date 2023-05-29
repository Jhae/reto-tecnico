package com.avanta.exchanged.service.impl;

import com.avanta.exchanged.repository.CustomCurrencyRepository;
import com.avanta.exchanged.response.GetCurrencyWithCountryResponse;
import com.avanta.exchanged.response.converter.GetCurrencyWithCountryConverter;
import com.avanta.exchanged.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CustomCurrencyRepository customCountryRepository;

    @Override
    public Flux<GetCurrencyWithCountryResponse> loadCurrenciesWithCountry() {
        return customCountryRepository.findAllWithCountry()
                .flatMap(
                        currencyWithCountryNtt -> {
                            return Flux.just(new GetCurrencyWithCountryConverter().convert(currencyWithCountryNtt));
                        }
                );
    }
}
