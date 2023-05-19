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
@Table(name = "user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 50,nullable = false)
    String username;
    @Column(length = 50,nullable = false)
    String password;
    @Column(length = 100,nullable = false)
    String email;
    @Column(length = 50,nullable = false)
    String name;
    @Column(length = 50,nullable = false)
    String lastName;
//    @Column(precision = 10,scale = 2)
//    BigDecimal money;

    Boolean enabled;
}
