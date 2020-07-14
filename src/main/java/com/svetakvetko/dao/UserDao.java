package com.svetakvetko.dao;

import com.svetakvetko.domain.User;
import java.util.Collection;

public interface UserDao {

    void create(User user);

    void delete(Long userId);

    User findById(Long userId);

    User findByLogin(String userLogin);

    boolean isExist(String userLogin);

    Collection<User> findAll();

    User update(User user);
}
