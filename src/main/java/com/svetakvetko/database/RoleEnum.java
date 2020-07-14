package com.svetakvetko.database;

public enum RoleEnum {
    ADMIN_ACCESS("ADMIN"),
    USER_ACCESS("USER");

    String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

