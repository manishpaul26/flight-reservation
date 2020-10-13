package com.isobar.core.flight.farecalculator;

import com.isobar.core.beans.BookingDetails;
import com.isobar.core.beans.Passengers;

public class GoldCalculator implements FareCalculator {

    @Override
    public float calculateCost(BookingDetails bookingDetails) {

        int fare = bookingDetails.getFlight().getPrice();
        Passengers passengers = bookingDetails.getPassengers();

        float totalCost = passengers.getAdults() * fare;

        if (passengers.getChildAges() != null) {
            for (Integer age : passengers.getChildAges()) {
                if (age <= 8) {
                    totalCost = totalCost + (fare * 0.70f);
                } else if (age > 8 && age <= 16) {
                    totalCost = totalCost + (fare * 0.80f);
                }
            }
        }

        return totalCost;
    }
}
