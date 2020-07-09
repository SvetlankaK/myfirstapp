package factory;

import dao.DataBaseUserDao;
import dao.UserDao;


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
