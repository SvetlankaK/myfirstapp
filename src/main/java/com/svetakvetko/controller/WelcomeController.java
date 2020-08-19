package com.svetakvetko.controller;

import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequestMapping(value = "/welcome")
@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView sendWelcomeView(HttpServletRequest req, ModelAndView modelAndView) {
        HttpSession session = req.getSession(true);
        String login = (String) session.getAttribute("userLogin");
        User user = userService.findByLogin(login);
        modelAndView.addObject("roles", user.getRole());
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

}