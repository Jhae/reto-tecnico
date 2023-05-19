package com.avanta.exchanged.response.converter;

import com.avanta.exchanged.entity.ExchangeType;
import com.avanta.exchanged.response.ExchangeTypeDto;
import org.springframework.core.convert.converter.Converter;

public class ExchangeTypeDtoMapper implements Converter<ExchangeType, ExchangeTypeDto> {

    @Override
    public ExchangeTypeDto convert(ExchangeType source) {

        return ExchangeTypeDto.builder()
                .id(source.getId())
                .rate(source.getRate())
                .build();
    }

}
