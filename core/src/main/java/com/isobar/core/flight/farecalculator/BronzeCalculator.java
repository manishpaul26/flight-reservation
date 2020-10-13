package com.isobar.core.flight.farecalculator;

import com.isobar.core.beans.BookingDetails;
import com.isobar.core.beans.Passengers;

public class BronzeCalculator implements FareCalculator {

    @Override
    public float calculateCost(BookingDetails bookingDetails) {
        int fare = bookingDetails.getFlight().getPrice();
        Passengers passengers = bookingDetails.getPassengers();

        float totalCost = passengers.getAdults() * fare;

        if (passengers.getChildAges() != null) {
            for (Integer age : passengers.getChildAges()) {
                if (age >= 3 && age <= 16) {
                    totalCost = totalCost + (fare * 0.90f);
                } else if (age > 0 && age <=2) {
                    totalCost = totalCost + (fare * 0.6f);
                }
            }
        }

        return totalCost;
    }
}
