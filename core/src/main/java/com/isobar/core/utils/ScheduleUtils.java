package com.isobar.core.utils;

import com.google.gson.Gson;
import com.isobar.core.beans.Flight;
import com.isobar.core.beans.Route;
import com.isobar.core.beans.Schedule;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;

public class ScheduleUtils {

    public static Schedule getSchedule(Route route) {

        Map<String, Map<String, List<Flight>>> flightSchedule =  new HashMap<>();


        Map<String, List<Flight>> sydneyDepartures = new HashMap<>(1);
        List<Flight> sydneyToMelbourneFlights = new ArrayList<>();
        sydneyToMelbourneFlights.add(new Flight(LocalTime.of(6, 5), LocalTime.of(7, 40), 75));
        sydneyToMelbourneFlights.add(new Flight(LocalTime.of(6, 40), LocalTime.of(8, 15), 74));
        sydneyToMelbourneFlights.add(new Flight(LocalTime.of(7, 0), LocalTime.of(8, 35), 79));
        sydneyToMelbourneFlights.add(new Flight(LocalTime.of(10, 0), LocalTime.of(11, 35), 131));

        sydneyDepartures.put("melbourne", sydneyToMelbourneFlights);

        flightSchedule.put("sydney", sydneyDepartures);

        List<Flight> melbourneToSydneyFlights = new ArrayList<>();
        melbourneToSydneyFlights.add(new Flight(LocalTime.of(6, 15), LocalTime.of(7, 50), 79));
        melbourneToSydneyFlights.add(new Flight(LocalTime.of(6, 50), LocalTime.of(8, 25), 76));
        melbourneToSydneyFlights.add(new Flight(LocalTime.of(7, 30), LocalTime.of(9, 15), 89));
        melbourneToSydneyFlights.add(new Flight(LocalTime.of(10, 15), LocalTime.of(11, 50), 99));


        Schedule sydToMel = new Schedule("Sydney", "Melbourne", sydneyToMelbourneFlights);
        Schedule melToSyd = new Schedule("Melbourne", "Sydney", melbourneToSydneyFlights);

        return route == Route.SYDNEY_TO_MELBOURNE ? sydToMel : melToSyd;

    }

    public static Schedule getJsonSchedule(Route route) {
        String filename = Route.SYDNEY_TO_MELBOURNE == route ? "sydney-to-melbourne.json" : "melbourne-to-sydney.json";
        String fileContents = readFileFromResources(filename);

        return new Gson().fromJson(fileContents, Schedule.class);
    }

    public static String readFileFromResources(String filename)  {
        try {

            URL resource = ScheduleUtils.class.getClassLoader().getResource(filename);
            byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
            return new String(bytes);
        } catch (URISyntaxException| IOException e) {
            return StringUtils.EMPTY;
        }
    }

}
