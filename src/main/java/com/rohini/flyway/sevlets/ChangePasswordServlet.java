package com.rohini.flyway.sevlets;

import com.rohini.flyway.dao.UserDao;
import com.rohini.flyway.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
@WebServlet("/admin/change-password")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/admin/change-password.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        long userId = Objects.isNull(request.getSession().getAttribute("SESSION_USER")) ? -1 : ((User) request.getSession().getAttribute("SESSION_USER")).getUserId();

        boolean isSaved = false;
        if(userId != -1) {
            isSaved = userDao.changePassword(newPassword, userId);
        }

        if (isSaved) {
            request.setAttribute("RESP_STATUS", "SUCCESS");
            request.setAttribute("RESP_MSG", "Password is changed successfully!");
            RequestDispatcher rd = request.getRequestDispatcher("/admin/change-password.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("RESP_STATUS", "FAILED");
            request.setAttribute("RESP_MSG", "Failed to change password!");
            RequestDispatcher rd = request.getRequestDispatcher("/admin/change-password.jsp");
            rd.forward(request, response);
        }
    }

}
