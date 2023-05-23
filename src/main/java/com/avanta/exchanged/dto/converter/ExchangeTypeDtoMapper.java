package com.avanta.exchanged.dto.converter;

import com.avanta.exchanged.entity.ExchangeType;
import com.avanta.exchanged.dto.CurrencyDto;
import com.avanta.exchanged.dto.ExchangeTypeDto;
import org.springframework.core.convert.converter.Converter;

public class ExchangeTypeDtoMapper implements Converter<ExchangeType, ExchangeTypeDto> {

    @Override
    public ExchangeTypeDto convert(ExchangeType source) {
        ExchangeTypeDto exchangeTypeDto = ExchangeTypeDto.builder()
                .id(source.getId())
                .originCurrency(source.getOriginCurrency())
                .destinyCurrency(source.getDestinyCurrency())
                .rate(source.getRate())
                .build();
        return exchangeTypeDto;
    }

}
