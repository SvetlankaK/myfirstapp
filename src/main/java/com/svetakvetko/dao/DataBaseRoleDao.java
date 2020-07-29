//package com.svetakvetko.dao;
//
//import com.svetakvetko.database.DataBaseConfiguration;
//import com.svetakvetko.domain.Role;
//import com.svetakvetko.domain.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//@Repository
//public class DataBaseRoleDao implements RoleDao {
//
//    private static Logger log = Logger.getLogger(DataBaseRoleDao.class.getName());
//
//    @Autowired
//    private DataBaseConfiguration dataBaseConfiguration;
//
//
//    public List<Long> getAllId(User user) {
//        List<Long> idList = new ArrayList<>();
//        for (int i = 0; i < user.getRole().size(); i++) {
//            idList.add(user.getRole().get(i).getId());
//        }
//        return idList;
//    }
//
//    public List<Role> getRolesById(Long userId) {
//        Connection connection = null;
//        List<Role> roles = new ArrayList<>();
//        try {
//            connection = dataBaseConfiguration.getDBConnection();
//            PreparedStatement ps = connection.prepareStatement("SELECT  \"webapp\".\"role\".id,  \"webapp\".\"role\".rolename FROM \"webapp\".\"user_roles\" "
//                    + " INNER JOIN \"webapp\".\"USER\" ON (\"webapp\".\"USER\".userid=\"webapp\".\"user_roles\".user_id) "
//                    + " INNER JOIN  \"webapp\".\"role\" ON (\"webapp\".\"role\".id=\"webapp\".\"user_roles\".role_id)"
//                    + " WHERE user_id=?");
//            ps.setLong(1, userId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Role role = extractRoleFromResultSet(rs);
//                roles.add(role);
//            }
//            return roles;
//        } catch (SQLException e) {
//            log.log(Level.WARNING, "Exception: ", e);
//        } finally {
//            dataBaseConfiguration.closeDBConnection(connection);
//        }
//        return Collections.emptyList();
//    }
//
//
//    public List<Role> getRolesByLogin(String userLogin) {
//        Connection connection = null;
//        List<Role> roles = new ArrayList<>();
//        try {
//            connection = dataBaseConfiguration.getDBConnection();
//            PreparedStatement ps = connection.prepareStatement("SELECT  \"webapp\".\"role\".id,  \"webapp\".\"role\".rolename FROM \"webapp\".\"user_roles\" "
//                    + " INNER JOIN \"webapp\".\"USER\" ON (\"webapp\".\"USER\".userid=\"webapp\".\"user_roles\".user_id) "
//                    + " INNER JOIN  \"webapp\".\"role\" ON (\"webapp\".\"role\".id=\"webapp\".\"user_roles\".role_id)"
//                    + " WHERE \"webapp\".\"USER\".userLogin=?");
//            ps.setString(1, userLogin);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Role role = extractRoleFromResultSet(rs);
//                roles.add(role);
//            }
//            return roles;
//        } catch (SQLException e) {
//            log.log(Level.WARNING, "Exception: ", e);
//        } finally {
//            dataBaseConfiguration.closeDBConnection(connection);
//        }
//        return Collections.emptyList();
//    }
//
//    public List<Role> getAllPossibleRoles() {
//        Connection connection = null;
//        List<Role> roles = new ArrayList<>();
//        try {
//            connection = dataBaseConfiguration.getDBConnection();
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"webapp\".\"role\" ");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Role role = extractRoleFromResultSet(rs);
//                roles.add(role);
//            }
//            return roles;
//        } catch (SQLException e) {
//            log.log(Level.WARNING, "Exception: ", e);
//        } finally {
//            dataBaseConfiguration.closeDBConnection(connection);
//        }
//        return Collections.emptyList();
//    }
//
//    public Role extractRoleFromResultSet(ResultSet rs) {
//        Role role = new Role();
//        try {
//            role.setId(rs.getLong("id"));
//            role.setRoleName(rs.getString("roleName"));
//        } catch (SQLException e) {
//            log.log(Level.WARNING, "Exception: ", e);
//        }
//        return role;
//    }
//
//    public List<Long> getRolesIdDB(List<Role> roles) {
//        return roles.stream().map(Role::getId).collect(Collectors.toList());
//    }
//
//    public void deleteRoles(User user) {
//        for (int i = 0; i < getRolesById(user.getUserId()).size(); i++) {
//            Set<Long> similar = new HashSet<>(getAllId(user));
//            Set<Long> different = new HashSet<>();
//            different.addAll(getAllId(user));
//            different.addAll(getRolesIdDB(getRolesById(user.getUserId())));
//            different.removeAll(similar);
//            Connection connection = null;
//            try {
//                for (Long id : different) {
//                    connection = dataBaseConfiguration.getDBConnection();
//                    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"webapp\".\"user_roles\" where user_id=? AND role_id=?");
//                    preparedStatement.setLong(1, user.getUserId());
//                    preparedStatement.setLong(2, id);
//                    preparedStatement.executeUpdate();
//                }
//
//            } catch (SQLException e) {
//                log.log(Level.WARNING, "Exception: ", e);
//            } finally {
//                dataBaseConfiguration.closeDBConnection(connection);
//            }
//
//        }
//
//    }
//}
