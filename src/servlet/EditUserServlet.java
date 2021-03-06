package servlet;

import domain.User;
import factory.ServiceFactory;
import service.UserService;
import util.URLUtilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;


@WebServlet(urlPatterns = "/editUser.jhtml")
public class EditUserServlet extends HttpServlet {

    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<String, String> query = URLUtilities.getQuery(req);
        if (!query.get("action").equals("new")) {
            String userLogin = query.get("user");
            User user = userService.findByLogin(userLogin);
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userLogin = req.getParameter("userLogin").trim();
        User user = userService.findByLogin(userLogin);
        user.setAll(req.getParameter("password").trim(), req.getParameter("access"), req.getParameter("email").trim(), req.getParameter("name").trim(), req.getParameter("surname").trim(), Double.parseDouble(req.getParameter("salary").trim()), req.getParameter("birth"));
        userService.update(user);
        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}


