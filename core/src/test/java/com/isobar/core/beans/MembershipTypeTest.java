package com.isobar.core.beans;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class MembershipTypeTest {

    /**
     * Test case for Main class inputs to get the right membership type.
     */
    @Test
    public void testWithInteger() {
        MembershipType membershipType = MembershipType.of(1);
        assertEquals(MembershipType.GOLD, membershipType);

        membershipType = MembershipType.of(2);
        assertEquals(MembershipType.SILVER, membershipType);

        membershipType = MembershipType.of(3);
        assertEquals(MembershipType.BRONZE, membershipType);

    }


    /**
     * Whe used with servlets, a string value is passed. This value is used to get the right membership type from @{@link MembershipType#of(String)}.
     */
    @Test
    public void testWithString() {

        MembershipType membershipType = MembershipType.of("gold");
        assertEquals(MembershipType.GOLD, membershipType);

        membershipType = MembershipType.of("silver");
        assertEquals(MembershipType.SILVER, membershipType);

        membershipType = MembershipType.of("bronze");
        assertEquals(MembershipType.BRONZE, membershipType);


    }
}
