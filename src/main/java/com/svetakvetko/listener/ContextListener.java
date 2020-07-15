package com.svetakvetko.listener;

import com.svetakvetko.database.DataBaseConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {


  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    /*DataBaseConfiguration.createSchema();
    DataBaseConfiguration.createDbUserTable();
    DataBaseConfiguration.insertDefaultDataInDbUserTable();*/
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {

  }
}
