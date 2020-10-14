package com.isobar.core.beans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteTest {

    /**
     * Test route values passed as integers from @{@link com.isobar.core.Main}.
     */
    @Test
    public void testRoutesWhenUsingIntegers() {
        Route route = Route.of(1);

        assertEquals(route, Route.SYDNEY_TO_MELBOURNE);

        route = Route.of(2);
        assertEquals(route, Route.MELBOURNE_TO_SYDNEY);
    }

    /**
     * Test route values passed as string from servlet and service.
     */
    @Test
    public void testRoutesWhenUsingString() {
        Route route = Route.of("sydney", "melbourne");

        assertEquals(route, Route.SYDNEY_TO_MELBOURNE);

        route = Route.of("melbourne", "sydney");
        assertEquals(route, Route.MELBOURNE_TO_SYDNEY);
    }
}
