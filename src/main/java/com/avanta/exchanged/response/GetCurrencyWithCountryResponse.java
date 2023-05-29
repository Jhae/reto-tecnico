package com.avanta.exchanged.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class GetCurrencyWithCountryResponse {

    private String id;
    private String name;
    private List<Country> countries;

    @AllArgsConstructor @NoArgsConstructor @Builder @Data
    public static class Country{
        private String id;
        private String name;
    }
}
