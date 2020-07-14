package com.svetakvetko.factory;

import com.svetakvetko.dao.DataBaseUserDao;
import com.svetakvetko.dao.UserDao;


public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public UserDao getUserDao() {
        return DataBaseUserDao.getInstance();
    }
}
