package com.isobar.core.beans;

import java.util.List;

public class Passengers {

    private int adults;

    private List<Integer> childAges;

    private MembershipType membershipType;

    public Passengers(int adults, List<Integer> childAges, MembershipType membershipType) {
        this.adults = adults;
        this.childAges = childAges;
        this.membershipType = membershipType;
    }

    public int getAdults() {
        return adults;
    }

    public List<Integer> getChildAges() {
        return childAges;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }
}
