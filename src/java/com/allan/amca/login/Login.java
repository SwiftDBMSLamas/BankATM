package com.allan.amca.login;

import com.allan.amca.data.DataResources;
import com.allan.amca.data.PINCryptUtils;

import java.sql.*;

/**
 * Login class that has one method to verify the login of the user.
 * @author allanaranzaso
 */
public class Login {

    private static final Login instance = new Login();
    private static final int CLIENT_ID_PARAM = 1;
    private static final int ENCRYPT_PIN_PARAM = 1;
    private static final int SALT_PARAM = 2;

    private Login() {}

    /**
     * Validates the credentials of the user by taking their client ID and PIN and comparing it to what's stored in the database
     * @param clientID the client ID to validate and login
     * @param pin the PIN of the client to validate
     * @return true if login is successful. Otherwise, false
     */
    public boolean login(final long clientID, final String pin) {
        final String URI            = DataResources.getDBUri();
        final String DB_USER        = DataResources.getDBUsername();
        final String DB_PW          = DataResources.getDBPassword();
        final String LOGIN_QUERY    = "SELECT pin, salt FROM clients WHERE client_id = ?;";
        final ResultSet result;
        boolean loginValid = false;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement validateLogin = connection.prepareStatement(LOGIN_QUERY)) {
                validateLogin.setLong(CLIENT_ID_PARAM, clientID);
                result = validateLogin.executeQuery();
                if (result.next()) {
                    final String securePin = result.getString(ENCRYPT_PIN_PARAM);
                    final String salt = result.getString(SALT_PARAM);
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
