package com.avanta.exchanged.entity.aux;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
public class GetCurrencyWithCountryNtt {
    private String id;
    private String name;
    private List<Country> countries;

    @AllArgsConstructor @NoArgsConstructor @Builder @Data
    public static class Country{
        private String id;
        private String name;
    }
}
