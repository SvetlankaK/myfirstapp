package com.svetakvetko.controller;

import com.svetakvetko.domain.Role;
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
import java.util.Collections;

import static com.svetakvetko.database.RoleEnum.USER_ACCESS;


@RequestMapping(value = "/registration")
@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;


    @GetMapping
    public ModelAndView sendRegistrationView() {

        return new ModelAndView("registration");
    }

    @PostMapping
    public ModelAndView registerUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        long id = Long.parseLong(String.valueOf(userService.findAll().size() + 2)); //TODO generate in db
        if (userService.isExist(user.getUserLogin())) {
            return new ModelAndView("redirect:/registration");
        } else {
            userService.create(new User(id, user.getUserLogin(), user.getPassword(), Collections.singletonList(new Role(1, USER_ACCESS.getName())), user.getEmail(), user.getName(), user.getSurname(), user.getSalary(), user.getDateOfBirth()));

            ServletUtilities.createSession(new User(id, user.getUserLogin(), user.getPassword(), Collections.singletonList(new Role(1, USER_ACCESS.getName())), user.getEmail(), user.getName(), user.getSurname(), user.getSalary(), user.getDateOfBirth()), request);
            return new ModelAndView("redirect:/welcome");
        }

    }
}





