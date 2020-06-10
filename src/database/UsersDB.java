package database;
import domain.User;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import static database.RoleEnum.*;

public class UsersDB {


    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    static {
        UsersDB.add(new User("kat17", "драсте", USER_ACCESS.getName(), "cat1717@mail.ru", "Анна", "Иванова", 200, "10.10.1999"));
        UsersDB.add(new User("leadss", "fdeefe", USER_ACCESS.getName(), "liveliver@gmail.com", "Максим", "Вешалкин", 550, "07.09.1990"));
        UsersDB.add(new User("great29", "совсемнегениальныйпароль", USER_ACCESS.getName(), "football_player@gmail.com", "Вадим", "Бабурченков", 320, "17.01.1880"));
        UsersDB.add(new User("honeyMOON", "пароль", USER_ACCESS.getName(), "smirnovy@gmail.com", "Василиса", "Веббер", 400, "15.11.1988"));
        UsersDB.add(new User("Света", "пароль", ADMIN_ACCESS.getName(), "svetlanka17@gmail.com", "Светлана", "Кветко", 100000, "06.04.2000"));
    }

    public static Collection<User> getUsers() {
        return users.values();
    }

    public static boolean getUserByLoginAndPassword(final String userLogin, final String password) {
        if (getPasswordByUserLogin(userLogin).equals(password)) {
            return true;
        }
        return false;
    }

    public static String getRole(final String userLogin) {
        return users.get(userLogin).getRole();
    }

    public static void changeUserPassword(final String userLogin, String newPassword) {
        users.get(userLogin).setPassword(newPassword);

    }


    public static String getPasswordByUserLogin(final String userLogin) {
        return users.get(userLogin).getPassword();
    }

    public static void add(final User user) {
        users.put(user.getUserLogin(), user);
    }


    public static boolean isExist(final String userLogin) {
        return users.containsKey(userLogin);
    }

    public static User getByLogin(String login) {
        return users.get(login);
    }

    public static void deleteUser(final String userLogin) {
        users.remove(userLogin);
    }

}