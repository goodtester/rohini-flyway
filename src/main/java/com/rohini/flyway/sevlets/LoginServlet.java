package com.rohini.flyway.sevlets;

import com.rohini.flyway.dao.LoginDao;
import com.rohini.flyway.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    final LoginDao loginDao = new LoginDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        User user = loginDao.doLogin(email, password);
        if (Objects.isNull(user)) {
            request.setAttribute("RESP_MSG", "Login Failed!!");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } else {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("SESSION_USER", user);
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
