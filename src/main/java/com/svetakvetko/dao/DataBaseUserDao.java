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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class DataBaseUserDao implements UserDao {

    @Autowired
    DataBaseConfiguration dataBaseConfiguration;

    private static Logger log = Logger.getLogger(DataBaseUserDao.class.getName());

    @Override
    public void create(User user) {
        Connection connection = dataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO \"webapp\".\"USER\" VALUES (?, ?, ?,?,?,?,?,?,?)");
            setUserPreparedStatements(user, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
    }


    @Override
    public void delete(Long id) {
        Connection connection = dataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("DELETE FROM \"webapp\".\"USER\" WHERE userId=%d", id));
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
    }


    @Override
    public User findById(Long userId) {
        Connection connection = dataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM \"webapp\".\"USER\" WHERE userId=%d", userId));
            ResultSet roles = getRoles(userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs, roles);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }

    @Override
    public boolean isExist(String userLogin) {
        Connection connection = dataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM \"webapp\".\"USER\"  WHERE userLogin='%s'", userLogin));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
        return false;
    }


    @Override
    public Collection<User> findAll() {
        Connection connection = dataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"webapp\".\"USER\"");
            ResultSet rs = ps.executeQuery();
            Set<User> users = new HashSet<>();
            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }

    @Override
    public User update(User user) {
        Connection connection = dataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("UPDATE \"webapp\".\"USER\" SET userid=?, username=?,userLogin=?,usersurname=?,userpassword=?,userrole=?,useremail=?,userdateOfBirth=?,userSalary=? WHERE userId=%d", user.getUserId()));
            setUserPreparedStatements(user, ps);
            ps.executeUpdate();
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM \"webapp\".\"USER\" WHERE userId=%d", user.getUserId()));
            ResultSet rs = preparedStatement.executeQuery();
            return extractUserFromResultSet(rs);
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }

    @Override
    public User findByLogin(String userLogin) {
        Connection connection = dataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM \"webapp\".\"USER\" WHERE userLogin='%s'", userLogin));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }

    public ResultSet getRoles(Long userId) {
        Connection connection = dataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT rolename FROM \"webapp\".\"user_roles\""
                    + "  INNER JOIN \"webapp\".roles ON (\"webapp\".roles.id=\"webapp\".user_roles.role_id)"
                    + " WHERE userId='%s'\"", userId));
            return ps.executeQuery();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }


    private User extractUserFromResultSet(ResultSet rs, ResultSet roles) {
        User user = new User();
        try {
            user.setUserId(rs.getLong("userId"));
            user.setName(rs.getString("userName"));
            user.setSurname(rs.getString("usersurname"));
            user.setPassword(rs.getString("userpassword"));
            user.setEmail(rs.getString("useremail"));
            user.setDateOfBirth(rs.getString("userDateOfBirth"));
            user.setSalary(rs.getDouble("usersalary"));
            user.setUserLogin(rs.getString("userLogin"));
            user.setRole(Collections.singletonList(roles.getString("userrole")));
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return user;
    }


    private void setUserPreparedStatements(User user, PreparedStatement ps) {
        try {
            ps.setLong(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUserLogin());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole().toString());
            ps.setString(7, user.getEmail());
            ps.setString(8, user.getDateOfBirth());
            ps.setDouble(9, user.getSalary());
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
    }

}