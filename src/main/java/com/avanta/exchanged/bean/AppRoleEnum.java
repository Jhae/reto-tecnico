package com.avanta.exchanged.bean;

public enum AppRoleEnum {

    USER
    ,ADMIN;

    public String fullName()
    {
        return "ROLE_"+this.name();
    }

}
