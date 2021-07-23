package com.allan.amca.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

public final class DatabaseConnection {

    private static Connection con;
    private static final Locale locale     = new Locale("en");
    private static ResourceBundle bundle   = ResourceBundle.getBundle("res", locale);


    private static final String USE_CLIENT_DB = "USE Clients";


    public static Connection getConnection() {
        final String url        = bundle.getString("db.url");
        final String user       = bundle.getString("db.user");
        final String password   = bundle.getString("db.pw");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            con = connection;
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return con;
    }

    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        try {
            conn.createStatement();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}

