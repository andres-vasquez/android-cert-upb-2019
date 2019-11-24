package edu.upb.pumatiti.utils;

import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

import edu.upb.pumatiti.models.types.UserType;
import edu.upb.pumatiti.models.ui.UserLogged;

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
}
