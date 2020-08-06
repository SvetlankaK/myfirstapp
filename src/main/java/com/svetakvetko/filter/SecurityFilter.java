package com.svetakvetko.filter;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import com.svetakvetko.service.RoleService;
import com.svetakvetko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.svetakvetko.database.RoleEnum.ADMIN_ACCESS;

@Component
//TODO WEBFILTER RIP?
public class SecurityFilter extends HttpFilter {

    private List<String> allowedAll;
    private List<String> allowedRegistered;
    private List<String> allowedAdmin;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void init(FilterConfig fConfig) {
        allowedAll = new ArrayList<>();
        Collections.addAll(allowedAll, "/", "/login", "/registration", "/favicon.ico");
        allowedRegistered = new ArrayList<>(allowedAll);
        Collections.addAll(allowedRegistered, "/welcome", "/logout");
        allowedAdmin = new ArrayList<>(allowedRegistered);
        Collections.addAll(allowedAdmin, "/users", "/editUser", "/delete");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        boolean loggedIn = session != null && session.getAttribute("userLogin") != null;
        boolean isAdmin = false;
        List<Role> userRoles = new ArrayList<>();
        if (loggedIn) {
            User user = userService.findByLogin(session.getAttribute("userLogin").toString());
            userRoles = roleService.getRolesById(user.getUserId());
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
            chain.doFilter(request, response);
        } else {
            if (loggedIn) {
                response.sendRedirect(request.getContextPath() + "/welcome");
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }

    @Override
    public void destroy() {

    }
}