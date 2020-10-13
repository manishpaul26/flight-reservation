package com.isobar.core.beans;

import java.util.List;

public class Schedule {

    private String origin;

    private String destination;

    private List<Flight> flights;

    public Schedule(String origin, String destination, List<Flight> flights) {
        this.origin = origin;
        this.destination = destination;
        this.flights = flights;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
