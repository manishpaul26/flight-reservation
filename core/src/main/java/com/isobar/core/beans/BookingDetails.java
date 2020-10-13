package com.isobar.core.beans;

public class BookingDetails {

    private final Passengers passengers;
    private final Flight flight;

    public BookingDetails(Flight flight, Passengers passengers) {
        this.flight = flight;
        this.passengers = passengers;
    }

    public Passengers getPassengers() {
        return passengers;
    }

    public Flight getFlight() {
        return flight;
    }
}
