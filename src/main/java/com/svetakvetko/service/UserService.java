package com.svetakvetko.service;

import com.svetakvetko.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;


public interface UserService extends UserDetailsService {
    void create(User user);

    void delete(Long userId);

    User findById(Long UserId);

    boolean isExist(String userLogin);

    Collection<User> findAll();

    void update(User user);

    User findByLogin(String userLogin);


}
