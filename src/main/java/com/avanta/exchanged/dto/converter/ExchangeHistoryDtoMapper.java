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
                .originCurrency(source.getOriginCurrency())
                .destinyCurrency(source.getDestinyCurrency())
                .originAmount(source.getOriginAmount())
                .destinyAmount(source.getDestinyAmount())
                .exchangeRate(source.getExchangeRate())
                .operationDate(source.getOperationDate())
                .user(source.getUser())
                .build();
        return converted;
    }
}
