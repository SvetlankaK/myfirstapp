package com.svetakvetko.controller;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.util.ServletUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;

import static com.svetakvetko.database.RoleEnum.USER_ACCESS;


@RequestMapping(value = "/registration")
@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;


    @GetMapping
    public ModelAndView sendRegistrationView(ModelAndView modelAndView) {
        modelAndView.getModel().put("user", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        long id = Long.parseLong(String.valueOf(userService.findAll().size() + 2)); //TODO generate in db
        if (userService.isExist(user.getUserLogin())) {
            return this.sendRegistrationView(ServletUtilities.createErrorMessage("User login already exists", modelAndView));
        } else {
            userService.create(new User(id, user.getUserLogin(), user.getPassword(), Collections.singletonList(new Role(1, USER_ACCESS.getName())), user.getEmail(), user.getName(), user.getSurname(), user.getSalary(), user.getDateOfBirth()));
            ServletUtilities.createSession(new User(id, user.getUserLogin(), user.getPassword(), Collections.singletonList(new Role(1, USER_ACCESS.getName())), user.getEmail(), user.getName(), user.getSurname(), user.getSalary(), user.getDateOfBirth()), request);
            return new ModelAndView("redirect:/welcome");
        }

    }
}





