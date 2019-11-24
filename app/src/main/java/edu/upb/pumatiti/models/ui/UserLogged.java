package edu.upb.pumatiti.models.ui;

import edu.upb.pumatiti.models.types.UserType;

public class UserLogged {
    private String uuid;

    private String name;

    private String email;

    private UserType userType;

    private String busUuid;

    public UserLogged(String uuid, String name, String email, UserType userType) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.userType = userType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getBusUuid() {
        return busUuid;
    }

    public void setBusUuid(String busUuid) {
        this.busUuid = busUuid;
    }


}
