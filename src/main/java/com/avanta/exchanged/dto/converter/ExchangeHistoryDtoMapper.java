package com.avanta.exchanged.dto.converter;

import com.avanta.exchanged.dto.CurrencyDto;
import com.avanta.exchanged.dto.ExchangeHistoryDto;
import com.avanta.exchanged.entity.ExchangeHistory;
import org.springframework.core.convert.converter.Converter;

public class ExchangeHistoryDtoMapper implements Converter<ExchangeHistory, ExchangeHistoryDto> {

    @Override
    public ExchangeHistoryDto convert(ExchangeHistory source) {
        ExchangeHistoryDto converted = ExchangeHistoryDto.builder()
                .id(source.getId())
                .originAmount(source.getOriginAmount())
                .destinyAmount(source.getDestinyAmount())
                .exchangeRate(source.getExchangeRate())
                .operationDate(source.getOperationDate())
                .build();

        if (source.getOriginCurrency() != null) {
            CurrencyDto originCurrencyDto = new CurrencyDtoMapper().convert(source.getOriginCurrency());
            converted.setOriginCurrency(originCurrencyDto);
        }
        if (source.getDestinyCurrency() != null) {
            CurrencyDto destinyCurrencyDto = new CurrencyDtoMapper().convert(source.getDestinyCurrency());
            converted.setDestinyCurrency(destinyCurrencyDto);
        }
        return converted;
    }
}
