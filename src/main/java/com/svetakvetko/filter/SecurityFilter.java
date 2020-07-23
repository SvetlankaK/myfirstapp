package com.svetakvetko.filter;

import com.svetakvetko.dao.DataBaseUserDao;
import com.svetakvetko.database.RoleEnum;
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

    private List<String> allowedAll;
    private List<String> allowedRegistered;
    private List<String> allowedAdmin;
    @Autowired
    private UserService userService;
    @Autowired
    private DataBaseUserDao dataBaseUserDao;

    @Override
    public void init(FilterConfig fConfig) {
        allowedAll = new ArrayList<>();
        Collections.addAll(allowedAll, "/", "/login.jhtml", "/registration.jhtml", "/favicon.ico");
        allowedRegistered = new ArrayList<>(allowedAll);
        Collections.addAll(allowedRegistered, "/welcome.jhtml", "/logout.jhtml");
        allowedAdmin = new ArrayList<>(allowedRegistered);
        Collections.addAll(allowedAdmin, "/users.jhtml", "/editUser.jhtml", "/delete.jhtml");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        boolean loggedIn = session != null && session.getAttribute("userLogin") != null;
        boolean isAdmin = false;
        List<Role> userRoles = new ArrayList<>();
        if (loggedIn) {
            User user = userService.findByLogin(session.getAttribute("userLogin").toString());
            userRoles = dataBaseUserDao.getRoles(user.getUserId());
            for (Role role : userRoles) {
                if (role.getRoleName().equals(ADMIN_ACCESS.getName())) {
                    isAdmin = true;
                    break;
                }
            }
        }
        String requestURI = request.getRequestURI();
        boolean allowedLink = false;
        if (!loggedIn) {
            if (allowedAll.contains(requestURI)) {
                allowedLink = true;
            }
        } else {
            if (isAdmin) {
                allowedLink = allowedAdmin.contains(requestURI);
            } else {
                allowedLink = allowedRegistered.contains(requestURI);
            }
        }

        if (allowedLink) {
            filterChain.doFilter(request, response);
        } else {
            if (loggedIn) {
                response.sendRedirect(request.getContextPath() + "/welcome.jhtml");
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jhtml");
            }
        }
    }

    @Override
    public void destroy() {

    }
}