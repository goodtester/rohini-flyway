package com.rohini.flyway.models;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
public class Airline {
    private Long airlineId;
    private String airlineName;
    private String airlineCode;

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }
}
