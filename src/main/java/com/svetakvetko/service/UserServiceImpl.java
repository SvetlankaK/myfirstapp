package com.svetakvetko.service;

import com.svetakvetko.domain.User;
import com.svetakvetko.mapper.RoleMapper;
import com.svetakvetko.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public void create(User user) {
        userMapper.create(user);
        for (int i = 0; i < user.getRole().size(); i++) {
            roleMapper.addRole(user, user.getRole().get(i).getId());
        }

    }

    @Override
    public void delete(Long userId) {
        userMapper.delete(userId);
    }

    @Override
    public User findById(Long userId) {
        return userMapper.findById(userId);
    }

    @Override
    public boolean isExist(String userLogin) {
        if (userMapper.findByLogin(userLogin).toString().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Collection<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User update(User user) {
        userMapper.update(user);
        for (int i = 0; i < user.getRole().size(); i++) {
            roleMapper.addRole(user, user.getRole().get(i).getId());
        }
        if (user.getRole().size() != roleService.getRolesIdDB(user.getRole()).size()) {
            roleService.deleteRoles(user);
        }
        return userMapper.findById(user.getUserId());
    }

    @Override
    public User findByLogin(String userLogin) {
        return userMapper.findByLogin(userLogin);
    }
}
