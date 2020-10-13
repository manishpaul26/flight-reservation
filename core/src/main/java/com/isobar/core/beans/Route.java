package com.isobar.core.beans;

public enum Route {

    SYDNEY_TO_MELBOURNE, MELBOURNE_TO_SYDNEY;

    public static Route of(int selection) {
        if (selection == 1) {
            return Route.SYDNEY_TO_MELBOURNE;
        } else {
            return Route.MELBOURNE_TO_SYDNEY;
        }

    }
}
