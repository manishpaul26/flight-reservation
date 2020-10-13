package com.isobar.core.models;

import com.day.cq.wcm.api.Page;
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


    private ReservationModel hello;

    private Page page;

    private Resource resource;

    @BeforeEach
    public void setup(AemContext context) throws Exception {

        context.addModelsForPackage("com.isobar.core.models");
        // prepare a page with a test resource
        page = context.create().page("/content/reservation-page");
        resource = context.create().resource(page, "reservation",
                "sling:resourceType", "flight-reservation/components/reservation/v1/reservation");

        context.currentResource(resource);
        hello = context.request().adaptTo(ReservationModel.class);
    }


    /**
     * Model should return "Sydney and Melbourne"
     * @throws Exception
     */
    @Test
    void testModelReturnsCorrectCities() throws Exception {
        List<String> cities = hello.getCities();

        assertThat("Size of the list of cities should be 2", cities.size(), is(2));

        assertThat("First city should be Sydney", cities.get(0), is("Sydney"));
    }

}
