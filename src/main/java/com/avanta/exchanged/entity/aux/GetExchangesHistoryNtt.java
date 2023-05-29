package com.avanta.exchanged.entity.aux;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetExchangesHistoryNtt {
    private String id;
    private BigDecimal originAmount;
    private BigDecimal destinyAmount;
    private BigDecimal exchangeRate;
    private Date operationDate;
    private Currency destinyCurrency;
    private Currency originCurrency;
    private User user;

    @AllArgsConstructor @NoArgsConstructor @Builder @Data
    public static class Currency{
        private String id;
        private String name;
        private List<Country> countries;

        @AllArgsConstructor @NoArgsConstructor @Builder @Data
        public static class Country{
            private String id;
            private String name;
        }
    }
    @AllArgsConstructor @NoArgsConstructor @Builder @Data
    public static class User{
        private String id;
        private String username;
        private String password;
        private String email;
        private String name;
        private String lastName;
        private Boolean enabled;
        private List<String> roles;
    }
}