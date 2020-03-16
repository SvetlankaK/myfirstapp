package database;

import domain.User;

import java.util.concurrent.ConcurrentHashMap;

public class UsersDB {
    private static final ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>();

    static {
        add(new User("Света", "оченьгениальныйпароль"));
        add(new User("Саша", "совсемнегениальныйпароль"));
        add(new User("Виктор", "пароль"));
    }
    public static boolean getUserByLoginAndPassword(final String userName, final String password) {
        if (getPasswordByUserName(userName).equals(password)) {
            return true;
        }
        return false;
    }


    public static void changeUserPassword(final String userName, final String password, String newPassword) {
        users.put(userName, newPassword);

    }


    public static String getPasswordByUserName(final String userName) {
        return users.get(userName);
    }

    public static void add(final User user) {
        users.put(user.getUserName(), user.getPassword());
    }


    public static boolean isExist(final String userName) {
        return users.containsKey(userName);
    }


}
