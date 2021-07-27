package com.allan.amca.account;

import com.allan.amca.data.DatabaseHandler;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Account {

    private Double accountBalance;
    private DatabaseHandler db = DatabaseHandler.newInstance();

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(final Double balance) {
        validateBalance(balance);
        this.accountBalance = balance;
    }

    public Double retrieveBalance(final Long clientID) {
        Locale locale = new Locale("en");
        ResourceBundle bundle = ResourceBundle.getBundle("res", locale);
        final String url          = bundle.getString("db.url");
        final String dbUser       = bundle.getString("db.user");
        final String password     = bundle.getString("db.pw");
        final String query = "SELECT balance FROM account WHERE clientID = " + clientID + ";";
        ResultSet rs;
        Statement statement;

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            statement = connection.createStatement();
            statement.execute("USE Clients");
            rs = statement.executeQuery(query);
            while (rs.next()) {
                accountBalance = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return accountBalance;
    }

    private static void validateBalance(final Double balance) {
        if (balance < 0.0) {
            throw new IllegalArgumentException("Balance cannot go into overdraft");
        }
    }
}
