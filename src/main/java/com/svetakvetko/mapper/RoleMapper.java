package com.svetakvetko.mapper;

import com.svetakvetko.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface RoleMapper {

    List<Role> getRolesById(Long userId);

    List<Role> getAllPossibleRoles();

    List<Role> getRolesByLogin(String userLogin);

    void deleteRole(Map<String, Object> userLongMap);

    void deleteRolesById(Long userId);

    void addRole(Map<String, Object> userLongMap);

    Long getRoleIdByRoleName(String roleName);

    String getRoleNameByRoleId(Long roleId);
}
