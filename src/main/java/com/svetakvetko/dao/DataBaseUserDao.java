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
import java.util.stream.Collectors;

@Repository
public class DataBaseUserDao implements UserDao {

    @Autowired
    private DataBaseConfiguration dataBaseConfiguration;

    @Autowired
    private DataBaseRoleDao dataBaseRoleDao;

    private static Logger log = Logger.getLogger(DataBaseUserDao.class.getName());

    @Override
    public void create(User user) {
        Connection connection = null;
        try {
            connection = dataBaseConfiguration.getDBConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO \"webapp\".\"USER\" VALUES (?,?,?,?,?,?,?,?)");
            setUserPreparedStatements(user, ps);
            ps.executeUpdate();
            for (int i = 0; i < user.getRole().size(); i++) {
                PreparedStatement pst = connection.prepareStatement("INSERT INTO \"webapp\".\"user_roles\" VALUES ( ?, ?)");
                pst.setLong(1, user.getUserId());
                pst.setLong(2, user.getRole().get(i).getId());
                pst.executeUpdate();
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
            PreparedStatement ps = connection.prepareStatement("DELETE FROM \"webapp\".\"USER\" WHERE userId=?"); //TODO Fixme
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        } finally {
            dataBaseConfiguration.closeDBConnection(connection);
        }
    }

    @Override
    public User findById(Long userId) {
        Connection connection = null;
        try {
            connection = dataBaseConfiguration.getDBConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"webapp\".\"USER\" WHERE userId=?");
            ps.setLong(1, userId);
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
        Connection connection = null;
        try {
            connection = dataBaseConfiguration.getDBConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"webapp\".\"USER\"  WHERE userLogin=?");
            ps.setString(1, userLogin);
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
        Connection connection = null;
        try {
            connection = dataBaseConfiguration.getDBConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE \"webapp\".\"USER\" SET userid=?, username=?,userLogin=?,usersurname=?,userpassword=?,useremail=?,userdateOfBirth=?,userSalary=? WHERE userId=?");
            setUserPreparedStatements(user, ps);
            ps.setLong(9, user.getUserId());
            ps.executeUpdate();
            for (int i = 0; i < user.getRole().size(); i++) {
                PreparedStatement pst = connection.prepareStatement("INSERT INTO \"webapp\".\"user_roles\" ( user_id, role_id) VALUES (?,?) ON CONFLICT DO NOTHING");
                pst.setLong(1, user.getUserId());
                pst.setLong(2, user.getRole().get(i).getId());
                pst.executeUpdate();
            }
            if (user.getRole().size() != dataBaseRoleDao.getRolesById(user.getUserId()).size()) {
                deleteRoles(user, connection);
            }
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"webapp\".\"USER\" WHERE userId=?");
            ps.setLong(1, user.getUserId());
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
        Connection connection = null;
        try {
            connection = dataBaseConfiguration.getDBConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"webapp\".\"USER\" WHERE userLogin=?");
            ps.setString(1, userLogin);
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


    public List<Long> getRolesIdDB(List<Role> roles) {
        return roles.stream().map(Role::getId).collect(Collectors.toList());
    }

    private void deleteRoles(User user, Connection connection) {
        for (int i = 0; i < dataBaseRoleDao.getRolesById(user.getUserId()).size(); i++) {
            Set<Long> similar = new HashSet<>(DataBaseRoleDao.getAllId(user));
            Set<Long> different = new HashSet<>();
            different.addAll(DataBaseRoleDao.getAllId(user));
            different.addAll(getRolesIdDB(dataBaseRoleDao.getRolesById(user.getUserId())));
            different.removeAll(similar);
            try {
                for (Long id : different) {
                    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"webapp\".\"user_roles\" where user_id=? AND role_id=?");
                    preparedStatement.setLong(1, user.getUserId());
                    preparedStatement.setLong(2, id);
                    preparedStatement.executeUpdate();
                }

            } catch (SQLException e) {
                log.log(Level.WARNING, "Exception: ", e);
            } finally {
                dataBaseConfiguration.closeDBConnection(connection);
            }

        }

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
            user.setRole(dataBaseRoleDao.getRolesById(rs.getLong("userId")));
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