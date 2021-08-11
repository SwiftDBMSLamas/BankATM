package com.allan.amca.login;

import com.allan.amca.data.Resources;

import java.sql.*;

public class Login extends LoginViewModel {

    private static final Login instance = new Login();

    private Login() {}
// Can refactor this into a result class. call the method
    public boolean login(final Long clientID, final int pin) {
        final String URI            = Resources.getDBUri();
        final String DB_USER        = Resources.getDBUsername();
        final String DB_PW          = Resources.getDBPassword();
        final String LOGIN_QUERY    = "SELECT client_id, pin FROM client WHERE client_id = ? AND pin = ?;";
        final ResultSet result;
        boolean resultValid = false;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement validateLogin = connection.prepareStatement(LOGIN_QUERY)) {
                validateLogin.setLong(1, clientID);
                validateLogin.setInt(2, pin);
                result = validateLogin.executeQuery();
                if (result.next()) {
                    resultValid = true;
                    System.out.println("Login successful!");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultValid;
    }

//    Singleton access
    public static Login getInstance() {
        return instance;
    }
}
