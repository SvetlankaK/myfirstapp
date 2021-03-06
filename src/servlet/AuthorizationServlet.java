package servlet;


import domain.User;
import factory.ServiceFactory;
import service.UserService;
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

    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("userLogin");
        String password = req.getParameter("password");
        if (userService.isExist(userLogin)) {
            User user = userService.findByLogin(userLogin);
            Long id = user.getUserId();
            if (user.getPassword().equals(password)) {
                HttpSession session = ServletUtilities.createSession(userLogin, password, id, req);
                resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
            } else {
                this.doGet(ServletUtilities.createErrorMessage("Invalid password", req), resp);
            }

        } else {
            this.doGet(ServletUtilities.createErrorMessage("User doesn't exist", req), resp);
        }
    }
}


