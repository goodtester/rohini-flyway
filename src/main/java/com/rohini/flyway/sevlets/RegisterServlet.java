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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setAddress(request.getParameter("address"));
        user.setPhone(request.getParameter("phone"));
        user.setUserType(0);
        user.setActive(true);

        boolean isSaved = userDao.saveUser(user);
        if (isSaved) {
            request.setAttribute("RESP_MSG", "User is added successfully");
            request.setAttribute("RESP_STATUS", "SUCCESS");
        } else {
            request.setAttribute("RESP_MSG", "Failed to add user!");
            request.setAttribute("RESP_STATUS", "SUCCESS");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rd.forward(request, response);
    }

}
