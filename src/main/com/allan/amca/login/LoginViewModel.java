package main.com.allan.amca.login;

import main.com.allan.amca.data.DataResources;
import main.com.allan.amca.data.PINCryptUtils;

import java.sql.*;

/**
 * LoginViewModel class that has one method to verify the login of the user.
 * @author allanaranzaso
 */
public class LoginViewModel {

    private static final LoginViewModel instance = new LoginViewModel();
    private static final int CLIENT_ID_PARAM = 1;
    private static final int ENCRYPT_PIN_PARAM = 1;
    private static final int SALT_PARAM = 2;

    private LoginViewModel() {}

    /**
     * Validates the credentials of the user by taking their client ID and PIN and comparing it to what's stored in the database
     * @param clientID the client ID to validate and login
     * @param pin the PIN of the client to validate
     * @return true if login is successful. Otherwise, false
     */
    public boolean login(final long clientID, final String pin) {
        final DataResources res     = DataResources.getInstance();
        res.loadPropsFile();
        final String DB_URL         = res.getDBUrl();
        final String DB_USER        = res.getDBUsername();
        final String DB_PW          = res.getDBPassword();
        final String LOGIN_QUERY    = "SELECT pin, salt FROM pins WHERE client_id = ?;";
        final ResultSet result;
        boolean loginValid = false;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW)) {
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
    public static LoginViewModel getInstance() {
        return instance;
    }
}
