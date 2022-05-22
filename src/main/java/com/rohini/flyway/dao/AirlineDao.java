package com.rohini.flyway.dao;

import com.rohini.flyway.models.Airline;

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
public class AirlineDao extends DBBase {

    public boolean saveAirline(final Airline airline) {

        final String sql = "INSERT INTO airlines(airlineCode, airlineName) "
            + " VALUES(?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            stmt.setString(1, airline.getAirlineCode());
            stmt.setString(2, airline.getAirlineName());


            int count = stmt.executeUpdate();
            if (count > 0) {
                return true;
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public List<Airline> listAirlines() {

        final List<Airline> airlines = new ArrayList<>();
        ResultSet rs = null;

        final String sql = "SELECT airlineId, airlineCode, airlineName "
            + "FROM airlines "
            + "ORDER BY airlineName ";

        try (final PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            rs = stmt.executeQuery();

            while (rs.next()) {
                Airline airline = new Airline();
                airline.setAirlineId(rs.getLong("airlineId"));
                airline.setAirlineCode(rs.getString("airlineCode"));
                airline.setAirlineName(rs.getString("airlineName"));
                airlines.add(airline);
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
        return airlines;
    }

}
