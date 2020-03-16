package listener;

import database.UsersDB;
import domain.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UsersDB.add(new User("Света", "оченьгениальныйпароль"));
        UsersDB.add(new User("Саша", "совсемнегениальныйпароль"));
        UsersDB.add(new User("Виктор", "пароль"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
