package com.svetakvetko.controller;

import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;


@RequestMapping(value = "/users")
@Controller
public class UsersViewController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView sendUsersView(ModelAndView modelAndView) {
        Collection<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users");
        return modelAndView;
    }

}
