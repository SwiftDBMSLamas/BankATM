package com.allan.amca.data;

import com.allan.amca.user.Client;
import com.allan.amca.user.UserFactoryGenerator;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public final class DatabaseHandler implements Handler {
//    Resources
    private static final String DB_URI          = Resources.getDBUri();
    private static final String DB_USER         = Resources.getDBUsername();
    private static final String DB_PW           = Resources.getDBPassword();

//    Table name
    private static final String TABLE_CLIENT                = "client";

//    User table columns
    private static final String COLUMN_CLIENT_ID            = "client_id";
    private static final String COLUMN_CLIENT_FIRSTNAME     = "first_name";
    private static final String COLUMN_CLIENT_LASTNAME      = "last_name";
    private static final String COLUMN_CLIENT_PASSWORD      = "password";

//    PrepareStatement parameters for Client object
    private static final int NO_RECORDS                     = 0;
    private static final int FIRST_NAME_PARAM               = 1;
    private static final int LAST_NAME_PARAM                = 2;
    private static final int PASS_PARAM                     = 3;
    private static final int CLIENT_ID_PARAM                = 4;

//    DML commands
    private static final String ADD_NEW_USER   = "INSERT INTO " + TABLE_CLIENT;

    private static final DatabaseHandler instance     = new DatabaseHandler();

    private DatabaseHandler(){}

    public static DatabaseHandler newInstance() {
        return instance;
    }

    /**
     *  creates database on start if it doesn't already exist
      */
    @Override
    public void onCreate() {
        final String DATABASE_NAME               = "Clients";
        final String CREATE_CLIENT_DB            = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
        final String CREATE_CLIENT_TABLE = "CREATE TABLE " + TABLE_CLIENT + "("
                + COLUMN_CLIENT_ID + " INT NOT NULL,"
                + COLUMN_CLIENT_FIRSTNAME + " VARCHAR(30) NOT NULL,"
                + COLUMN_CLIENT_LASTNAME + " VARCHAR(40) NOT NULL,"
                + COLUMN_CLIENT_PASSWORD + " TEXT NOT NULL,"
                + "PRIMARY KEY (" + COLUMN_CLIENT_ID + ")" + ");";

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PW)) {
            try (PreparedStatement createDB = connection.prepareStatement(CREATE_CLIENT_DB);
                PreparedStatement createTable = connection.prepareStatement(CREATE_CLIENT_TABLE)) {
                connection.setAutoCommit(false);

                createDB.execute();
                createTable.execute();

                DatabaseMetaData metaData = connection.getMetaData();
                System.out.printf("The driver name is: %s \n", metaData.getDriverName());
                System.out.println("A new database has been successfully created");
            }
            connection.commit();
        } catch (SQLException ex) {
            System.err.println("Message: " + ex.getMessage());
            System.err.println("Cause: " + ex.getCause());
        }
    }

    /** Adds client to the database
     *  @param user the client to add to the database
     * @return false if the execute statement is an update count. Otherwise, true if execute statement returned a ResultSet.
     */
    @Override
    public boolean addClient(@NotNull Client user)  {
        final String ADD_USER       = ADD_NEW_USER + " (first_name, last_name, password) " +
                                        "VALUES (?, ?, ?);";
        boolean clientAdded         = false;
        final int recordsInserted;

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PW)) {
            try (PreparedStatement addClient = connection.prepareStatement(ADD_USER)) {
                connection.setAutoCommit(false);
                addClient.setString(FIRST_NAME_PARAM, user.getFirstName());
                addClient.setString(LAST_NAME_PARAM, user.getLastName());
                addClient.setString(PASS_PARAM, user.getPassword());

                recordsInserted = addClient.executeUpdate();
                if (recordsInserted > NO_RECORDS) {
                    clientAdded = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientAdded;
    }

    /**
    * Retrieve client from the database
    * @param idToRetrieve - the client ID of the client to retrieve
    * @throws IllegalArgumentException if the client does not exist in the database
    */
    @Override
    public Client getClient(final long idToRetrieve) {
        return getClientFromDatabase(idToRetrieve);
    }

    /**
     * Check the database to see if the client you are looking for exists.
     * @param idToCheck the client ID of the client you want to check
     * @return true if exists in the database. Otherwise, false
     */
    public boolean checkIfClientExists(final long idToCheck) {
        boolean clientExists = false;
        Client client = getClient(idToCheck);

        if (client != null) {
            clientExists = true;
        }
        return clientExists;
    }

    /**
     * Update the selected client from the database
     * @param user the client object you wish to update in the database
     * @return false if the execute statement is an update count. Otherwise, true if execute statement returned a ResultSet
     */
    @Override
    public boolean updateClient(@NotNull final Client user)  {
        final int recordsUpdated;
        final String UPDATE_USER = "UPDATE " + TABLE_CLIENT + " " +
                                    "SET " + COLUMN_CLIENT_FIRSTNAME + " = ?, "
                                    + COLUMN_CLIENT_LASTNAME + " = ?, "
                                    + COLUMN_CLIENT_PASSWORD + " = ? WHERE client_id = ?;";
        boolean clientIsUpdated = false;

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PW)) {
            try (PreparedStatement updateClient = connection.prepareStatement(UPDATE_USER)) {
                connection.setAutoCommit(false);

                updateClient.setString(FIRST_NAME_PARAM, user.getFirstName());
                updateClient.setString(LAST_NAME_PARAM, user.getLastName());
                updateClient.setString(PASS_PARAM, user.getPassword());
                updateClient.setLong(CLIENT_ID_PARAM, user.getClientID());

                recordsUpdated = updateClient.executeUpdate();
                if (recordsUpdated > NO_RECORDS) {
                    clientIsUpdated = true;
                    System.out.println("Client successfully updated");
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         return clientIsUpdated;
    }

    /**
     * Delete the selected client from the database
     * @param clientID the client ID you wish to delete from the database
     * @return false if the execute statement is an update count. Otherwise, true if execute statement returned a ResultSet
     */
    @Override
    public boolean deleteClient(final long clientID) {
        final String DELETE_USER = "DELETE FROM client WHERE client_id = ?;";
        final int recordsDeleted;
        boolean clientDeleted    = false;

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PW)) {
            try (PreparedStatement deleteClient = connection.prepareStatement(DELETE_USER)) {
                connection.setAutoCommit(false);

                deleteClient.setLong(1, clientID);
                recordsDeleted = deleteClient.executeUpdate();

                if (recordsDeleted > NO_RECORDS) {
                    clientDeleted = true;
                    System.out.printf("Client with ID: %d successfully deleted \n", clientID);
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientDeleted;
    }

    /**
     * Private method to retrieve a client from the database. Uses the resultSet from the getClient method to retrieve
     * the client
     * @param clientID The client's ID you want to retrieve from the database. Must be 16 digits long.
     * @return A client from the database
     */
    private Client getClientFromDatabase(final long clientID) {
        final ResultSet resultSet;
        final String SELECT_CLIENT_QUERY = "SELECT * FROM client WHERE client_id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PW)) {
            try (PreparedStatement getClient = connection.prepareStatement(SELECT_CLIENT_QUERY)) {

                getClient.setLong(1, clientID);
                resultSet = getClient.executeQuery();
                if (resultSet.next()) {
                    return getClient(resultSet);
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Private method to utilize the ResultSet and return a client from the database
     * @param resultSet the object that the SQL query will return. Never null
     * @return Client that was retrieved from the database
     * @throws SQLException if the query is invalid or if the database has issues
     */
    @NotNull
    private Client getClient(ResultSet resultSet) throws SQLException {
        final int CLIENT_ID_COL     = 1;
        final int FIRST_NAME_COL    = 2;
        final int LAST_NAME_COL     = 3;
        final int PASSWORD_COL      = 4;

        final long clientID;
        final String firstName;
        final String lastName;
        final String pw;
        final Client client;

        clientID = resultSet.getLong(CLIENT_ID_COL);
        firstName = resultSet.getString(FIRST_NAME_COL);
        lastName = resultSet.getString(LAST_NAME_COL);
        pw = resultSet.getString(PASSWORD_COL);

        client = UserFactoryGenerator.CreateUser(firstName, lastName, pw);
        client.setClientID(clientID);

        return client;
    }
}
