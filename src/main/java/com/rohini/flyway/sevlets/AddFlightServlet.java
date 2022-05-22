package com.rohini.flyway.sevlets;

import com.rohini.flyway.dao.AirlineDao;
import com.rohini.flyway.dao.AirportDao;
import com.rohini.flyway.dao.FlightDao;
import com.rohini.flyway.models.Flight;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
@WebServlet("/admin/add-flight")
public class AddFlightServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final FlightDao flightDao = new FlightDao();
    private final AirlineDao airlineDao = new AirlineDao();
    private final AirportDao airportDao = new AirportDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("airlines", airlineDao.listAirlines());
        request.setAttribute("airports", airportDao.listAirports());

        RequestDispatcher rd = request.getRequestDispatcher("./add-flight.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final String airlineCode = request.getParameter("airlineCode");
        final String flightNumber = request.getParameter("flightNumber");
        final String startTime = request.getParameter("startTime");
        final String endTime = request.getParameter("endTime");
        final String duration = formatElapsedTime((parseDate(endTime).getTime() - parseDate(startTime).getTime())/1000);
        final String sourceAirport = request.getParameter("sourceAirport");
        final String destinationAirport = request.getParameter("destinationAirport");
        final String totalSeats = request.getParameter("totalSeats");
        final String stops = request.getParameter("stops");
        final String price = request.getParameter("price");

        final Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setAirlineCode(airlineCode);
        flight.setStartTime(startTime);
        flight.setEndTime(endTime);
        flight.setDuration(duration);
        flight.setSourceAirport(sourceAirport);
        flight.setDestinationAirport(destinationAirport);
        flight.setTotalSeats(Integer.parseInt(totalSeats));
        flight.setAvailableSeats(Integer.parseInt(totalSeats));
        flight.setStops(Integer.parseInt(stops));
        flight.setPrice(price);

        boolean isSaved = flightDao.saveFlight(flight);

        if (isSaved) {
            request.setAttribute("RESP_MSG", "Flight is added successfully");
            request.setAttribute("RESP_STATUS", "SUCCESS");
        } else {
            request.setAttribute("RESP_MSG", "Failed to add Flight!");
            request.setAttribute("RESP_STATUS", "FAILED");
        }

        request.setAttribute("airlines", airlineDao.listAirlines());
        request.setAttribute("airports", airportDao.listAirports());

        RequestDispatcher rd = request.getRequestDispatcher("/admin/add-flight.jsp");
        rd.forward(request, response);
    }

    public static Date parseDate (String strDate) {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date1 = null;
        try {
            date1 = dateFormat.parse(strDate);
        }
        catch (ParseException e) {
            e.printStackTrace ();
        }
        return date1;
    }

    public static String formatElapsedTime (long seconds) {

        long hours = TimeUnit.SECONDS.toHours(seconds);
        seconds -= TimeUnit.HOURS.toSeconds (hours);

        long minutes = TimeUnit.SECONDS.toMinutes (seconds);

        return String.format ("%dh %dm", hours, minutes);
    }
}
