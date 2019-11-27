package edu.upb.pumatiti.preparation.utils;

public class Constants {
    //Intent
    public static final String INTENT_KEY_USER = "userLogged";

    //Menu keys
    public static final String KEY_ROUTES = "routes";
    public static final String KEY_NEWS = "news";
    public static final String KEY_RULES = "rules";

    //Default values for testing
    public static final String DEFAULT_USER_HOST = "host@pumatiti.com";
    public static final String DEFAULT_USER_ANALYST = "analyst@pumatiti.com";
    public static final String DEFAULT_BUS_ID = "bus-";
    public static final int DEFAULT_BUS_MAX_ID = 3;

    //Default map settings
    public static final double DEFAULT_LAT = -16.524702;
    public static final double DEFAULT_LNG = -68.110632;
    public static final int DEFAULT_ZOOM = 15;

    //URL for testing
    public static final String BASE_URL = "https://firebasestorage.googleapis.com/v0/b/pumatiti-preparation.appspot.com/o/";
    public static final String API_PARAM_ALT = "media";

    //Firebase paths
    public static final String FIREBASE_PATH_BUSES = "buses";
    public static final long GPS_TIMER = 2000;
}
