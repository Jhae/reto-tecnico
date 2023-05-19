package com.avanta.exchanged.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "exchange_type")
public class ExchangeType {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    private Long idOriginCurrency;
//    private Long idDestinyCurrency;
    private BigDecimal rate;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "id_origin_currency")
    private Currency originCurrency;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "id_destiny_currency")
    private Currency destinyCurrency;

}
