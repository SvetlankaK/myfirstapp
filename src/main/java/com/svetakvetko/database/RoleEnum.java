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
        long resultId = 0;
        switch (roleName) {
            case "ADMIN_ACCESS.getName()":
                resultId = 2;
            case "USER_ACCESS.getName()":
                resultId = 1;
        }
        return resultId;
    }
}

