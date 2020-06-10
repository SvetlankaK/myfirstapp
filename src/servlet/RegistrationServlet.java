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

import static database.RoleEnum.*;

@WebServlet(urlPatterns = "/registration.jhtml")
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("userLogin").trim();
        String password = req.getParameter("password").trim();
        String name = req.getParameter("name").trim();
        String surname = req.getParameter("surname").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth");
        String salary = req.getParameter("salary").trim();
        if (UsersDB.isExist(userLogin)) {
            this.doGet(ServletUtilities.createErrorMessage("This name is already taken, try again", req), resp);
        }
        UsersDB.add(new User(userLogin, password, USER_ACCESS.getName(), email, name, surname, Double.parseDouble(salary), birth));
        HttpSession session = ServletUtilities.createSession(new User(userLogin, password, USER_ACCESS.getName(), email, name, surname, Double.parseDouble(salary), birth), req);
        resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
    }
}





