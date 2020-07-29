package com.svetakvetko.dao;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;


import java.sql.ResultSet;
import java.util.List;


public interface RoleDao {

    List<Long> getAllId(User user);

    List<Role> getRolesById(Long userId);

    List<Role> getRolesByLogin(String userLogin);

    List<Role> getAllPossibleRoles();

    Role extractRoleFromResultSet(ResultSet rs);

    void deleteRoles(User user);
}

