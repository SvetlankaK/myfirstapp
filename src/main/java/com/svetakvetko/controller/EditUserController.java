package com.svetakvetko.controller;


import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.service.RoleService;
import com.svetakvetko.service.UserService;
import com.svetakvetko.util.URLUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping(value = "/editUser")
@Controller
public class EditUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/editUser{somePath}")
    public ModelAndView sendEditUserView(@PathVariable("somePath") String somePath) {
//        resp.setContentType("text/html");
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> query = URLUtilities.getQuery(somePath);
        if (!query.get("action").equals("new")) {
            String userLogin = query.get("user");
            User user = userService.findByLogin(userLogin);
            modelAndView.addObject("user", user);
            List<Role> allPossibleRoles = roleService.getAllPossibleRoles();
            modelAndView.addObject("roles", allPossibleRoles);
        }
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @PostMapping("/editUser")
    public ModelAndView editUser(@ModelAttribute("userForm") User userView, @RequestParam Map<String, String[]> allParams) {
//      Map<String, String[]> parameters = req.getParameterMap(); - как было
// и брала я не из allParams в foreach, а отсюда
        String[] values = null;
        for (Map.Entry<String, String[]> entry : allParams.entrySet()) {
            if (entry.getKey().equals("access")) {
                values = entry.getValue();
                System.out.println(Arrays.toString(values));
            }
        }
        Set<String> userRolesIds = new HashSet<>(Arrays.asList(values));
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


