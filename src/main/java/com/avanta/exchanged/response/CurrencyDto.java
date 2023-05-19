package com.avanta.exchanged.response;

import com.avanta.exchanged.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CurrencyDto {
    private Long id;
    private String name;
    private CountryDto country;
}
