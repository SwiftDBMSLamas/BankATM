package com.allan.amca.data;

import com.allan.amca.user.Client;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public final class DatabaseHelper implements Helper {
    private Statement state;
    private final Locale locale     = new Locale("en");
    private ResourceBundle bundle   = ResourceBundle.getBundle("res", locale);
    private static Connection con   = DatabaseConnection.getConnection();

//    Database name and Table name
    private static final String DATABASE_NAME               = "Clients";
    private static final String TABLE_CLIENT                = "client";

//    com.allan.amca.Login.User table columns
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
    private static final String UPDATE_PW      = "UPDATE";
    private static final DatabaseHelper instance     = new DatabaseHelper();

    private DatabaseHelper(){}

    public static DatabaseHelper getInstance() {
        return instance;
    }

    // creates database on start if it doesn't already exist
    @Override
    public void onCreate() {
        final String url        = bundle.getString("db.url");
        final String user       = bundle.getString("db.user");
        final String password   = bundle.getString("db.pw");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

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

    /* Adds client to the database
        @param user- the client to add to the database
        @throws IllegalArgumentException if the client exists in the database
     */
    @Override
    public void addClient(@NotNull Client user)  {
        final String addSQL = ADD_NEW_USER + " (first_name, last_name, password) " +
                "VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '"
                + user.getPassword() + "');";
        executeSQL(addSQL , user);
    }

    @Override
    public Client retrieveClient(final Long idToRetrieve) {
        final String url          = bundle.getString("db.url");
        final String dbUser       = bundle.getString("db.user");
        final String password     = bundle.getString("db.pw");
        final ResultSet resultSet;
        final Long clientID;
        final String firstName;
        final String lastName;
        final String pw;
        final Client client;
        final String retrieveSQL = "SELECT * " +
                                    "FROM client " +
                                    "WHERE client_id ='" + idToRetrieve + "';";

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);
            resultSet = state.executeQuery(retrieveSQL);
            if (resultSet.next()) {
                clientID = resultSet.getLong(1);
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                pw = resultSet.getString(4);
                client = new Client(firstName, lastName, pw);
                client.setClientID(clientID);

                return client;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        throw new IllegalArgumentException("Client does not exists in the database");
    }


    public boolean checkIfClientExists(final Long idToCheck) {
        final ResultSet resultSet;
        boolean clientExists      = false;
        final String url          = bundle.getString("db.url");
        final String dbUser       = bundle.getString("db.user");
        final String password     = bundle.getString("db.pw");

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);
            resultSet = state.executeQuery("SELECT client_id FROM client WHERE client_id = '" + idToCheck + "'");
            if (resultSet.next()) {
                clientExists = true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return clientExists;
    }


    @Override
    public void updateClient(@NotNull final Client user)  {
        final String UPDATE_USER = "UPDATE " + TABLE_CLIENT + " " +
                "SET " + COLUMN_CLIENT_FIRSTNAME + " = " + "'" + user.getFirstName() + "', "
                + COLUMN_CLIENT_LASTNAME + " = " + "'" + user.getLastName() + "', "
                + COLUMN_CLIENT_PASSWORD + " = " + "'" + user.getPassword() + "' "
                + "WHERE client_id = " + user.getClientID();
        executeSQL(UPDATE_USER, user);
    }

    @Override
    public void deleteClient(final Long clientID) {
        final String DELETE_USER = "DELETE FROM client WHERE client_id =" + clientID;
        executeSQL(DELETE_USER, clientID);
    }

    /*
        Method executes the SQL passed through the CRUD methods
        @sql - the SQL statement to pass through. Must be in MySQL syntax
        @user - the client that will be manipulated in the database
     */
    private void executeSQL(final String sql, @NotNull final Client user) {
        final String url          = bundle.getString("db.url");
        final String dbUser       = bundle.getString("db.user");
        final String password     = bundle.getString("db.pw");

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            state = connection.createStatement();
            state.executeQuery(USE_CLIENT_DB);
            state.execute(sql);

            if (sql.contains("INSERT")) {
                System.out.printf("Adding new client successful: %s  %s \n", user.getFirstName(), user.getLastName());
            } else if (sql.contains("UPDATE")) {
                System.out.println("Client information has been updated successfully");
            } else if (sql.contains("DELETE")) {
                System.out.println("WARNING! Client information has been deleted from the database successfully");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void executeSQL(final String sql, final Long clientID) {
        final String url          = bundle.getString("db.url");
        final String dbUser       = bundle.getString("db.user");
        final String password     = bundle.getString("db.pw");

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            state = connection.createStatement();
            state.executeQuery(USE_CLIENT_DB);
            state.execute(sql);

            if (sql.contains("INSERT")) {
                System.out.printf("Adding new client successful, client ID: %n \n", clientID);
            } else if (sql.contains("UPDATE")) {
                System.out.printf("Client information has been updated successfully for: %n \n", clientID);
            } else if (sql.contains("DELETE")) {
                System.out.printf("WARNING! Client information has been deleted from the database successfully." +
                        "Client ID: %n \n", clientID);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
