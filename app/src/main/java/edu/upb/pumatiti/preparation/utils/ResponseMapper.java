package edu.upb.pumatiti.preparation.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.upb.pumatiti.preparation.R;
import edu.upb.pumatiti.preparation.models.repository.Bus;
import edu.upb.pumatiti.preparation.models.repository.Route;
import edu.upb.pumatiti.preparation.models.repository.Stop;
import edu.upb.pumatiti.preparation.models.types.UserType;
import edu.upb.pumatiti.preparation.models.ui.BusMarker;
import edu.upb.pumatiti.preparation.models.ui.StopMarker;
import edu.upb.pumatiti.preparation.models.ui.UserLogged;

public class ResponseMapper {

    public static UserLogged mapFirebaseUserToUsserLogged(FirebaseUser user) {
        UserLogged userLogged = new UserLogged(user.getUid(), user.getDisplayName(), user.getEmail(),
                ResponseMapper.getUserType(user.getEmail()));
        //Assign random bus
        if (userLogged.getUserType().equals(UserType.HOST)) {
            userLogged.setBusUuid(ResponseMapper.getRandomBusUuid());
        }
        return userLogged;
    }

    private static UserType getUserType(String email) {
        //TODO just for testing
        if (email.equals(Constants.DEFAULT_USER_HOST)) {
            return UserType.HOST;
        } else if (email.equals(Constants.DEFAULT_USER_ANALYST)) {
            return UserType.ANALYST;
        } else {
            return UserType.REGULAR_USER;
        }
    }

    private static String getRandomBusUuid() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(Constants.DEFAULT_BUS_MAX_ID) + 1;
        return Constants.DEFAULT_BUS_ID + randomInt;
    }

    public static List<StopMarker> mapRoutesToStopMarker(List<Route> routes) {
        List<StopMarker> stopMarkers = new ArrayList<>();
        if (routes != null && !routes.isEmpty()) {
            for (Route route : routes) {
                if (route.getStopList() != null && !route.getStopList().isEmpty()) {
                    for (Stop stop : route.getStopList()) {
                        stopMarkers.add(new StopMarker(stop.getUuid(),
                                stop.getName(),
                                stop.getAddress(),
                                new LatLng(stop.getLat(), stop.getLng()),
                                R.drawable.ic_stop_white_48dp));
                    }
                }
            }
        }
        return stopMarkers;
    }

    public static List<BusMarker> mapBusesToBusMarker(List<Bus> buses) {
        List<BusMarker> busMarkers = new ArrayList<>();
        if (buses != null && !buses.isEmpty()) {
            for (Bus bus : buses) {
                if (bus.getLat() != 0 && bus.getLng() != 0) {
                    busMarkers.add(new BusMarker(bus.getPlate(),
                            new LatLng(bus.getLat(), bus.getLng()),
                            R.drawable.ic_bus_white_48dp));
                }
            }
        }
        return busMarkers;
    }

    public static Bus findMyBus(List<Route> routes, String busUuid) {
        if (routes != null && !routes.isEmpty()) {
            for (Route route : routes) {
                if (route.getBusList() != null && !route.getBusList().isEmpty()) {
                    for (Bus bus : route.getBusList()) {
                        if (bus.getUuid().equals(busUuid)) {
                            return bus;
                        }
                    }
                }
            }
        }
        return null;
    }
}
