package com.allan.amca.data;

import java.util.Locale;
import java.util.ResourceBundle;

public final class DataResources {

    private static final String URI;
    private static final String DB_USER;
    private static final String PASSWORD;

    static {
        Locale locale = new Locale("en");
        ResourceBundle bundle = ResourceBundle.getBundle("res", locale);
        URI = bundle.getString("db.url");
        DB_USER = bundle.getString("db.user");
        PASSWORD = bundle.getString("db.pw");
    }

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


}
