package com.isobar.core.models;

import org.osgi.annotation.versioning.ConsumerType;

import java.util.List;

@ConsumerType
public interface ReservationModel {

    default List<String> getCities() {
        throw new UnsupportedOperationException();
    }


    default String getOriginLabel() {
        throw new UnsupportedOperationException();
    }


    default String getDestinationLabel() {
        throw new UnsupportedOperationException();
    }
}
