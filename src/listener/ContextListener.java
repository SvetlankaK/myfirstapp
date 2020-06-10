package listener;

import database.UsersDB;
import domain.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


import static database.RoleEnum.*;

@WebListener
public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UsersDB.add(new User("kat17", "драсте", USER_ACCESS.getName(), "cat1717@mail.ru", "Анна", "Иванова", 200, "10.10.1999"));
        UsersDB.add(new User("leadss", "fdeefe", USER_ACCESS.getName(), "liveliver@gmail.com", "Максим", "Вешалкин", 550, "07.09.1990"));
        UsersDB.add(new User("great29", "совсемнегениальныйпароль", USER_ACCESS.getName(), "football_player@gmail.com", "Вадим", "Бабурченков", 320, "17.01.1880"));
        UsersDB.add(new User("honeyMOON", "пароль", USER_ACCESS.getName(), "smirnovy@gmail.com", "Василиса", "Веббер", 400, "15.11.1988"));
        UsersDB.add(new User("Света", "пароль", ADMIN_ACCESS.getName(), "svetlanka17@gmail.com", "Светлана", "Кветко", 100000, "06.04.2000"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
