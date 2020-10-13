package com.isobar.core.beans;

import org.apache.commons.lang3.StringUtils;

public enum Route {

    SYDNEY_TO_MELBOURNE, MELBOURNE_TO_SYDNEY;

    public static Route of(int selection) {
        if (selection == 1) {
            return Route.SYDNEY_TO_MELBOURNE;
        } else {
            return Route.MELBOURNE_TO_SYDNEY;
        }

    }

    public static Route of(String origin, String destination) {

        if (StringUtils.equalsIgnoreCase(origin, "sydney")) {
            return Route.SYDNEY_TO_MELBOURNE;
        } else  {
            return Route.MELBOURNE_TO_SYDNEY;
        }

    }
}
