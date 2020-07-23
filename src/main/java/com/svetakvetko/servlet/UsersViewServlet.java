package com.svetakvetko.servlet;

import com.svetakvetko.dao.DataBaseUserDao;
import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@WebServlet(urlPatterns = "/users.jhtml")
public class UsersViewServlet extends HttpServlet {
    @Autowired
    private UserService userService;

    @Autowired
    private DataBaseUserDao dataBaseUserDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users = userService.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);

    }

}
