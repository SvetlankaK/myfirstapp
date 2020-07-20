package com.svetakvetko.listener;


import com.svetakvetko.database.DataBaseConfiguration;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Autowired
    private UserService userService;

    @Autowired
    private DataBaseConfiguration dataBaseConfiguration;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        dataBaseConfiguration.createSchema();
        dataBaseConfiguration.createDbUserTable();
        if (!userService.isExist("Sveta")) {
            dataBaseConfiguration.insertDefaultDataInDbUserTable();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
