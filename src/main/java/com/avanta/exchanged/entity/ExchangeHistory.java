package com.avanta.exchanged.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "exchange_history",uniqueConstraints = {@UniqueConstraint(columnNames = {"id_origin_currency","id_destiny_currency"})})
public class ExchangeHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10,scale = 2)
    private BigDecimal originAmount;

    @Column(precision = 10,scale = 2)
    private BigDecimal destinyAmount;

    @Column(precision = 10,scale = 2)
    private BigDecimal exchangeRate;

    private Date operationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destiny_currency",referencedColumnName = "id")
    private Currency destinyCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origin_currency",referencedColumnName = "id")
    private Currency originCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user",referencedColumnName = "id")
    private User user;
}
