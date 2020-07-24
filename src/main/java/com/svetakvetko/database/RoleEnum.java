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

    public static long getIdByRoleName(String roleName) {
        long resultId = 1;
        switch (roleName) {
            case "admin":
                resultId = 2;
                break;
            case "user":
                resultId = 1;
                break;
        }
        return resultId;
    }
}

