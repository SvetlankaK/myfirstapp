package com.svetakvetko.controller;


import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.service.RoleService;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping(value = "/editUser")
@Controller
public class EditUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping
    public ModelAndView sendEditUserView(@RequestParam("user") String userLogin) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findByLogin(userLogin);
        modelAndView.addObject("user", user);
        List<Role> allPossibleRoles = roleService.getAllPossibleRoles();
        modelAndView.addObject("allRoles", allPossibleRoles);
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView editUser(@Valid @ModelAttribute("user") User userView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            this.sendEditUserView(userView.getUserLogin());
        }
        Set<String> userRolesIds = userView.getRole().stream()
                .map(Role::getId)
                .map(String::valueOf)
                .collect(Collectors.toSet());
        List<Role> allPossibleRoles = roleService.getAllPossibleRoles();
        List<Role> userRoles = allPossibleRoles.stream()
                .filter(role -> userRolesIds.contains(String.valueOf(role.getId())))
                .collect(Collectors.toList());
        User user = userService.findByLogin(userView.getUserLogin());
        user.setAll(userView.getPassword(), userRoles, userView.getEmail(), userView.getName(), userView.getSurname(), userView.getSalary(), userView.getDateOfBirth());
        userService.update(user);
        return new ModelAndView("redirect:/users");
    }
}


