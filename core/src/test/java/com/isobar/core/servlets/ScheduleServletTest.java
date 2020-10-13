package com.isobar.core.servlets;


import com.google.gson.Gson;
import com.isobar.core.beans.Schedule;
import com.isobar.core.services.FlightScheduleService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import static junitx.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(AemContextExtension.class)
public class ScheduleServletTest {

    private ScheduleServlet servlet = new ScheduleServlet();

    @Mock
    private FlightScheduleService service;

    void doGet(AemContext context) throws ServletException, IOException {


        service = mock(FlightScheduleService.class);

        when(service.getSchedule(any())).thenReturn(new Schedule("Sydney", "Melbourne", new ArrayList<>()));

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();
        context.registerService(FlightScheduleService.class, service);


        servlet.doGet(request, response);

        Schedule schedule = new Gson().fromJson(response.getOutputAsString(), Schedule.class);

        assertEquals("Title = resource title", response.getOutputAsString());
    }


}
