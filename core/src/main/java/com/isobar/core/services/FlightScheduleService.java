package com.isobar.core.services;


import com.isobar.core.beans.Route;
import com.isobar.core.beans.Schedule;

public interface FlightScheduleService {

    Schedule getSchedule(Route route);
}
