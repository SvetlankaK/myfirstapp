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

@WebServlet(urlPatterns = "/loginedit.jhtml")
public class ChangeProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object oldPassword = req.getSession().getAttribute("password").toString();
        req.setAttribute("oldPassword", oldPassword);
        req.getRequestDispatcher("/WEB-INF/jsp/loginedit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("newPassword");
        HttpSession session = req.getSession(true);
        String user = session.getAttribute("user").toString();
        String oldPassword = session.getAttribute("password").toString();
        if (user != null && password != null) {
            UsersDB.changeUserPassword(user, oldPassword, password);
            session = ServletUtilities.createSession(user, password, req);
            req.setAttribute("PasswordChange", "Your password has been changed");
            req.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(req, resp);
        }

    }

}
