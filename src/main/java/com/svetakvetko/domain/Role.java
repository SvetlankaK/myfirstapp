package com.svetakvetko.domain;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private long id;
    private String roleName;

    public Role(long id, String name) {
        this.id = id;
        this.roleName = name;
    }

    public Role() {

    }

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


    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
