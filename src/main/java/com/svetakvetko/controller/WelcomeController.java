package com.svetakvetko.controller;

import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RequestMapping(value = "/welcome")
@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView sendWelcomeView(HttpServletRequest req, ModelAndView modelAndView, Authentication authentication) {
       /* HttpSession session = req.getSession(true);
        String login = (String) session.getAttribute("userLogin");*/
        User user = (User) authentication.getPrincipal();
        modelAndView.addObject("roles", user.getRole()); //TODO add user to model add adapt jsp to take it from model instead of session
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

}