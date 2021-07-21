package Data;

import com.allan.amca.user.Client;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class DatabaseHelper implements Helper {
// TODO: refactor the db methods. Should be able to take code and just pass in SQL query instead
    private static final int DATABASE_VERSION = 1;
    public Statement state = null;

//    Database name and Table name
    private static final String DATABASE_NAME   = "Clients";
    private static final String TABLE_CLIENT    = "client";

//    User table columns
    private static final String COLUMN_CLIENT_ID            = "client_id";
    private static final String COLUMN_CLIENT_FIRSTNAME     = "first_name";
    private static final String COLUMN_CLIENT_LASTNAME      = "last_name";
    private static final String COLUMN_CLIENT_PASSWORD      = "password";
//    Create db and use clients
    private static final String CREATE_CLIENT_DB    = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
    private static final String USE_CLIENT_DB       = "USE Clients";
//    Create client table sql
    private final String CREATE_CLIENT_TABLE = "CREATE TABLE " + TABLE_CLIENT + "("
            + COLUMN_CLIENT_ID + " INT NOT NULL,"
            + COLUMN_CLIENT_FIRSTNAME + " VARCHAR(30) NOT NULL,"
            + COLUMN_CLIENT_LASTNAME + " VARCHAR(40) NOT NULL,"
            + COLUMN_CLIENT_PASSWORD + " TEXT NOT NULL,"
            + "PRIMARY KEY (" + COLUMN_CLIENT_ID + ")" + ");";
//    DML commands
    private static final String ADD_NEW_USER   = "INSERT INTO " + TABLE_CLIENT;
    private static final String UPDATE_PW      = "UPDATE";

    // creates database
    @Override
    public void onCreate() {
        final String url = "jdbc:mysql://localhost:3306/";

        try (Connection connection = DriverManager.getConnection(url,
                "root", "allanaranzaso")) {

            if (connection != null) {
                state = connection.createStatement();

                state.executeQuery(CREATE_CLIENT_DB);
                state.executeQuery(USE_CLIENT_DB);
                state.executeQuery(CREATE_CLIENT_TABLE);

                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("The driver name is " + metaData.getDriverName());
                System.out.println("A new database has successfully been created");
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println("Message: " + ex.getMessage());
            System.err.println("Cause: " + ex.getCause());
        }
    }

    // Adds client to the database
    // @param user- the client to add to the database
    // @throws IllegalArgumentException if the client exists in the database
    @Override
    public void addClient(@NotNull Client user) {
        final String addSQL = ADD_NEW_USER + " (client_id, first_name, last_name, password) " +
                "VALUES ('" + user.getClientID() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '"
                + user.getPassword() + "');";

        executeSQL(addSQL , user);
    }

    public boolean checkIfClientExists(Client user) {
        boolean clientExists = false;
        final ResultSet resultSet;
        final String url = "jdbc:mysql://localhost:3306/";

        try (Connection connection = DriverManager.getConnection(url,
                "root", "allanaranzaso")) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);
            resultSet = state.executeQuery("SELECT client_id FROM client WHERE client_id = '" + user.getClientID() + "'");

            if (resultSet.next()) {
                clientExists = true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return clientExists;
    }

    @Override
    public void updateClient(Client user) {
        final String UPDATE_USER = "UPDATE " + TABLE_CLIENT + " " +
                "SET " + COLUMN_CLIENT_FIRSTNAME + " = " + "'" + user.getFirstName() + "',"
                + COLUMN_CLIENT_LASTNAME + " = " + "'" + user.getLastName() + "'"
                + "WHERE client_id = '" + user.getClientID() + "'";

        executeSQL(UPDATE_USER, user);
    }

    @Override
    public void deleteClient(Client user) {
        final String DELETE_USER = "DELETE ";
        //TODO: implement
    }

    private void executeSQL(final String sql, final Client user) {
        final String url = "jdbc:mysql://localhost:3306/";
        final String name = user.toString();
        checkIfClientExists(user);
        try (Connection connection = DriverManager.getConnection(url,
                "root", "allanaranzaso")) {
            state = connection.createStatement();
            state.execute(USE_CLIENT_DB);

            state.execute(sql);
            System.out.println("Action has been successfully performed");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
