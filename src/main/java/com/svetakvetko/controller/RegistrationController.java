package com.svetakvetko.controller;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.validation.RegistrationGroup;
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
    public ModelAndView registerUser(@Validated(RegistrationGroup.class) @ModelAttribute("user") User user, BindingResult bindingResult, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        if (userService.isExist(user.getUserLogin())) {
            errors.rejectValue("userLogin", "${user.exist}");
        } else {
            userService.create(new User(user.getUserLogin(), user.getPassword(), Collections.singletonList(new Role(1, USER_ACCESS.getName())), user.getEmail(), user.getName(), user.getSurname(), user.getSalary(), user.getDateOfBirth()));
            return new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }
}





