package com.svetakvetko.factory;

import com.svetakvetko.service.UserService;
import com.svetakvetko.service.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }
}
