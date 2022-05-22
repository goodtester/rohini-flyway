package com.rohini.flyway.dao;

import com.rohini.flyway.models.Airport;

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
public class AirportDao extends DBBase {

    public boolean saveAirport(final Airport airport) {

        final String sql = "INSERT INTO airports(airportCode, airportName, airportCity, airportCountry) "
            + " VALUES(?, ?, ?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            stmt.setString(1, airport.getAirportCode());
            stmt.setString(2, airport.getAirportName());
            stmt.setString(3, airport.getAirportCity());
            stmt.setString(4, airport.getAirportCountry());

            int count = stmt.executeUpdate();
            if (count > 0) {
                return true;
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    /* Get Airports */
    public List<Airport> listAirports() {

        final List<Airport> airports = new ArrayList<>();
        ResultSet rs = null;

        final String sql = "SELECT airportId, airportCode, airportName "
            + "FROM airports "
            + "ORDER BY airportName ";

        try (final PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            rs = stmt.executeQuery();

            while (rs.next()) {
                Airport airport = new Airport();
                airport.setAirportId(rs.getLong("airportId"));
                airport.setAirportCode(rs.getString("airportCode"));
                airport.setAirportName(rs.getString("airportName"));
                airports.add(airport);
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
        return airports;
    }

}
