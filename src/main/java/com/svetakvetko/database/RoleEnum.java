package com.svetakvetko.database;

public enum RoleEnum {
    ADMIN_ACCESS("admin"),
    USER_ACCESS("user");

    String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

