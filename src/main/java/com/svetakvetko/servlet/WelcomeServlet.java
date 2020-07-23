package com.svetakvetko.servlet;

import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/welcome.jhtml")

public class WelcomeServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        long id = (Long) session.getAttribute("userId");
        User user = userService.findById(id);
        req.setAttribute("roles", user.getRole());
        req.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(req, resp);
    }

}