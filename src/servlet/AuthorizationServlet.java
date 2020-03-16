package servlet;

import database.UsersDB;
import util.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/login.jhtml")
public class AuthorizationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if (UsersDB.isExist(userName)) {
            UsersDB.getPasswordByUserName(userName);
            if (UsersDB.getPasswordByUserName(userName).equals(password)) {
                HttpSession session = ServletUtilities.createSession(userName, password, req);
                resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
            } else {
                this.doGet(ServletUtilities.createErrorMessage("Invalid Password", req), resp);
            }
        } else {
            this.doGet(ServletUtilities.createErrorMessage("User does not exist", req), resp);
        }
    }
}


