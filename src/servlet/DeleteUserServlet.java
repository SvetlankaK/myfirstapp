package servlet;

import database.UsersDB;
import util.URLUtilities;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(urlPatterns = "/delete.jhtml")
public class DeleteUserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> query = URLUtilities.getQuery(req);
        String userLogin = query.get("user");
        UsersDB.deleteUser(userLogin);
        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}
