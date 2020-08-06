package com.svetakvetko.controller;

import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value = "/delete")
@Controller
public class DeleteUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView deleteUser(@RequestParam("user") String userLogin) {
        User user = userService.findByLogin(userLogin);
        userService.delete(user.getUserId());
        return new ModelAndView("redirect:/users");
    }
}
