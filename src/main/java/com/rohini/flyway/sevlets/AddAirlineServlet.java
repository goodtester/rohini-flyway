package com.rohini.flyway.sevlets;

import com.rohini.flyway.dao.AirlineDao;
import com.rohini.flyway.models.Airline;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
@WebServlet("/admin/add-airline")
public class AddAirlineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final AirlineDao airlineDao = new AirlineDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("./add-airline.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final String airlineCode = request.getParameter("airlineCode");
        final String airlineName = request.getParameter("airlineName");


        final Airline airline = new Airline();
        airline.setAirlineCode(airlineCode);
        airline.setAirlineName(airlineName);

        boolean isSaved = airlineDao.saveAirline(airline);

        if (isSaved) {
            request.setAttribute("RESP_MSG", "Airline is added successfully");
            request.setAttribute("RESP_STATUS", "SUCCESS");
        } else {
            request.setAttribute("RESP_MSG", "Failed to add Airline!");
            request.setAttribute("RESP_STATUS", "FAILED");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/admin/add-airline.jsp");
        rd.forward(request, response);
    }
}
