package com.isobar.core.services;

import com.isobar.core.beans.Route;
import com.isobar.core.beans.Schedule;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightScheduleServiceImplTest {

    @InjectMocks
    FlightScheduleServiceImpl flightScheduleService;

    @Mock
    private ResourceResolverFactory resourceResolverFactory;

    @Mock
    private ResourceResolver resourceResolver;

    /**
     * When the right response is received from service.
     * @throws LoginException
     */
    @Test
    public void testServiceResponse() throws LoginException {

        when(resourceResolverFactory.getServiceResourceResolver(any())).thenReturn(resourceResolver);

        Schedule schedule = flightScheduleService.getSchedule(Route.SYDNEY_TO_MELBOURNE);

        assertThat("Origin should be Sydney:", schedule.getOrigin(), is("Sydney"));
        assertThat("Number of flights should be 4", schedule.getFlights().size(), is(4));

    }

    /**
     * When resource resolver throws login exception.
     * @throws LoginException
     */
    @Test
    public void testLoginExceptionHandling() throws LoginException {

        when(resourceResolverFactory.getServiceResourceResolver(any())).thenThrow(LoginException.class);
        Schedule schedule = flightScheduleService.getSchedule(Route.SYDNEY_TO_MELBOURNE);

        assertNull(schedule);

    }

    /**
     *
     * Finally condition coverage for closing resource resolver.
     * @throws LoginException
     */
    @Test
    public void testResourceResolverClose() throws LoginException {


        when(resourceResolverFactory.getServiceResourceResolver(any())).thenReturn(resourceResolver);
        when(resourceResolver.isLive()).thenReturn(true);
        Schedule schedule = flightScheduleService.getSchedule(Route.SYDNEY_TO_MELBOURNE);
        assertThat("Origin should be Sydney:", schedule.getOrigin(), is("Sydney"));


    }
}
