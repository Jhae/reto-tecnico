package com.avanta.exchanged.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "user")
public class User {
    @Id
    String id;
    String username;
    String password;
    String email;
    String name;
    String lastName;
    Boolean enabled;
}
