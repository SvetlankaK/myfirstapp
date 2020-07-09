package util;

import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class ServletUtilities {
    public static HttpServletRequest createErrorMessage(String message, HttpServletRequest req) {
        req.setAttribute("errorMessage", message);
        return req;
    }

    public static HttpSession createSession(User user, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userLogin", user.getUserLogin());
        session.setAttribute("password", user.getPassword());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("name", user.getName());
        session.setAttribute("surname", user.getSurname());
        session.setAttribute("birth", user.getDateOfBirth());
        session.setAttribute("salary", user.getSalary());
        return session;
    }

    public static HttpSession createSession(String login, String password, Long id, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.setAttribute("userLogin", login);
        session.setAttribute("password", password);
        session.setAttribute("userId", id);
        return session;
    }
}
