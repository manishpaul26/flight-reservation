package com.isobar.core.beans;

import com.drew.lang.annotations.Nullable;

public enum MembershipType {
    GOLD, SILVER, BRONZE;

    @Nullable
    public static MembershipType of(int type) {
        switch (type) {
            case 1:
                return GOLD;
            case 2:
                return SILVER;
            case 3:
                return BRONZE;
        }
        return null;
    }


    @Nullable
    public static MembershipType of(String type) {
        switch (type) {
            case "gold":
                return GOLD;
            case "silver":
                return SILVER;
            case "bronze":
                return BRONZE;
        }
        return null;
    }
}
