package com.avanta.exchanged.response;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class UserDto {

    Long id;
    String username;
    String password;
    String email;
    String name;
    String lastName;
    Boolean enabled;

}
