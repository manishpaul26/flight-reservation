package com.isobar.core.services;


import com.isobar.core.beans.Route;
import com.isobar.core.beans.Schedule;
import com.isobar.core.utils.ScheduleUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service = FlightScheduleService.class)
public class FlightScheduleServiceImpl implements FlightScheduleService {


    public static final String SYD_MEL = "/content/dam/flight-reservation/schedule/melbourne-to-sydney.json";
    public static final String MEL_SYD = "/content/dam/flight-reservation/schedule/sydney-to-melbourne.json";

    private static final Logger LOG = LoggerFactory.getLogger(FlightScheduleService.class);

    @Reference
    private ResourceResolverFactory resolverFactory;


    @Override
    public Schedule getSchedule(Route route) {

        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "readService");
        ResourceResolver resolver = null;
        try {
            resolver = resolverFactory.getServiceResourceResolver(param);
            String filepath = route == Route.SYDNEY_TO_MELBOURNE ? SYD_MEL : MEL_SYD;
            Resource resource = resolver.getResource(filepath);

            // read file or nodes/resources. But for now, using ScheduleUtils variable

            return ScheduleUtils.getSchedule(route);

        } catch (LoginException e) {
            LOG.error("LoginException {}", e);
        } finally {
            if (resolver != null && resolver.isLive()) {
                resolver.close();
            }
        }
        return null;
    }
}
