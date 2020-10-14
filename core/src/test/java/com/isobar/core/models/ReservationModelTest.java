package com.isobar.core.models;

import com.day.cq.wcm.api.Page;
import com.google.common.collect.ImmutableMap;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(AemContextExtension.class)
public class ReservationModelTest {


    private ReservationModel model;

    private Page page;

    private Resource resource;

    @BeforeEach
    public void setup(AemContext context) throws Exception {

        context.addModelsForPackage("com.isobar.core.models");
        // prepare a page with a test resource

        page = context.create().page("/content/reservation-page");


        resource = context.create().resource(page, "reservation",
                ImmutableMap.<String, Object>builder()
                        .put("sling:resourceType", "flight-reservation/components/reservation/v1/reservation")
                        .put("originLabel", "Origin")
                        .put("destinationLabel", "Destination")
                        .build());

        context.currentResource(resource);
        model = context.request().adaptTo(ReservationModel.class);
    }


    /**
     * Model should return "Sydney and Melbourne"
     * @throws Exception
     */
    @Test
    void testModelReturnsCorrectCities() throws Exception {
        List<String> cities = model.getCities();

        assertThat("Size of the list of cities should be 2", cities.size(), is(2));
        assertThat("First city should be Sydney", cities.get(0), is("Sydney"));

        assertThat("Properties should be correctly injected", model.getOriginLabel(), is("Origin"));
        assertThat("Properties should be correctly injected", model.getDestinationLabel(), is("Destination"));

    }

}
