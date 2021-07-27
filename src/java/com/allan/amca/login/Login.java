package com.allan.amca.login;

import java.sql.*;

public class Login extends LoginViewModel {

    private static final Login instance = new Login();

    private Login() {}
// Can refactor this into a result class.. call the method
    public boolean login(final Long clientID, final String password) {
        final String url = "jdbc:mysql://localhost:3306/";
        final Statement state;
        final ResultSet result;
        boolean resultValid = false;
        try (Connection connection = DriverManager.getConnection(url, "root", "allanaranzaso")) {
            state = connection.createStatement();
            state.execute("USE Clients");

            result = state.executeQuery("SELECT client_id, password " +
                    "FROM client " +
                    "WHERE client_id =" + clientID + " " + "AND password ='" + password + "'");
            if (result.next()) {
                resultValid = true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultValid;
    }

//    Singleton access
    public static Login getInstance() {
        return instance;
    }
}
