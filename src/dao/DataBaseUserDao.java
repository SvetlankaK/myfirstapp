package dao;

import database.UsersDB;
import domain.User;
import java.util.*;

public class DataBaseUserDao implements UserDao {

    private static DataBaseUserDao instance;

    private DataBaseUserDao() {
    }

    public static DataBaseUserDao getInstance() {
        if (instance == null) {
            instance = new DataBaseUserDao();
        }
        return instance;
    }

    @Override
    public void create(User user) {
        UsersDB.create(user);
    }

    @Override
    public void delete(Long id) {
        UsersDB.deleteUser(id);
    }


    @Override
    public User findById(Long userId) {
        return UsersDB.findById(userId);
    }

    @Override
    public boolean isExist(String userLogin) {
        return UsersDB.isExist(userLogin);
    }

    @Override
    public Collection<User> findAll() {
        return UsersDB.findAll();
    }

    @Override
    public User update(User user) {
        return UsersDB.update(user);
    }

    @Override
    public User findByLogin(String userLogin) {
        return UsersDB.findByLogin(userLogin);
    }
}