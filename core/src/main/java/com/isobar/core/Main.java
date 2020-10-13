package com.isobar.core;

import com.isobar.core.beans.*;
import com.isobar.core.flight.farecalculator.FareCalculator;
import com.isobar.core.flight.farecalculator.FareCalculatorFactory;
import com.isobar.core.utils.ScheduleUtils;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int selection;

        Route selectedRoute = Route.of(simpleIntegerInput("Choose route : \n 1. Sydney to Melbourne \n 2. Melbourne to Sydney", scanner));
        Schedule schedule =  ScheduleUtils.getJsonSchedule(selectedRoute);

        int count = 1;
        System.out.println("#\tFrom\tTo\t\t\tDeparture\tArrival\tCost");
        for (Flight flight : schedule.getFlights()) {
            System.out.println(count + "\t" + schedule.getOrigin() + "\t" + schedule.getDestination() + "\t" + flight.getDepartureTime()+ "\t\t" + flight.getArrivalTime()+"\t" + flight.getPrice());
            count++;
        }


        selection = simpleIntegerInput("Select flight: (Enter the number)", scanner);
        Flight selectedFlight = schedule.getFlights().get(selection - 1);


        System.out.println("Enter passenger details: ");
        int adults = simpleIntegerInput("Adults (16+): ", scanner);;
        int numberOfChildren = simpleIntegerInput("Children (below 16 years): ", scanner);


        List<Integer> childrenAges = new ArrayList<>(numberOfChildren);
        for( int i = 0; i < numberOfChildren; i++) {
            Integer age = simpleIntegerInput("Enter age for child " + (i + 1), scanner);
            if (age > 16 || age < 0) {
                System.out.println("Invalid age. Please enter a number between 0 and 16");
                i--;
                continue;
            }
            childrenAges.add(age);
        }


        int membershipInput = simpleIntegerInput("Enter your membership type: \n1. Gold\n2. Silver\n3. Bronze", scanner);
        MembershipType membershipType = MembershipType.of(membershipInput);

        Passengers passengers = new Passengers(adults, childrenAges, membershipType);
        BookingDetails bookingDetails = new BookingDetails(selectedFlight, passengers);

        FareCalculator fareCalculator = FareCalculatorFactory.getInstance(membershipType);
        float cost = fareCalculator.calculateCost(bookingDetails);

        System.out.println("Total fare: " + cost);


    }

    public static int getInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int simpleIntegerInput(String message, Scanner scanner) {
        int selection;
        do {
            System.out.println(message);
            selection = getInput(scanner.nextLine());
        } while (selection == -1);
        return selection;
    }
}
