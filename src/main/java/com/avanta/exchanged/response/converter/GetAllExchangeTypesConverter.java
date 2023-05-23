package com.avanta.exchanged.response.converter;

import com.avanta.exchanged.entity.aux.GetAllExchangeTypesNtt;
import com.avanta.exchanged.response.GetAllExchangeTypesResponse;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllExchangeTypesConverter implements Converter<GetAllExchangeTypesNtt, GetAllExchangeTypesResponse> {

    @Override
    public GetAllExchangeTypesResponse convert(GetAllExchangeTypesNtt source) {
        GetAllExchangeTypesResponse converted = GetAllExchangeTypesResponse.builder()
                .id(source.getId())
                .rate(source.getRate())
                .destinyCurrency(new CurrencyConverter().convert(source.getDestinyCurrency()))
                .build();

            if(source.getOriginCurrency() != null){
                converted.setOriginCurrency(new CurrencyConverter().convert(source.getOriginCurrency()));
            }

            if(source.getDestinyCurrency() != null){
                converted.setDestinyCurrency(new CurrencyConverter().convert(source.getDestinyCurrency()));
            }
        return converted;
    }
    public static class CurrencyConverter implements Converter<GetAllExchangeTypesNtt.Currency, GetAllExchangeTypesResponse.Currency>{

        @Override
        public GetAllExchangeTypesResponse.Currency convert(GetAllExchangeTypesNtt.Currency source) {
            GetAllExchangeTypesResponse.Currency converted = GetAllExchangeTypesResponse.Currency.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .build();
            if (source.getCountries()!=null)
            {
                List<GetAllExchangeTypesResponse.Currency.Country> convertedCountries = source.getCountries().stream()
                        .map(new CountryConverter()::convert)
                        .collect(Collectors.toList());

                converted.setCountries(convertedCountries);

            }
            return converted;
        }
        public static class CountryConverter implements Converter<GetAllExchangeTypesNtt.Currency.Country, GetAllExchangeTypesResponse.Currency.Country>{

            @Override
            public GetAllExchangeTypesResponse.Currency.Country convert(GetAllExchangeTypesNtt.Currency.Country source) {
                return GetAllExchangeTypesResponse.Currency.Country.builder()
                        .id(source.getId())
                        .name(source.getName())
                        .build();
            }
        }
    }
}
