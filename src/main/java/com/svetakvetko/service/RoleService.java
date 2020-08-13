package com.svetakvetko.service;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;

import java.util.List;

public interface RoleService {

    List<Role> getRolesById(Long userId);

    List<Role> getAllPossibleRoles();

    List<Role> getRolesByLogin(String userLogin);

    void addRole(User user, Long roleId);

}
