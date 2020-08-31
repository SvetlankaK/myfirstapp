package com.svetakvetko.controller;


import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.validation.EditPasswordGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value = "/editPassword")
@Controller
public class EditPasswordController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ModelAndView sendEditPasswordView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("editPassword");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView editPassword(@Validated(EditPasswordGroup.class) @ModelAttribute("user") User userView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView();
        }
        User user = userService.findByLogin(userView.getUserLogin());
        userService.update(user);
        return new ModelAndView("redirect:/users");
    }
}
