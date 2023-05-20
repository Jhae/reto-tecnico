package com.avanta.exchanged.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor @NoArgsConstructor
@Builder
@Data
@Document(collection = "country")
public class Country {
    @Id
    private Long id;
    private String name;
}
