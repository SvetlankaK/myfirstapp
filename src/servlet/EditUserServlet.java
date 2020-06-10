package servlet;

import database.UsersDB;
import domain.User;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<String, String> query = URLUtilities.getQuery(req);
        if (!query.get("action").equals("new")) {
            String userLogin = query.get("user");
            User user = UsersDB.getByLogin(userLogin);
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userLogin = req.getParameter("userLogin").trim();
        String password = req.getParameter("password").trim();
        String name = req.getParameter("name").trim();
        String surname = req.getParameter("surname").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth");
        String salary = req.getParameter("salary").trim();
        String access = req.getParameter("access");
        System.out.println(access);
        UsersDB.add(new User(userLogin, password, access, email, name, surname, Double.parseDouble(salary), birth));
        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}


