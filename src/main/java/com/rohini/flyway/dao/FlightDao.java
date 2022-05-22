package com.rohini.flyway.dao;

import com.rohini.flyway.models.Flight;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
public class FlightDao extends DBBase {

    public boolean saveFlight(final Flight flight) {

        final String sql = "INSERT INTO flights (flightNumber, airlineCode, sourceAirport, destinationAirport, startTime, endTime, price, totalSeats, availableSeats, stops, duration) "
            + " VALUES(?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            stmt.setString(1, flight.getFlightNumber());
            stmt.setString(2, flight.getAirlineCode());
            stmt.setString(3, flight.getSourceAirport());
            stmt.setString(4, flight.getDestinationAirport());
            stmt.setString(5, flight.getStartTime());
            stmt.setString(6, flight.getEndTime());
            stmt.setString(7, flight.getPrice());
            stmt.setInt(8, flight.getTotalSeats());
            stmt.setInt(9, flight.getAvailableSeats());
            stmt.setInt(10, flight.getStops());
            stmt.setString(11, flight.getDuration());

            System.out.println(stmt.toString());

            int count = stmt.executeUpdate();
            if (count > 0) {
                return true;
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public List<Flight> searchFlight(String from, String to, String date) {

        final List<Flight> flights = new ArrayList<>();
        ResultSet rs = null;

        final String sql = "SELECT f.flightId, f.flightNumber, f.airlineCode, "
            + "f.sourceAirport, f.destinationAirport, f.startTime, f.endTime, "
            + "f.price, f.availableSeats, f.stops, f.duration "
            + "FROM flights f "
            + "WHERE f.sourceAirport = ? AND f.destinationAirport = ? AND f.startTime >= ? ";

        try {
            final PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, from);
            stmt.setString(2, to);
            stmt.setString(3, date);

            System.out.println(stmt.toString());

            rs = stmt.executeQuery();

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getLong(1));
                flight.setFlightNumber(rs.getString(2));
                flight.setAirlineCode(rs.getString(3));
                flight.setSourceAirport(rs.getString(4));
                flight.setDestinationAirport(rs.getString(5));
                flight.setStartTime(rs.getString(6));
                flight.setEndTime(rs.getString(7));
                flight.setPrice(rs.getString(8));
                flight.setAvailableSeats(rs.getInt(9));
                flight.setStops(rs.getInt(10));
                flight.setDuration(rs.getString(11));

                flights.add(flight);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());

        } finally {
            if (Objects.nonNull(rs)) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        return flights;
    }
}
