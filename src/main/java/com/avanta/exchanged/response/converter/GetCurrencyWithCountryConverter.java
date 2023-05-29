package com.avanta.exchanged.response.converter;

import com.avanta.exchanged.entity.aux.GetCurrencyWithCountryNtt;
import com.avanta.exchanged.response.GetCurrencyWithCountryResponse;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class GetCurrencyWithCountryConverter implements Converter<GetCurrencyWithCountryNtt, GetCurrencyWithCountryResponse> {

    @Override
    public GetCurrencyWithCountryResponse convert(GetCurrencyWithCountryNtt source) {
        GetCurrencyWithCountryResponse converted = GetCurrencyWithCountryResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
        if ( source.getCountries() != null )
        {
            converted.setCountries(source.getCountries().stream().map(country -> new CountryConverter().convert(country)).collect(Collectors.toList()));
        }
        return converted;
    }

    static class CountryConverter implements Converter<GetCurrencyWithCountryNtt.Country, GetCurrencyWithCountryResponse.Country>{

        @Override
        public GetCurrencyWithCountryResponse.Country convert(GetCurrencyWithCountryNtt.Country source) {
            return GetCurrencyWithCountryResponse.Country.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .build();
        }
    }
}
