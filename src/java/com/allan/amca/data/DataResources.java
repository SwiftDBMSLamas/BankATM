package com.allan.amca.data;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class to retrieve the database information from the properties file. If you are having issues connecting to the
 * database. Please open the res_en.properties file and make sure the database URI, user, password are correct for your
 * specific configuration.
 */
public final class DataResources {

    private static final String URI;
    private static final String DB_USER;
    private static final String PASSWORD;
    private static final String NEW_DB_URI;

    static {
        Locale locale = new Locale("en");
        ResourceBundle bundle = ResourceBundle.getBundle("res", locale);
        URI = bundle.getString("db.url");
        DB_USER = bundle.getString("db.user");
        PASSWORD = bundle.getString("db.pw");
        NEW_DB_URI = bundle.getString("createDB.url");
    }
    // prevent from allowing any other classes to instantiate this class
    private DataResources(){}

    public static String getDBUsername() {
        return DB_USER;
    }

    public static String getDBPassword() {
        return PASSWORD;
    }

    public static String getDBUri() {
        return URI;
    }
    public static String getNewDbUri() {
        return NEW_DB_URI;
    }

}
