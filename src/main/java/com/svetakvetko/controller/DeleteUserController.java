package com.svetakvetko.controller;


import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.util.URLUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@RequestMapping(value = "/delete")
@Controller
public class DeleteUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/delete{somePath}")
    public ModelAndView deleteUser(@PathVariable("somePath") String somePath) {
        Map<String, String> query = URLUtilities.getQuery(somePath);
        User user = userService.findByLogin(query.get("user"));
        userService.delete(user.getUserId());
        return new ModelAndView("redirect:/users");//нет контекст паса и jhtml
    }
}
