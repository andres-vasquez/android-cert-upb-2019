package edu.upb.pumatiti.utils;

import com.google.firebase.auth.FirebaseUser;

import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.models.types.UserType;
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
}
