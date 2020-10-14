package com.isobar.core.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class ReservationModelInterfaceTest {



    /**
     * Simple interface tests.
     * @throws Exception
     */
    @Test

    void testModelReturnsCorrectCities() {


        ReservationModel model = new ReservationModel() {};

        assertThrows(UnsupportedOperationException.class, () -> model.getCities());
        assertThrows(UnsupportedOperationException.class, () -> model.getDestinationLabel());
        assertThrows(UnsupportedOperationException.class, () -> model.getOriginLabel());

    }

}
