package com.rohini.flyway.filters;

import com.rohini.flyway.Constant;
import com.rohini.flyway.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
    DispatcherType.INCLUDE, DispatcherType.ERROR}, urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);

        String addAirline = request.getContextPath() + "/admin/add-airline";
        String addAirport = request.getContextPath() + "/admin/add-airport";
        String addFlight = request.getContextPath() + "/admin/add-flight";
        String changePassword = request.getContextPath() + "/admin/change-password";
        User user = (User) session.getAttribute(Constant.SESSION_USER);

        if(request.getRequestURI().equals(addAirline) || request.getRequestURI().equals(addAirport)
            || request.getRequestURI().equals(addFlight) || request.getRequestURI().equals(changePassword)) {
            if(user == null || user.getUserType() == 0) {
                response.sendRedirect(request.getContextPath() + "/index");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
