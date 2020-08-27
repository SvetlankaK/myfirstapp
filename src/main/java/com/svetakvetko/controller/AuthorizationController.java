package com.svetakvetko.controller;


import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.validation.AuthorizationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value = "/login")
@Controller
public class AuthorizationController {

    @Autowired
    private UserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView sendLoginView(ModelAndView modelAndView) {
        modelAndView.getModel().put("user", new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }


//    @GetMapping("/login-error")
//    public String login(ModelAndView modelAndView) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String errorMessage = null;
//
//        if (auth.getCredentials() != null) {
//            AuthenticationException ex =(AuthenticationException) WebAttributes.AUTHENTICATION_EXCEPTION;
//            if (ex != null) {
//                errorMessage = ex.getMessage();
//            }
//        }
//        modelAndView.addObject("errorMessage", errorMessage);
//        return "login";
//    }

    @PostMapping
    public ModelAndView loginUser(@RequestParam(value = "error", required = false) @Validated(AuthorizationGroup.class) @ModelAttribute("user") User userView, Errors errors, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        if (userService.isExist(userView.getUserLogin())) {
            User user = userService.findByLogin(userView.getUserLogin());
            if (user.getPassword().equals(passwordEncoder.encode(userView.getPassword()))) {
                return new ModelAndView("redirect:/welcome");
            } else {
                errors.rejectValue("password", "${user.wrongPassword}");
            }
        } else {
            errors.rejectValue("userLogin", "${user.notExist}");
        }
        return modelAndView;
    }

}



