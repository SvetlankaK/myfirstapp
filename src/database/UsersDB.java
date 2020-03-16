package database;

import domain.User;

import java.util.concurrent.ConcurrentHashMap;

import static database.RoleEnum.*;

public class UsersDB {
    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    static {
        add(new User("Света", "паролль", ADMIN_ACCESS.getName()));
        add(new User("Саша", "совсемнегениальныйпароль", USER_ACCESS.getName()));
        add(new User("Виктор", "пароль", USER_ACCESS.getName()));
    }

    public static boolean getUserByLoginAndPassword(final String userName, final String password) {
        if (getPasswordByUserName(userName).equals(password)) {
            return true;
        }
        return false;
    }

    public static String getRole(final String userName) {
        if (getPasswordByUserName(userName).equals(password)) {
            return true;
        }
        return false;
    }

    public static void changeUserPassword(final String userName, final String password, String newPassword) {
        users.put(userName, );

    }


    public static String getPasswordByUserName(final String userName) {
        return users.get(userName).getPassword();
    }

    public static void add(final User user) {
        users.put(user.getUserName(), user);
    }


    public static boolean isExist(final String userName) {
        return users.containsKey(userName);
    }


}