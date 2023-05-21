package com.avanta.exchanged.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class UserDto {

    String id;
    String username;
    String password;
    String email;
    String name;
    String lastName;
    Boolean enabled;

}
