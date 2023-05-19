package com.avanta.exchanged.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "country")
public class Country {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
}
