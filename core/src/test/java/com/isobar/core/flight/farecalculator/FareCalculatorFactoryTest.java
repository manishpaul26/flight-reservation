package com.isobar.core.flight.farecalculator;

import com.isobar.core.beans.MembershipType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FareCalculatorFactoryTest {

    @Test
    public void testGoldInstance() {

        FareCalculator fareCalculator = FareCalculatorFactory.getInstance(MembershipType.GOLD);
        assertTrue(fareCalculator instanceof GoldCalculator);

    }


    @Test
    public void testSilverInstance() {

        FareCalculator fareCalculator = FareCalculatorFactory.getInstance(MembershipType.SILVER);
        assertTrue(fareCalculator instanceof SilverCalculator);

    }


    @Test
    public void testBronzeInstance() {

        FareCalculator fareCalculator = FareCalculatorFactory.getInstance(MembershipType.BRONZE);
        assertTrue(fareCalculator instanceof BronzeCalculator);

    }

    @Test
    public void testIncorrectMembership() {
        FareCalculator fareCalculator = FareCalculatorFactory.getInstance(null);
        assertNull(fareCalculator);

    }
}
