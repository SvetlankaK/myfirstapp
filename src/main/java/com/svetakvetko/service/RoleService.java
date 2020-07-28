package com.svetakvetko.service;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Long> getRolesIdDB(List<Role> roles);

    List<Long> getRolesIdInUser(User user);

    Set<Long> findDifferenceDBAndUser(User user);

    List<Role> getRolesById(Long userId);

    List<Role> getAllPossibleRoles();

    List<Role> getRolesByLogin(String userLogin);

    void deleteRoles(User user);

    void addRole(User user, Long roleId);
}
