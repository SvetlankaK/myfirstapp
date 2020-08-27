package com.svetakvetko.controller;

import com.svetakvetko.domain.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login-error")
public class ErrorController {
    //ну бля не понимаю, как туда засунуть эту ошибку, чтоб она реально засунулась
    @GetMapping()
    public ModelAndView loginError(@SessionAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) AuthenticationException authEx) {
        String errorMessage = authEx != null ? authEx.getMessage() : "[unknown error]";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", new User());
        modelAndView.setViewName("login");
        modelAndView.addObject("errorMessage", errorMessage);
        return modelAndView;
    }

}
