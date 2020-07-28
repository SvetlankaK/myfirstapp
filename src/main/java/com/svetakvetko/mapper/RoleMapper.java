package com.svetakvetko.mapper;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMapper {

    List<Role> getRolesById(Long userId);

    List<Role> getAllPossibleRoles();

    List<Role> getRolesByLogin(String userLogin);

    void deleteRole(User user, Long roleId);

    void addRole(User user, Long roleId);
}
