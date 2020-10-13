package com.isobar.core.flight.farecalculator;

import com.isobar.core.beans.BookingDetails;

public interface FareCalculator {

    float calculateCost(BookingDetails bookingDetails);
}
