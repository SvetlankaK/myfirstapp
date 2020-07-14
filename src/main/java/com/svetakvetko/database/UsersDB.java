package com.svetakvetko.database;

import com.svetakvetko.domain.User;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsersDB {

    private static Logger log = Logger.getLogger(UsersDB.class.getName());


    private static User extractUserFromResultSet(ResultSet rs) {
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
            user.setRole(rs.getString("userrole"));
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return user;
    }


    private static void setUserPreparedStatements(User user, PreparedStatement ps) {
        try {
            ps.setLong(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUserLogin());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getEmail());
            ps.setString(8, user.getDateOfBirth());
            ps.setDouble(9, user.getSalary());
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
    }


    public static User findById(Long id) {
        Connection connection = DataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM \"webapp\".\"USER\" WHERE userId=%d", id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            DataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }

    public static User findByLogin(String userLogin) {
        Connection connection = DataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM \"webapp\".\"USER\" WHERE userLogin='%s'", userLogin));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            DataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }

    public static Set<User> findAll() {
        Connection connection = DataBaseConfiguration.getDBConnection();
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
            DataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }

    public static void create(User user) {
        Connection connection = DataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO \"webapp\".\"USER\" VALUES (?, ?, ?,?,?,?,?,?,?)");
            setUserPreparedStatements(user, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            DataBaseConfiguration.closeDBConnection(connection);
        }
    }


    public static User update(User user) {
        Connection connection = DataBaseConfiguration.getDBConnection();
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
            DataBaseConfiguration.closeDBConnection(connection);
        }
        return null;
    }

    public static void deleteUser(long id) {
        Connection connection = DataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("DELETE FROM \"webapp\".\"USER\" WHERE userId=%d", id));
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            DataBaseConfiguration.closeDBConnection(connection);
        }
    }

    public static boolean isExist(String userLogin) {
        Connection connection = DataBaseConfiguration.getDBConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM \"webapp\".\"USER\"  WHERE userLogin='%s'", userLogin));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            DataBaseConfiguration.closeDBConnection(connection);
        }
        return false;
    }
}