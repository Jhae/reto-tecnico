package com.avanta.exchanged.bean;

import lombok.*;

import java.util.stream.Stream;
@RequiredArgsConstructor @Getter
public enum AppRoleEnum {

    USER("USUARIO")
    ,ADMIN("ADMINISTRADOR");

    public static String ROLE_PREFIX = "ROLE_";

    private final String fmttName;

    public String getFullName()
    {
        return ROLE_PREFIX + this.name();
    }

    public static Boolean exists(String fullName){
        return Stream.of(AppRoleEnum.values())
                .map(appRoleEnum -> appRoleEnum.getFullName())
                .anyMatch(roleEmum -> roleEmum.equals(fullName));
    }

    public static AppRoleEnum fromFullName(String fullName){
        return valueOf(fullName.replace(ROLE_PREFIX, ""));
    }

}
