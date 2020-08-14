package com.svetakvetko.controller;


import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.util.ServletUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RequestMapping(value = "/login")
@Controller
@SessionAttributes("user")
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
    public ModelAndView loginUser(@Valid @ModelAttribute("user") User userView, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return this.sendLoginView(modelAndView);
        }
        if (userService.isExist(userView.getUserLogin())) {
            User user = userService.findByLogin(userView.getUserLogin());
            Long id = user.getUserId();
            if (user.getPassword().equals(userView.getPassword())) {
                HttpSession session = ServletUtilities.createSession(userView.getUserLogin(), userView.getPassword(), id, request);
                return new ModelAndView("redirect:/welcome");
            } else {
                this.sendLoginView(ServletUtilities.createErrorMessage("Invalid password", modelAndView));
            }
        } else {
            this.sendLoginView(ServletUtilities.createErrorMessage("User doesn't exist", modelAndView));
        }
        return modelAndView;

    }
}



