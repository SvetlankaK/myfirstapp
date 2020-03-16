package filter;

import database.UsersDB;
import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@WebFilter(urlPatterns = "/*")
public class SecurityFilter implements Filter {

    private List<String> allowedLinks;

    @Override
    public void init(FilterConfig fConfig) {
        allowedLinks = new ArrayList<>();
        Collections.addAll(allowedLinks, "/", "/login.jhtml", "/registration.jhtml");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean availableRequest = false;
        for (String link : allowedLinks) {
            if (link.equals("/")) {
                if (request.getRequestURI().equals(request.getContextPath() + link)) {
                    availableRequest = true;
                    break;
                }
            } else {
                if (request.getRequestURI().contains(request.getContextPath() + link)) {
                    availableRequest = true;
                    break;
                }
            }

        }
        if (loggedIn || availableRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + allowedLinks.get(1));
        }
    }

    @Override
    public void destroy() {

    }
}
