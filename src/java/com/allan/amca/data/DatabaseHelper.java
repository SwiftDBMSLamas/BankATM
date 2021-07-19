package com.allan.amca.data;

import com.allan.amca.User;

import java.sql.*;

public class DatabaseHelper {

    private static final int DATABASE_VERSION = 1;
    public Statement state = null;
//    Database name and Table name
    private static final String DATABASE_NAME = "Clients";
    private static final String TABLE_CLIENT = "client";

//    User table columns
    private static final String COLUMN_CLIENT_ID = "client_id";
    private static final String COLUMN_CLIENT_FIRSTNAME = "first_name";
    private static final String COLUMN_CLIENT_LASTNAME = "last_name";
    private static final String COLUMN_CLIENT_PASSWORD = "password";

    private final String CREATE_CLIENT_DB = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
    private final String USE_CLIENT_DB = "USE Clients";

    private final String CREATE_CLIENT_TABLE = "CREATE TABLE " + TABLE_CLIENT + "("
            + COLUMN_CLIENT_ID + " INT AUTO_INCREMENT NOT NULL," + COLUMN_CLIENT_FIRSTNAME
            + " VARCHAR(30) NOT NULL," + COLUMN_CLIENT_LASTNAME + " VARCHAR(40) NOT NULL,"
            + COLUMN_CLIENT_PASSWORD + " TEXT NOT NULL," + "PRIMARY KEY (" + COLUMN_CLIENT_ID + ")" + ");";

    private final String ADD_NEW_USER = "INSERT INTO " + TABLE_CLIENT;


    public void onCreate() {
        final String url = "jdbc:mysql://localhost:3306/";

        try (Connection connection = DriverManager.getConnection(url,
                "root", "allanaranzaso")) {

            if (connection != null) {
                state = connection.createStatement();

                state.execute(USE_CLIENT_DB);
                state.execute(CREATE_CLIENT_DB);
                state.execute(CREATE_CLIENT_TABLE);

                System.out.println(state.getConnection());
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("The driver name is " + metaData.getDriverName());
                System.out.println("A new database has successfully been created");
            }
        } catch (SQLException ex) {
            System.err.println("Message: " + ex.getMessage());
            System.err.println("Cause: " + ex.getCause());
        }
    }

    public void addClient(User user) {
        final String url = "jdbc:mysql://localhost:3306/";
        final String name = user.toString();

        try (Connection connection = DriverManager.getConnection(url,
                "root", "allanaranzaso")) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);
            state.execute(ADD_NEW_USER + " (first_name, last_name, password) " +
                    "VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '"
                    + user.getPassword() + "');" );
            if(checkIfClientExists(user)) {
                state.execute("rollback ");
            }
            System.out.println(name + " has been added to the database");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private boolean checkIfClientExists(User user) {
        boolean clientExists = false;
        final String url = "jdbc:mysql://localhost:3306/";


        try (Connection connection = DriverManager.getConnection(url,
                "root", "allanaranzaso")) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);
            clientExists = state.execute("SELECT client_id FROM client WHERE last_name = '" + user.getLastName() + "'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return clientExists;
    }

    public void updateClient(User user) {
        final String url = "jdbc:mysql://localhost:3306/";
        final String name = user.toString();
        final String UPDATE_USER = "UPDATE " + TABLE_CLIENT + " SET " + COLUMN_CLIENT_FIRSTNAME + " = "
                + "'" + user.getFirstName() + "'," + COLUMN_CLIENT_LASTNAME + " = " + "'" + user.getLastName() + "'";

        try (Connection connection = DriverManager.getConnection(url,
                "root", "allanaranzaso")) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);
            state.execute(UPDATE_USER + " WHERE (client_id) = (client_id) ");
            System.out.println(name + "'s information has been updated.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
