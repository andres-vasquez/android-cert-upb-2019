package edu.upb.pumatiti.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.models.repository.Stop;
import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.models.types.UserType;
import edu.upb.pumatiti.models.ui.StopMarker;
import edu.upb.pumatiti.models.ui.UserLogged;

public class ResponseMapper {

    public static UserLogged mapUserToUserLooged(FirebaseUser user) {
        if (user.getEmail().equals(Constants.USER_EMAIL_HOST)) {
            return new UserLogged(user.getEmail(), UserType.HOST);
        } else if (user.getEmail().equals(Constants.USER_EMAIL_ANALYST)) {
            return new UserLogged(user.getEmail(), UserType.ANALYST);
        } else {
            return new UserLogged(user.getEmail(), UserType.REGULAR_USER);
        }
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

}
