package servlet;


import domain.User;
import factory.ServiceFactory;
import service.UserService;
import util.URLUtilities;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(urlPatterns = "/delete.jhtml")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> query = URLUtilities.getQuery(req);
        User user = userService.findByLogin(query.get("user"));
        userService.delete(user.getUserId());
        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}
