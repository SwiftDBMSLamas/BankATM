package com.allan.amca.transaction;

import com.allan.amca.data.Resources;
import com.allan.amca.user.Client;

import java.sql.*;

public class TransactionDatabaseImpl {
    private static final String URI          = Resources.getDBUri();
    private static final String DB_USER      = Resources.getDBUsername();
    private static final String DB_PW        = Resources.getDBPassword();

    public final boolean writeTransactionToDatabase(final Transaction transaction) {
        final int numOfRecordsInserted;
        final String QUERY = "INSERT INTO Transactions (transaction_type, transaction_date, transaction_amount) " +
                             "VALUES ( ?, ?, ?);";
        boolean success = false;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement writeToDB = connection.prepareStatement(QUERY)) {
                connection.setAutoCommit(false);
                writeToDB.setString(1, transaction.getTransactionType());
                writeToDB.setString(2, transaction.getTransactionDate());
                writeToDB.setDouble(3, transaction.getTransactionAmount());

                numOfRecordsInserted = writeToDB.executeUpdate();
                if (numOfRecordsInserted > 0) {
                    success = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }

    public boolean updateAccountTransactionsTable(final Client client, final Transaction transaction) {
        final String UPDATE = "INSERT INTO account_transactions (clientID, transaction_id, transaction_date)" +
                                "VALUES (?,?,?)";
        final int numOfUpdatedRecords;
        boolean tablesUpdated = false;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement updateTable = connection.prepareStatement(UPDATE)) {
                connection.setAutoCommit(false);
                updateTable.setLong(1, client.getClientID());
                updateTable.setInt(2, retrieveTransactionID());
                updateTable.setString(3, transaction.getTransactionDate());
                numOfUpdatedRecords = updateTable.executeUpdate();

                if (numOfUpdatedRecords > 0) {
                    tablesUpdated = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tablesUpdated;
    }

    private static int retrieveTransactionID() {
        final String RETRIEVE_QUERY = "SELECT transaction_id FROM transactions ORDER BY transaction_id DESC LIMIT 1;";
        final ResultSet resultSet;
        int transactionID = 0;
        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement retrieveTransaction = connection.prepareStatement(RETRIEVE_QUERY)) {
                connection.setAutoCommit(false);
                resultSet = retrieveTransaction.executeQuery();

                if (resultSet.next()) {
                    transactionID = resultSet.getInt(1);
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transactionID;
    }
}
