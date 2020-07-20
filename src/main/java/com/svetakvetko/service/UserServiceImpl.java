package com.svetakvetko.service;

import com.svetakvetko.dao.UserDao;
import com.svetakvetko.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void delete(Long userId) {
        userDao.delete(userId);
    }

    @Override
    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public boolean isExist(String userLogin) {
        return userDao.isExist(userLogin);
    }

    @Override
    public Collection<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User findByLogin(String userLogin) {
        return userDao.findByLogin(userLogin);
    }
}
