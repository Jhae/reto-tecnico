package com.avanta.exchanged.dto.converter;
import com.avanta.exchanged.entity.Currency;
import com.avanta.exchanged.dto.CurrencyDto;
import org.springframework.core.convert.converter.Converter;


public class CurrencyDtoMapper implements Converter<Currency, CurrencyDto> {
    @Override
    public CurrencyDto convert(Currency source) {
        CurrencyDto converted = CurrencyDto.builder()
                .id(source.getId())
                .name(source.getName())
                .countries(source.getCountries())
                .build();

        return converted;
    }
}
