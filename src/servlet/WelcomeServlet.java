package servlet;

import database.UsersDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/welcome.jhtml")
public class WelcomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String login = (String) session.getAttribute("userLogin");
        String password = (String) session.getAttribute("password");
        req.setAttribute("role", UsersDB.getRole(login));
        req.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(req, resp);

    }


}
