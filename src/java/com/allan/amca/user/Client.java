package com.allan.amca.user;

<<<<<<< HEAD
import java.sql.*;

public class Client extends User {

    private static final String USE_CLIENT_DB = "USE Clients";
    private Long clientCardID;

    public Client(final String firstName, final String lastName, final String password) {
        super(firstName, lastName, password);
    }

    public Long getClientID() {
        retrieveID();
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

            if (resultSet.next()) {
                clientCardID = resultSet.getLong(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
=======
public class Client extends User {

    private static int clientID = 123012300;
    private String clientCardID;
    private static final String CLIENT_CARD_PREFIX;

    static {
        CLIENT_CARD_PREFIX = "4519011";
    }

    public Client(final String firstName, final String lastName, final String password) {
        super(firstName, lastName, password);
        createClientID();
    }

    public void createClientID() {
        final String clientCard;
        clientID++;
        clientCard = CLIENT_CARD_PREFIX + clientID;
        this.clientCardID = clientCard;
    }

    public String getClientID() {
        return clientCardID;
>>>>>>> master
    }
}
