package com.isobar.core.flight.farecalculator;

import com.isobar.core.beans.MembershipType;

public class FareCalculatorFactory {

    public static FareCalculator getInstance(MembershipType membershipType) {
        if (membershipType == MembershipType.GOLD) {
            return new GoldCalculator();
        } else if (membershipType == MembershipType.SILVER) {
            return new SilverCalculator();
        } else {
            return new BronzeCalculator();
        }
    }
}
