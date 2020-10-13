package com.isobar.core.flight.farecalculator;


import com.isobar.core.beans.BookingDetails;
import com.isobar.core.beans.Flight;
import com.isobar.core.beans.MembershipType;
import com.isobar.core.beans.Passengers;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class SilverCalculatorTest {

    private Flight flight;

    @BeforeEach
    public void initialize() {
        flight = new Flight(LocalTime.of(6, 30), LocalTime.of(8, 30), 75);

    }

    /**
     *
     * 3 adults * 75 = 225
     */
    @Test
    public void testWithOnlyAdults() {

        Passengers passengers = new Passengers(3, null, MembershipType.SILVER);

        BookingDetails bookingDetails = new BookingDetails(flight, passengers);

        SilverCalculator silverCalculator = new SilverCalculator();
        float cost = silverCalculator.calculateCost(bookingDetails);

        assertThat("Cost should be $225 for 3 adults: ", cost, CoreMatchers.is(225f));

    }

    /***
     *
     * 2 adults * 75 = 150
     * 2 children below 8 = 80% of 75 = 60
     *      = 2 * 60 = 120
     *
     * Total = 105 + 225 = 270
     */
    @Test
    public void testWithAdultsAndChildrenBelow8() {

        List<Integer> childAges = new ArrayList<>();
        childAges.add(new Integer(1));
        childAges.add(new Integer(5));
        Passengers passengers = new Passengers(2, childAges, MembershipType.SILVER);

        BookingDetails bookingDetails = new BookingDetails(flight, passengers);

        SilverCalculator silverCalculator = new SilverCalculator();
        float cost = silverCalculator.calculateCost(bookingDetails);

        assertThat("Cost should be $225 for 2 adults and 2 children (below 8): ", cost, CoreMatchers.is(270f));

    }

    /***
     *
     * 2 adults * 75 = 150
     * 2 children above 8 = 90% of 75 = 67.5
     *      = 2 * 67.50 = 135
     *
     * Total = 105 + 225 = 285
     */
    @Test
    public void testWithAdultsAndChildrenAbove8() {

        List<Integer> childAges = new ArrayList<>();
        childAges.add(new Integer(10));
        childAges.add(new Integer(12));
        Passengers passengers = new Passengers(2, childAges, MembershipType.SILVER);

        BookingDetails bookingDetails = new BookingDetails(flight, passengers);

        SilverCalculator silverCalculator = new SilverCalculator();
        float cost = silverCalculator.calculateCost(bookingDetails);

        assertThat("Cost should be $225 for 2 adults and 2 children (above 8): ", cost, CoreMatchers.is(285f));

    }

    /***
     *
     * 2 adults * 75 = 150
     * 1 child above 8 = 90% of 75 = 67.5
     * 1 child below 8 = 80% of 75 = 60
     *
     * Total = 105 + 225 = 277.5
     */
    @Test
    public void testWithAdultsAndMixedAgedChildren() {

        List<Integer> childAges = new ArrayList<>();
        childAges.add(new Integer(7));
        childAges.add(new Integer(12));
        Passengers passengers = new Passengers(2, childAges, MembershipType.SILVER);

        BookingDetails bookingDetails = new BookingDetails(flight, passengers);

        SilverCalculator silverCalculator = new SilverCalculator();
        float cost = silverCalculator.calculateCost(bookingDetails);

        assertThat("Cost should be $225 for 2 adults and 2 children (above 8): ", cost, CoreMatchers.is(277.5f));

    }


    /*** Edge Cases ****/


    /***
     *
     * 1 child (16 years) = 0.9 * 75 = 67.5
     */
    @Test
    public void testWithOneChildSixteen() {

        List<Integer> childAges = new ArrayList<>();
        childAges.add(new Integer(16));
        Passengers passengers = new Passengers(0, childAges, MembershipType.SILVER);

        BookingDetails bookingDetails = new BookingDetails(flight, passengers);

        SilverCalculator silverCalculator = new SilverCalculator();
        float cost = silverCalculator.calculateCost(bookingDetails);

        assertThat("Cost should be $60 for 1 child of age sixteen ", cost, CoreMatchers.is(67.5f));

    }


    /***
     *
     * 1 child (8 years) = 0.8 * 75 = 60
     */
    @Test
    public void testWithOneChildEight() {

        List<Integer> childAges = new ArrayList<>();
        childAges.add(new Integer(8));
        Passengers passengers = new Passengers(0, childAges, MembershipType.SILVER);

        BookingDetails bookingDetails = new BookingDetails(flight, passengers);

        SilverCalculator silverCalculator = new SilverCalculator();
        float cost = silverCalculator.calculateCost(bookingDetails);

        assertThat("Cost should be $60 for 1 child of age eight ", cost, CoreMatchers.is(60f));

    }
}
