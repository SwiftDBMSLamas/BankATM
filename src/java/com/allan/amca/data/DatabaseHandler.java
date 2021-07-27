package com.allan.amca.data;

import com.allan.amca.user.Client;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;


public final class DatabaseHandler implements Handler {
//    Resources
    private final Locale locale     = new Locale("en");
    private ResourceBundle bundle   = ResourceBundle.getBundle("res", locale);
    private final String url          = bundle.getString("db.url");
    private final String dbUser       = bundle.getString("db.user");
    private final String password     = bundle.getString("db.pw");

//    Database name and Table name
    private static final String DATABASE_NAME               = "Clients";
    private static final String TABLE_CLIENT                = "client";

//    User table columns
    private static final String COLUMN_CLIENT_ID            = "client_id";
    private static final String COLUMN_CLIENT_FIRSTNAME     = "first_name";
    private static final String COLUMN_CLIENT_LASTNAME      = "last_name";
    private static final String COLUMN_CLIENT_PASSWORD      = "password";

//    Create db and use clients
    private static final String CREATE_CLIENT_DB            = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
    private static final String USE_CLIENT_DB               = "USE Clients";

//    Create client table sql - DDL command
    private final String CREATE_CLIENT_TABLE = "CREATE TABLE " + TABLE_CLIENT + "("
            + COLUMN_CLIENT_ID + " INT NOT NULL,"
            + COLUMN_CLIENT_FIRSTNAME + " VARCHAR(30) NOT NULL,"
            + COLUMN_CLIENT_LASTNAME + " VARCHAR(40) NOT NULL,"
            + COLUMN_CLIENT_PASSWORD + " TEXT NOT NULL,"
            + "PRIMARY KEY (" + COLUMN_CLIENT_ID + ")" + ");";
//    DML commands
    private static final String ADD_NEW_USER   = "INSERT INTO " + TABLE_CLIENT;

    private static final DatabaseHandler instance     = new DatabaseHandler();
    private Statement state;

    private DatabaseHandler(){}

    public static DatabaseHandler newInstance() {
        return instance;
    }

    /**
     *  creates database on start if it doesn't already exist
      */
    @Override
    public void onCreate() {

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {

            if (connection != null) {
                state = connection.createStatement();

                state.executeQuery(CREATE_CLIENT_DB);
                state.executeQuery(USE_CLIENT_DB);
                state.executeQuery(CREATE_CLIENT_TABLE);

                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("The driver name is " + metaData.getDriverName());
                System.out.println("A new database has successfully been created");
            }
        } catch (SQLException ex) {
            System.err.println("Message: " + ex.getMessage());
            System.err.println("Cause: " + ex.getCause());
        }
    }

    /** Adds client to the database
     *  @param user the client to add to the database
     * @return false if the execute statement is an update count. Otherwise true if execute statement returned a ResultSet.
     */
    @Override
    public boolean addClient(@NotNull Client user)  {
        final String ADD_USER = ADD_NEW_USER + " (first_name, last_name, password) " +
                "VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '"
                + user.getPassword() + "');";

        return writeToDatabase(ADD_USER);
    }

    /**
    * Retrieve client from the database
    * @param idToRetrieve - the client ID of the client to retrieve
    * @throws IllegalArgumentException if the client does not exist in the database
    */
    @Override
    public Client getClient(final long idToRetrieve) {
        final String READ_QUERY = "SELECT * " +
                "FROM client " +
                "WHERE client_id ='" + idToRetrieve + "';";

        return getClientFromDatabase(READ_QUERY);
    }

    /**
     * Check the database to see if the client you are looking for exists.
     * @param idToCheck the client ID of the client you want to check
     * @return true if exists in the database. Otherwise false
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
     * @return false if the execute statement is an update count. Otherwise true if execute statement returned a ResultSet
     */
    @Override
    public boolean updateClient(@NotNull final Client user)  {
        final String UPDATE_USER = "UPDATE " + TABLE_CLIENT + " " +
                "SET " + COLUMN_CLIENT_FIRSTNAME + " = " + "'" + user.getFirstName() + "', "
                + COLUMN_CLIENT_LASTNAME + " = " + "'" + user.getLastName() + "', "
                + COLUMN_CLIENT_PASSWORD + " = " + "'" + user.getPassword() + "' "
                + "WHERE client_id = " + user.getClientID() + ";";

         return writeToDatabase(UPDATE_USER);
    }

    /**
     * Delete the selected client from the database
     * @param clientID the client ID you wish to delete from the database
     * @return false if the execute statement is an update count. Otherwise true if execute statement returned a ResultSet
     */
    @Override
    public boolean deleteClient(final long clientID) {
        final String DELETE_USER = "DELETE FROM client WHERE client_id =" + clientID + ";";

        return writeToDatabase(DELETE_USER);
    }

    /**
     * Method that executes the MySQL query
     * @param query the MySQL query to execute
     * @return false if the execute statement was an update count. Otherwise true if execute statement returned a ResultSet
     */
    public final boolean executeSQL(@NotNull final String query) {
        return writeToDatabase(query);
    }

    /**
     * Private method that writes to database
     * @param query the MySQL query to manipulate the database
     * @return false if the result from the execute statement returned an update count. Otherwise true if the result
     * returned a ResultSet
     */
    private boolean writeToDatabase(@NotNull final String query) {
        boolean isAResultSet = false;

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            state = connection.createStatement();
            state.executeQuery(USE_CLIENT_DB);
            isAResultSet = state.execute(query);

            if (query.contains("INSERT")) {
                System.out.print("Adding new client successful \n");
            } else if (query.contains("UPDATE")) {
                System.out.print("Client information has been updated successfully \n");
            } else if (query.contains("DELETE")) {
                System.out.print("WARNING! Client information has been deleted from the database successfully \n");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return isAResultSet;
    }

    /**
     * Private method to retrieve a client from the database. Uses the resultSet from the getClient method to retrieve
     * the client
     * @param query the MySQL query to execute
     * @return A client from the database
     */
    private Client getClientFromDatabase(@NotNull final String query) {
        final ResultSet resultSet;

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);
            resultSet = state.executeQuery(query);

            if (resultSet.next()) {
                return getClient(resultSet);
            }
        } catch (SQLException ex) {
            ex.getMessage();
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

        client = new Client(firstName, lastName, pw);
        client.setClientID(clientID);

        return client;
    }
}
