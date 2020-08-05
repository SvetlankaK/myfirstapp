package com.svetakvetko.service;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    public List<Long> getRolesIdDB(List<Role> roles) {
        return roles.stream().map(Role::getId).collect(Collectors.toList());
    }

    public List<Long> getRolesIdInUser(User user) {
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < user.getRole().size(); i++) {
            idList.add(user.getRole().get(i).getId());
        }
        return idList;
    }

    public Set<Long> findDifferenceDBAndUser(User user) {
        Set<Long> difference = new HashSet<>();
        for (int i = 0; i < roleMapper.getRolesById(user.getUserId()).size(); i++) {
            Set<Long> similar = new HashSet<>(getRolesIdInUser(user));
            difference.addAll(getRolesIdInUser(user));
            difference.addAll(getRolesIdDB(roleMapper.getRolesById(user.getUserId())));
            difference.removeAll(similar);
        }
        return difference;
    }

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
    public void deleteRoles(User user) { //TODO delete
        Map<String, Object> userRole = new HashMap();
        userRole.put("userId", user.getUserId());
        Set<Long> difference = new HashSet<>(findDifferenceDBAndUser(user));
        for (Long id : difference) {
            userRole.put("roleId", id);
            roleMapper.deleteRole(userRole);
        }
    }

    @Override
    public void addRole(User user, Long roleId) {
        Map<String, Object> userRole = new HashMap();
        userRole.put("userId", user.getUserId());
        userRole.put("roleId", roleId);
        roleMapper.addRole(userRole);
    }
}