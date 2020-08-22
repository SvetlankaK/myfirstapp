package com.svetakvetko.service;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> getRolesById(Long userId) {
        return roleMapper.getRolesById(userId);
    }

    @Override
    public List<Role> getAllPossibleRoles() {
        return roleMapper.getAllPossibleRoles();
    }

    @Override
    public List<Role> getRolesByLogin(String userLogin) {
        return roleMapper.getRolesByLogin(userLogin);
    }

    @Override
    public void addRole(User user, Long roleId) {
        Map<String, Object> userRole = new HashMap();
        userRole.put("userId", user.getUserId());
        userRole.put("roleId", roleId);
        roleMapper.addRole(userRole);
    }
}