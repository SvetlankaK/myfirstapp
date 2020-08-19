package com.svetakvetko.service;

import com.svetakvetko.domain.User;

import java.util.Collection;


public interface UserService {
    void create(User user);

    void delete(Long userId);

    User findById(Long UserId);

    boolean isExist(String userLogin);

    Collection<User> findAll();

    void update(User user);

    User findByLogin(String userLogin);


}
