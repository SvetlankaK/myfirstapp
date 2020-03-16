package servlet;

import database.UsersDB;
import domain.User;
import util.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration.jhtml")
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName").trim();
        String password = req.getParameter("password").trim();
        if (userName.equals("") || password.equals("")) {
            this.doGet(ServletUtilities.createErrorMessage("Please fill in all fields", req), resp);
        } else if (UsersDB.isExist(userName)) {
            this.doGet(ServletUtilities.createErrorMessage("This name is already taken, try again", req), resp);
        }
        UsersDB.add(new User(userName, password));
        HttpSession session = ServletUtilities.createSession(userName, password, req);
        resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
    }
}





