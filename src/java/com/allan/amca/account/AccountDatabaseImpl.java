package com.allan.amca.account;

import com.allan.amca.data.Resources;

import java.sql.*;

public class AccountDatabaseImpl {

    public static double retrieveBalance(final long accountID) {
        final String url          = Resources.getDBUri();
        final String dbUser       = Resources.getDBUsername();
        final String password     = Resources.getDBPassword();
        final String QUERY        = "SELECT balance FROM account WHERE clientID = ?;";
        final int    BALANCE_COL  = 1;
        ResultSet rs;
        double retrievedBalance = 0.0;

        try (Connection connection = DriverManager.getConnection(url, dbUser, password)) {
            try (PreparedStatement retrieveBal = connection.prepareStatement(QUERY)) {
                connection.setAutoCommit(false);
                retrieveBal.setLong(1, accountID);

                rs = retrieveBal.executeQuery();
                while (rs.next()) {
                    retrievedBalance = rs.getDouble(BALANCE_COL);
                }
            }
            connection.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retrievedBalance;
    }
}
