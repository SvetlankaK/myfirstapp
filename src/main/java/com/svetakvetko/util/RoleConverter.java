package com.svetakvetko.util;

import com.svetakvetko.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String source) {
        Role role = new Role();
        role.setId(Long.parseLong(source));
        return role;

    }
}