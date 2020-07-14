package com.svetakvetko.service;

import com.svetakvetko.dao.UserDao;
import com.svetakvetko.domain.User;
import com.svetakvetko.factory.DaoFactory;

import java.util.Collection;


public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserDao userDao = DaoFactory.getInstance().getUserDao();


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
