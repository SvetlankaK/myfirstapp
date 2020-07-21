package com.svetakvetko.domain;

public class Role {
    private long id;
    private String roleName;


    public void setId(long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }


}
