package com.avanta.exchanged.response.converter;

import com.avanta.exchanged.bean.AppRoleEnum;
import com.avanta.exchanged.entity.aux.GetExchangesHistoryNtt;
import com.avanta.exchanged.entity.aux.GetExchangesHistoryNtt;
import com.avanta.exchanged.response.GetAllExchangeTypesResponse;
import com.avanta.exchanged.response.GetExchangeHistoryResponse;
import com.avanta.exchanged.response.GetExchangeHistoryResponse;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllExchangeHistoryConverter implements Converter<GetExchangesHistoryNtt, GetExchangeHistoryResponse> {

    @Override
    public GetExchangeHistoryResponse convert(GetExchangesHistoryNtt source) {
        GetExchangeHistoryResponse converted = GetExchangeHistoryResponse.builder()
                .id(source.getId())
                .originAmount(source.getOriginAmount())
                .destinyAmount(source.getDestinyAmount())
                .exchangeRate(source.getExchangeRate())
                .operationDate(source.getOperationDate())
                .build();
        if ( source.getOriginCurrency() != null )
        {
            converted.setOriginCurrency(new GetAllExchangeHistoryConverter.CurrencyConverter().convert(source.getOriginCurrency()));
            converted.setDestinyCurrency(new GetAllExchangeHistoryConverter.CurrencyConverter().convert(source.getDestinyCurrency()));
        }
        if ( source.getUser() != null )
        {
            converted.setUser(new GetAllExchangeHistoryConverter.UserConverter().convert(source.getUser()));
        }

        return converted;
    }

    public static class CurrencyConverter implements Converter<GetExchangesHistoryNtt.Currency, GetExchangeHistoryResponse.Currency>{

        @Override
        public GetExchangeHistoryResponse.Currency convert(GetExchangesHistoryNtt.Currency source) {
            GetExchangeHistoryResponse.Currency converted = GetExchangeHistoryResponse
                    .Currency.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .build();
            if (source.getCountries()!=null)
            {
                List<GetExchangeHistoryResponse.Currency.Country> convertedCountries = source.getCountries().stream()
                        .map(new GetAllExchangeHistoryConverter
                                .CurrencyConverter
                                .CountryConverter()::convert)
                        .collect(Collectors.toList());

                converted.setCountries(convertedCountries);

            }
            return converted;
        }
        public static class CountryConverter implements Converter<GetExchangesHistoryNtt.Currency.Country, GetExchangeHistoryResponse.Currency.Country>{

            @Override
            public GetExchangeHistoryResponse.Currency.Country convert(GetExchangesHistoryNtt.Currency.Country source) {
                return GetExchangeHistoryResponse.Currency.Country.builder()
                        .id(source.getId())
                        .name(source.getName())
                        .build();
            }
        }
    }

    public static class UserConverter implements Converter<GetExchangesHistoryNtt.User, GetExchangeHistoryResponse.User>{

        @Override
        public GetExchangeHistoryResponse.User convert(GetExchangesHistoryNtt.User source) {
            GetExchangeHistoryResponse.User converted = GetExchangeHistoryResponse.User.builder()
                    .id(source.getId())
                    .username(source.getUsername())
//                    .password(source.getPassword())
                    .email(source.getEmail())
                    .name(source.getName())
                    .lastName(source.getLastName())
                    .enabled(source.getEnabled())
                    .build();

            List<String> fmttRoles = source.getRoles().stream()
                .filter(roleChain -> AppRoleEnum.exists(roleChain))
                .map(role -> AppRoleEnum.fromFullName(role).getFmttName())

                .collect(Collectors.toList());

            converted.setRoles(fmttRoles);

            return converted;
        }
    }
}
