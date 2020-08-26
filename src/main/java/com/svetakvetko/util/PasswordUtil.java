package com.svetakvetko.util;

import com.svetakvetko.domain.User;
import com.svetakvetko.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PasswordUtil {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public void encodeExistingPassword() {
        List<User> users = new ArrayList<>(userMapper.findAll());
        Map<String, Object> userDataInMap = new HashMap();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            String oldPassword = userMapper.getPasswordById(user.getUserId());
            String encodedPassword = passwordEncoder.encode(oldPassword);
            userDataInMap.put("userId", user.getUserId());
            userDataInMap.put("password", encodedPassword);
            userMapper.setPassword(userDataInMap);
        }
    }

}
