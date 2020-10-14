package com.isobar.core.models;


import com.isobar.core.services.FlightScheduleService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = {ReservationModel.class},
        resourceType = {ReservationModelImpl.RESOURCE_TYPE_V1})
public class ReservationModelImpl implements ReservationModel {


    public static final String RESOURCE_TYPE_V1 = "flight-reservation/components/reservation/v1/reservation";

    @OSGiService
    private FlightScheduleService flightScheduleService;


    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String originLabel;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String destinationLabel;


    @Override
    public List<String> getCities() {

        List<String> cities = new ArrayList<>(2);
        cities.add("Sydney");
        cities.add("Melbourne");
        return cities;
    }


    public String getOriginLabel() {
        return originLabel;
    }

    public String getDestinationLabel() {
        return destinationLabel;
    }
}
