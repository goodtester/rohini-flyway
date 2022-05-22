package com.rohini.flyway.sevlets;

import com.rohini.flyway.dao.AirportDao;
import com.rohini.flyway.dao.DBBase;
import com.rohini.flyway.dao.FlightDao;
import com.rohini.flyway.models.Flight;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
@WebServlet("/index")
public class SearchFlightServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    final FlightDao flightDao = new FlightDao();
    final AirportDao airportDao = new AirportDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AirportDao airportDao = new AirportDao();
        request.setAttribute("airports", airportDao.listAirports());
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fromAirport = request.getParameter("fromAirport");
        String toAirport = request.getParameter("toAirport");
        String departureDate = request.getParameter("departureDate");
        String passengers = request.getParameter("passengers");

        List<Flight> flights = flightDao.searchFlight(fromAirport, toAirport, departureDate);
        request.setAttribute("flights", flights);
        request.setAttribute("airports", airportDao.listAirports());
        request.setAttribute("fromAirport", fromAirport);
        request.setAttribute("toAirport", toAirport);
        request.setAttribute("departureDate", departureDate);
        request.setAttribute("passengers", passengers);

        RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
        rd.forward(request, response);
    }

}
