package com.svetakvetko.controller;


import com.svetakvetko.domain.User;
import com.svetakvetko.dto.EditPasswordDto;
import com.svetakvetko.service.UserService;
import com.svetakvetko.validation.EditPasswordGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView sendEditPasswordView(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();
        modelAndView.addObject("editPasswordDto", new EditPasswordDto(user.getUserId()));
        modelAndView.setViewName("editPassword");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView editPassword(@Validated(EditPasswordGroup.class) @ModelAttribute("editPasswordDto") EditPasswordDto editPasswordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView();
        }
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findById(editPasswordDto.getUserId());
        if (passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(editPasswordDto.getOldPassword()))) {
            boolean isChanged = userService.changePassword(editPasswordDto);
            if (!isChanged) {
                return modelAndView.addObject("wrongRepeat", true);
            }
        } else {
            return modelAndView.addObject("wrongOldPassword", true);
        }
        return modelAndView.addObject("passwordHasChanged", true);
    }
}
