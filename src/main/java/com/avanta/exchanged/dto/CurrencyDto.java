package com.avanta.exchanged.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CurrencyDto {
    private String id;
    private String name;
    private List<String> countries;
}
