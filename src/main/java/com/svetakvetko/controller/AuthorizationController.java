package com.svetakvetko.controller;


import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.util.ServletUtilities;
import com.svetakvetko.validation.AuthorizationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequestMapping(value = "/login")
@Controller
public class AuthorizationController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ModelAndView sendLoginView(ModelAndView modelAndView) {
        modelAndView.getModel().put("user", new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView loginUser(@Validated(AuthorizationGroup.class) @ModelAttribute("user") User userView, Errors errors, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        if (userService.isExist(userView.getUserLogin())) {
            User user = userService.findByLogin(userView.getUserLogin());
            Long id = user.getUserId();
            if (user.getPassword().equals(userView.getPassword())) {
                HttpSession session = ServletUtilities.createSession(userView.getUserLogin(), userView.getPassword(), id, request);
                return new ModelAndView("redirect:/welcome");
            } else {
                errors.rejectValue("password", "${user.wrongPassword}");
            }
        } else {
            errors.rejectValue("userLogin", "${user.notExist}");
        }
        return modelAndView;
    }
}



