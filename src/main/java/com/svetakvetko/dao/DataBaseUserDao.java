package com.svetakvetko.dao;

import com.svetakvetko.database.DataBaseConfiguration;
import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
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
            PreparedStatement ps = connection.prepareStatement("INSERT INTO \"webapp\".\"USER\" VALUES (?,?,?,?,?,?,?,?)");
            setUserPreparedStatements(user, ps);
            ps.executeUpdate();
            while (user.getRole().listIterator().hasNext()) {
                ps = connection.prepareStatement("INSERT INTO \"webapp\".\"user_roles\" VALUES ( ?, ?)");
                ps.setLong(1, user.getUserId());
                ps.setLong(2, user.getRole().listIterator().next().getId());
                ps.executeUpdate();
            }
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
            PreparedStatement ps = connection.prepareStatement(String.format("UPDATE \"webapp\".\"USER\" SET userid=?, username=?,userLogin=?,usersurname=?,userpassword=?,useremail=?,userdateOfBirth=?,userSalary=? WHERE userId=%d", user.getUserId()));
            setUserPreparedStatements(user, ps);
            ps.executeUpdate();
            ps = connection.prepareStatement(String.format("UPDATE \"webapp\".\"user_roles\" SET role_id=? where user_id=%d", user.getUserId()));
            while (user.getRole().listIterator().hasNext()) {
                ps = connection.prepareStatement("UPDATE \"webapp\".\"user_roles\" VALUES ( ?, ?)");
                ps.setLong(1, user.getUserId());
                ps.setLong(2, user.getRole().listIterator().next().getId());
                ps.executeUpdate();
            }
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

    public List<Role> getRoles(Long userId) {
        Connection connection = dataBaseConfiguration.getDBConnection();
        List<Role> roles = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT  \"webapp\".\"role\".id,  \"webapp\".\"role\".rolename FROM \"webapp\".\"user_roles\" "
                    + " INNER JOIN \"webapp\".\"USER\" ON (\"webapp\".\"USER\".userid=\"webapp\".\"user_roles\".user_id) "
                    + " INNER JOIN  \"webapp\".\"role\" ON (\"webapp\".\"role\".id=\"webapp\".\"user_roles\".role_id)"
                    + " WHERE user_id=%d", userId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = extractRoleFromResultSet(rs);
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


    private User extractUserFromResultSet(ResultSet rs) {
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
            user.setRole(getRoles(rs.getLong("userId")));
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return user;
    }

    public Role extractRoleFromResultSet(ResultSet rs) {
        Role role = new Role();
        try {
            role.setId(rs.getLong("id"));
            role.setRoleName(rs.getString("roleName"));
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return role;
    }

    private void setUserPreparedStatements(User user, PreparedStatement ps) {
        try {
            ps.setLong(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUserLogin());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getDateOfBirth());
            ps.setDouble(8, user.getSalary());
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
    }

}