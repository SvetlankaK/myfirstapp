package com.svetakvetko.dao;

import com.svetakvetko.database.DataBaseConfiguration;
import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class DataBaseRoleDao {

    private static Logger log = Logger.getLogger(DataBaseRoleDao.class.getName());

    @Autowired
    private DataBaseConfiguration dataBaseConfiguration;

    @Autowired
    private DataBaseUserDao dataBaseUserDao;


    public static List<Long> getAllId(User user) {
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < user.getRole().size(); i++) {
            idList.add(user.getRole().get(i).getId());
        }
        return idList;
    }

    public List<Role> getRolesById(Long userId) {
        Connection connection = null;
        List<Role> roles = new ArrayList<>();
        try {
            connection = dataBaseConfiguration.getDBConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT  \"webapp\".\"role\".id,  \"webapp\".\"role\".rolename FROM \"webapp\".\"user_roles\" "
                    + " INNER JOIN \"webapp\".\"USER\" ON (\"webapp\".\"USER\".userid=\"webapp\".\"user_roles\".user_id) "
                    + " INNER JOIN  \"webapp\".\"role\" ON (\"webapp\".\"role\".id=\"webapp\".\"user_roles\".role_id)"
                    + " WHERE user_id=?");
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = dataBaseUserDao.extractRoleFromResultSet(rs);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }


    public List<Role> getRolesByLogin(String userLogin) {
        Connection connection = null;
        List<Role> roles = new ArrayList<>();
        try {
            connection = dataBaseConfiguration.getDBConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT  \"webapp\".\"role\".id,  \"webapp\".\"role\".rolename FROM \"webapp\".\"user_roles\" "
                    + " INNER JOIN \"webapp\".\"USER\" ON (\"webapp\".\"USER\".userid=\"webapp\".\"user_roles\".user_id) "
                    + " INNER JOIN  \"webapp\".\"role\" ON (\"webapp\".\"role\".id=\"webapp\".\"user_roles\".role_id)"
                    + " WHERE \"webapp\".\"USER\".userLogin=?");
            ps.setString(1, userLogin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = dataBaseUserDao.extractRoleFromResultSet(rs);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }
}
