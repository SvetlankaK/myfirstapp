package com.svetakvetko.controller;


import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.util.ServletUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView loginUser(@ModelAttribute("user") User userView, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isExist(userView.getUserLogin())) {
            User user = userService.findByLogin(userView.getUserLogin());
            Long id = user.getUserId();
            if (user.getPassword().equals(userView.getPassword())) {
                HttpSession session = ServletUtilities.createSession(userView.getUserLogin(), userView.getPassword(), id, request);
                return new ModelAndView("redirect:/welcome");
                //TODO как редиректили раньше: req.getContextPath() + "/welcome.jhtml");

            } else {
                this.sendLoginView(ServletUtilities.createErrorMessage("Invalid password", modelAndView));
            }
        } else {
            this.sendLoginView(ServletUtilities.createErrorMessage("User doesn't exist", modelAndView));
        }
        return modelAndView;//TODO ето некая хуйня шоб не светилось, но так явно не надо((

    }
}



