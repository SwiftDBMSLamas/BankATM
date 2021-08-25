package com.allan.amca.login;

import com.allan.amca.data.DataResources;
import com.allan.amca.data.PINCryptUtils;

import java.sql.*;

public class Login {

    private static final Login instance = new Login();

    private Login() {}
    public boolean login(final long clientID, final String pin) {
        final String URI            = DataResources.getDBUri();
        final String DB_USER        = DataResources.getDBUsername();
        final String DB_PW          = DataResources.getDBPassword();
        final String LOGIN_QUERY    = "SELECT pin, salt FROM clients WHERE client_id = ?;";
        final ResultSet result;
        boolean loginValid = false;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement validateLogin = connection.prepareStatement(LOGIN_QUERY)) {
                validateLogin.setLong(1, clientID);
                result = validateLogin.executeQuery();
                if (result.next()) {
                    final String securePin = result.getString(1);
                    final String salt = result.getString(2);
                    loginValid = PINCryptUtils.verifyPIN(pin, securePin, salt);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loginValid;
    }

//    Singleton access
    public static Login getInstance() {
        return instance;
    }
}
