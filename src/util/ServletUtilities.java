package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class ServletUtilities {
    public static HttpServletRequest createErrorMessage(String message, HttpServletRequest req) {
        req.setAttribute("errorMessage", message);
        return req;
    }

    public static HttpSession createSession(String name, String password, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.setAttribute("user", name);
        session.setAttribute("password", password);
        return session;
    }
}
