package com.svetakvetko.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {
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

    public static List<Long> getAllId(User user) { //TODO move
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < user.getRole().size(); i++) {
            idList.add(user.getRole().get(i).id);
        }
        return idList;
    }


}
