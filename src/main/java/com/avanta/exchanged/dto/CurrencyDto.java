package com.avanta.exchanged.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CurrencyDto {
    private String id;
    private String name;
    private List<CountryDto> countries;
}
