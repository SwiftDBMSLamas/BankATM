package com.allan.amca.user;

import java.sql.*;

public class Client extends User {

    private static final String USE_CLIENT_DB = "USE Clients";
    private Long clientCardID;

    public Client(final String firstName, final String lastName, final String password) {
        super(firstName, lastName, password);
    }

    public Long getClientID() {
        return clientCardID;
    }

    public void setClientID(final Long clientID) {
        clientCardID = clientID;
    }
// later on will need to add another primary key.. maybe email?
    public void retrieveID() {
        final String url = "jdbc:mysql://localhost:3306/";
        Statement state;
        ResultSet resultSet;
        try (Connection connection = DriverManager.getConnection(url,
                "root", "allanaranzaso")) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);
            resultSet = state.executeQuery("SELECT client_id " +
                    "FROM client " +
                    "WHERE first_name = '" + this.getFirstName() + "'" +
                    "AND last_name ='" + this.getLastName() + "'" +
                    "AND password ='" + this.getPassword() + "';");
            System.out.println(resultSet.next());
            if (resultSet.next()) {
                System.out.println("Assigning ID...");
                clientCardID = resultSet.getLong(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
