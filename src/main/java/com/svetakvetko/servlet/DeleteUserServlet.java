package com.svetakvetko.servlet;


import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import com.svetakvetko.util.URLUtilities;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(urlPatterns = "/delete.jhtml")
public class DeleteUserServlet extends HttpServlet {

    @Autowired
    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> query = URLUtilities.getQuery(req);
        User user = userService.findByLogin(query.get("user"));
        userService.delete(user.getUserId());
        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}
