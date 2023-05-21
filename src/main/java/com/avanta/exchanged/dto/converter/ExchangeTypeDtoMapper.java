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
                .rate(source.getRate())
                .build();
        if(source.getOriginCurrency() != null){
            CurrencyDto originCurrencyDto = new CurrencyDtoMapper().convert(source.getOriginCurrency());
            exchangeTypeDto.setOriginCurrency(originCurrencyDto);
        }
        if(source.getDestinyCurrency() != null){
            CurrencyDto destinyCurrencyDto = new CurrencyDtoMapper().convert(source.getDestinyCurrency());
            exchangeTypeDto.setDestinyCurrency(destinyCurrencyDto);
        }
        return exchangeTypeDto;
    }

}
