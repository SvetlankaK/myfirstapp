package com.svetakvetko.controller;

import com.svetakvetko.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value = "/welcome")
@Controller
public class WelcomeController {


    @GetMapping
    public ModelAndView sendWelcomeView(ModelAndView modelAndView, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        modelAndView.addObject("roles", user.getRole()); //TODO add user to model add adapt jsp to take it from model instead of session
        modelAndView.addObject("userLogin", user.getUserLogin());
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

}