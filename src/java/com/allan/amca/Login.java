package com.allan.amca;

import java.sql.*;

public class Login {

//    User can login with client card and password
//    As a user, I want to login using my client card and password so that I can make a transaction
//    As a user, I want to reset my password so that I can login
    private static final Login instance = new Login();

    private Login() {}

    public boolean login(final String clientID, final String password) {
        final String url = "jdbc:mysql://localhost:3306/";
        final Statement state;
        final ResultSet result;
        boolean resultValid = false;
        try (Connection connection = DriverManager.getConnection(url, "root", "allanaranzaso")) {
            state = connection.createStatement();
            state.execute("USE Clients");

            result = state.executeQuery("SELECT client_id, password " +
                    "FROM client " +
                    "WHERE client_id ='" + clientID + "'" + "AND password ='" + password + "'");
            if (result.next()) {
                resultValid = true;
            }
            System.out.println(result.next());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultValid;
    }

    public void resetPw() {
//        TODO: add code, code will update database with new password
    }

    public static Login getInstance() {
        return instance;
    }
}
