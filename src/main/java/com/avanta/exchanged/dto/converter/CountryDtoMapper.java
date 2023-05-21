package com.avanta.exchanged.dto.converter;

import com.avanta.exchanged.dto.CountryDto;
import com.avanta.exchanged.entity.Country;
import org.springframework.core.convert.converter.Converter;

public class CountryDtoMapper implements Converter<Country, CountryDto> {
    @Override
    public CountryDto convert(Country source) {
        return CountryDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
