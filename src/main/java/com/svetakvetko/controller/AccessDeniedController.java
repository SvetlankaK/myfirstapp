package com.svetakvetko.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@RequestMapping(value = "/accessDenied")
@Controller
public class AccessDeniedController {

    @GetMapping
    public ModelAndView accessDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("userLogin", user.getName());
        }
        model.setViewName("accessDenied");
        return model;

    }

}



