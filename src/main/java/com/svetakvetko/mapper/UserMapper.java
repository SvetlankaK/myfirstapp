package com.svetakvetko.mapper;

import com.svetakvetko.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.Collection;

@Mapper
@Repository
public interface UserMapper {

    Collection<User> findAll();

    User findById(Long userId);

    User findByLogin(String userLogin);

    void delete(Long userId);


    User update(User user);

    void create(User user);
}
