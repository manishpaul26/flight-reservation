package com.isobar.core.servlets;


import com.google.gson.Gson;
import com.isobar.core.beans.*;
import com.isobar.core.flight.farecalculator.FareCalculator;
import com.isobar.core.flight.farecalculator.FareCalculatorFactory;
import com.isobar.core.services.FlightScheduleService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = Servlet.class,

        property={
                Constants.SERVICE_DESCRIPTION + "=Fare Calculator Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths="+ "/bin/flight/fare"
        }
)
public class FareCalculatorServlet extends SlingSafeMethodsServlet {

    @Reference
    private FlightScheduleService flightScheduleService;

    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        MembershipType membershipType = MembershipType.of(request.getParameter("membershipType"));

        int flightId = getIntegerParameter(request.getParameter("flightId"));

        int adults = getIntegerParameter(request.getParameter("adults"));
        List<Integer> childAges = getAges(request.getParameter("childAges"));

        if ((flightId > 3 || flightId < 0)) {
            response.getWriter().write("Flight ID should be between 1 and 4!");
            return;
        }

        Flight flight = flightScheduleService.getSchedule(Route.of(origin, destination)).getFlights().get(flightId -1);

        BookingDetails bookingDetails = new BookingDetails(flight, new Passengers(adults, childAges, membershipType));

        FareCalculator fareCalculator = FareCalculatorFactory.getInstance(membershipType);
        response.getWriter().write(new Gson().toJson(fareCalculator.calculateCost(bookingDetails)));
    }

    private List<Integer> getAges(String childAges) {
        List<String> ages = Arrays.asList(childAges.split(","));
        return ages.stream().map(Integer::parseInt).collect(Collectors.toList());
    }


    public int getIntegerParameter(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
