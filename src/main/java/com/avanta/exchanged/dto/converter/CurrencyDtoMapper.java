package com.avanta.exchanged.dto.converter;

import com.avanta.exchanged.dto.CountryDto;
import com.avanta.exchanged.entity.Currency;
import com.avanta.exchanged.dto.CurrencyDto;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyDtoMapper implements Converter<Currency, CurrencyDto> {
    @Override
    public CurrencyDto convert(Currency source) {
        CurrencyDto converted = CurrencyDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
        if(source.getCountries()!=null){
            List<CountryDto> countriesDto = source.getCountries().stream()
                    .map(new CountryDtoMapper()::convert)
                    .collect(Collectors.toList());
            converted.setCountries(countriesDto);
        }

        return converted;
    }
}
