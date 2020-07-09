package database;

import domain.User;

import java.sql.*;
import java.util.*;


public class UsersDB {

    private static Connection connection = DataBaseConfiguration.getDBConnection();
    private static final List<String> users = new ArrayList<>();


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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }


    public static User findById(Long id) {
        try {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM \"webapp\".\"USER\" WHERE userId=" + id);
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User findByLogin(String userLogin) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"webapp\".\"USER\" WHERE userLogin='" + userLogin + "'");
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Set<User> findAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"webapp\".\"USER\"");
            Set<User> users = new HashSet<>();
            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void create(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO \"webapp\".\"USER\" VALUES (?, ?, ?,?,?,?,?,?,?)");
            setUserPreparedStatements(user, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static User update(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE \"webapp\".\"USER\" SET userid=?, username=?,userLogin=?,usersurname=?,userpassword=?,userrole=?,useremail=?,userdateOfBirth=?,userSalary=? WHERE userId=" + user.getUserId());
            setUserPreparedStatements(user, ps);
            ps.executeUpdate();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"webapp\".\"USER\" WHERE userId=" + user.getUserId());
            return extractUserFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteUser(long id) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM \"webapp\".\"USER\" WHERE userId=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isExist(String userLogin) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"webapp\".\"USER\"  WHERE userLogin='" + userLogin + "'");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}