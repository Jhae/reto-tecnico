package com.avanta.exchanged.dto.converter;

import com.avanta.exchanged.dto.UserDto;
import com.avanta.exchanged.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserDtoMapper implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        return UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .email(source.getEmail())
                .lastName(source.getLastName())
                .enabled(source.getEnabled())
                .build();
    }
}
