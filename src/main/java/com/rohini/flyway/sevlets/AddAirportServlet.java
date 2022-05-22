package com.rohini.flyway.sevlets;

import com.rohini.flyway.dao.AirportDao;
import com.rohini.flyway.models.Airport;
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
@WebServlet("/admin/add-airport")
public class AddAirportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final AirportDao airportDao = new AirportDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("./add-airport.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final String airportCode = request.getParameter("airportCode");
        final String airportName = request.getParameter("airportName");
        final String airportCity = Objects.isNull(request.getParameter("airportCity")) ? "" : request.getParameter("airportCity");
        final String airportCountry = Objects.isNull(request.getParameter("airportCountry")) ? "USA" : request.getParameter("airportCountry");

        final Airport airport = new Airport();
        airport.setAirportCode(airportCode);
        airport.setAirportName(airportName);
        airport.setAirportCity(airportCity);
        airport.setAirportCountry(airportCountry);

        boolean isSaved = airportDao.saveAirport(airport);

        if (isSaved) {
            request.setAttribute("RESP_MSG", "Airport is added successfully");
            request.setAttribute("RESP_STATUS", "SUCCESS");
        } else {
            request.setAttribute("RESP_MSG", "Failed to add Airport!");
            request.setAttribute("RESP_STATUS", "FAILED");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/admin/add-airport.jsp");
        rd.forward(request, response);
    }
}
