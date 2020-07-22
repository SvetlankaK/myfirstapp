package com.svetakvetko.filter;

import com.svetakvetko.dao.DataBaseUserDao;
import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.svetakvetko.database.RoleEnum.*;


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
    private List<String> adminAllowedLinks;
    @Autowired
    private UserService userService;
    @Autowired
    private DataBaseUserDao dataBaseUserDao;

    @Override
    public void init(FilterConfig fConfig) {
        allowedLinks = new ArrayList<>();
        Collections.addAll(allowedLinks, "/", "/login.jhtml", "/registration.jhtml");
        adminAllowedLinks = new ArrayList<>();
        Collections.addAll(adminAllowedLinks, "/users.jhtml", "/editUser.jhtml");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        boolean loggedIn = session != null && session.getAttribute("userLogin") != null;
        boolean availableRequest = false;
        boolean forbiddenLink = false;
        List<Role> userRole = Collections.emptyList();
        if (loggedIn) {
            User user = userService.findByLogin(session.getAttribute("userLogin").toString());
            userRole = dataBaseUserDao.getRoles(user.getUserId());
        }
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
        for (String link : adminAllowedLinks) {
            if (request.getRequestURI().equals(request.getContextPath() + link)) {
                for (Role role : userRole) {
                    if (role.getRoleName().equals(ADMIN_ACCESS.getName())) {
                        availableRequest = true;
                        break;
                    }
                }
            } else {
                forbiddenLink = true;
            }
        }
        if (forbiddenLink && loggedIn) {
            request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response);
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