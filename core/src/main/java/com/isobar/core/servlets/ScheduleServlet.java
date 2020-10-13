package com.isobar.core.servlets;


import com.google.gson.Gson;
import com.isobar.core.beans.Route;
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

@Component(service = Servlet.class,

        property={
                Constants.SERVICE_DESCRIPTION + "=Reservation Schedule Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.resourceTypes="+ "flight-reservation/components/reservation/v1/reservation",
                "sling.servlet.extensions=" + "json",
                "sling.servlet.selectors=" + "schedule"
        }
)
public class ScheduleServlet extends SlingSafeMethodsServlet {

    @Reference
    private FlightScheduleService flightScheduleService;


    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");

        Route route = Route.of(origin, destination);

        response.getWriter().write(new Gson().toJson(flightScheduleService.getSchedule(route)));

    }


}
