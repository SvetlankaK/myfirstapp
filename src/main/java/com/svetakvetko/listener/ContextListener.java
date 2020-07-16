package com.svetakvetko.listener;

import com.svetakvetko.dao.DataBaseUserDao;
import com.svetakvetko.database.DataBaseConfiguration;

import com.svetakvetko.factory.ServiceFactory;
import com.svetakvetko.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataBaseConfiguration.createSchema();
        DataBaseConfiguration.createDbUserTable();
        if (!userService.isExist("Sveta")) {
            DataBaseConfiguration.insertDefaultDataInDbUserTable();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
