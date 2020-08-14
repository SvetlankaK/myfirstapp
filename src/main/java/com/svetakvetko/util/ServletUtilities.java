package com.svetakvetko.util;

import com.svetakvetko.domain.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class ServletUtilities {
    public static ModelAndView createErrorMessage(String message, ModelAndView modelAndView) {
        modelAndView.addObject("errorMessage", message);
        return modelAndView;
    }

    public static HttpSession createSession(User user, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userLogin", user.getUserLogin());
        session.setAttribute("password", user.getPassword());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("name", user.getName());
        session.setAttribute("surname", user.getSurname());
        session.setAttribute("birth", user.getDateOfBirth());
        session.setAttribute("salary", user.getSalary());
        session.setAttribute("role", user.getRole());
        return session;
    }

    public static HttpSession createSession(String login, String password, Long id, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.setAttribute("userLogin", login);
        session.setAttribute("password", password);
        session.setAttribute("userId", id);
        return session;
    }

    public static void populateError(String field, ModelAndView model, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            model.addObject(field + "Error", bindingResult.getFieldError(field)
                    .getDefaultMessage());
        }
    }
}
