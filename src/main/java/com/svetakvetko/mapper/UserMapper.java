package com.svetakvetko.mapper;

import com.svetakvetko.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    Collection<User> findAll();

    User findById(Long userId);

    User findByLogin(String userLogin);

    Long getIdByLogin(String userLogin);

    void delete(Long userId);

    void update(User user);

    void create(User user);

    String getPasswordById(Long userId);

    void setPassword(Map<String, Object> userLongMap);
}
