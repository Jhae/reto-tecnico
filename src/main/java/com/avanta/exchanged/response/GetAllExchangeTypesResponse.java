package com.avanta.exchanged.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetAllExchangeTypesResponse {
    private String id;
    private BigDecimal rate;
    private Currency originCurrency;
    private Currency destinyCurrency;

    @AllArgsConstructor @NoArgsConstructor @Builder @Data
    public static class Currency {
        private String id;
        private String name;
        private List<Country> countries;

        @AllArgsConstructor @NoArgsConstructor @Builder @Data
        public static class Country {
            private String id;
            private String name;
        }
    }
}
