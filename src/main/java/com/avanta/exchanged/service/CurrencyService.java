package com.avanta.exchanged.service;

import com.avanta.exchanged.response.GetCurrencyWithCountryResponse;
import reactor.core.publisher.Flux;

public interface CurrencyService {

    Flux<GetCurrencyWithCountryResponse> loadCurrenciesWithCountry();

}