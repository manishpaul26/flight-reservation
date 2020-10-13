package com.isobar.core.beans;

import java.time.LocalTime;

public class Flight {

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private int price;

    public Flight() {
    }

    public Flight(LocalTime departureTime, LocalTime arrivalTime, int price) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public int getPrice() {
        return price;
    }
}
