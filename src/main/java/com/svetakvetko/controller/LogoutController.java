package com.svetakvetko.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@RequestMapping(value = "/logout")
@Controller
public class LogoutController {

    @GetMapping
    public ModelAndView logoutUser() {
        SecurityContextHolder.clearContext();
        return new ModelAndView("redirect:/welcome");
    }
}
