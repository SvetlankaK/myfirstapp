package com.svetakvetko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequestMapping(value = "/logout")
@Controller
public class LogoutController {

    @GetMapping
    public ModelAndView logoutUser(HttpServletRequest request, ModelAndView modalAndView) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("userLogin");
            session.invalidate();
        }
        return new ModelAndView("redirect:/welcome");
    }
}
