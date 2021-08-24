package com.allan.amca.transaction;

import com.allan.amca.data.DaoAbstract;
import com.allan.amca.data.DataResources;
import com.allan.amca.user.Client;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

/**
 * Transaction Dao
 * @author allanaranzaso
 * @version 1.0
 */
public class TransactionDaoImpl extends DaoAbstract<Transaction, Long> {
    private static final String URI          = DataResources.getDBUri();
    private static final String DB_USER      = DataResources.getDBUsername();
    private static final String DB_PW        = DataResources.getDBPassword();
    private static final int TRANSACTION_TYPE_PARAM;
    private static final int TRANSACTION_DATE_PARAM;
    private static final int TRANSACTION_AMOUNT_PARAM;
    private static final int NO_RECORDS;
    private static final int TRANSACTION_CLIENT_ID_PARAM;
    private static final int CLIENT_REQUEST;
    private static final int TRANSACTION_ID_PARAM;

    static {
        NO_RECORDS                  = 0;
        TRANSACTION_TYPE_PARAM      = 1;
        TRANSACTION_DATE_PARAM      = 2;
        TRANSACTION_AMOUNT_PARAM    = 3;
        TRANSACTION_CLIENT_ID_PARAM = 4;
        CLIENT_REQUEST              = 1;
        TRANSACTION_ID_PARAM        = 1;
    }

    /**
     * Adds the transaction to the database.
     * @param transaction the transaction to add to the database
     * @return true if the query executed successfully. Otherwise, false
     */
    @Override
    protected final boolean addRecord(@NotNull final Transaction transaction) {
        final int numOfRecordsInserted;
        final String QUERY = "INSERT INTO transactions (transaction_type, transaction_date, transaction_amount, client_id) " +
                             "VALUES (?, ?, ?, ?);";
        boolean transactionAdded = false;
        Client client = Client.getClient(CLIENT_REQUEST);

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement addStmt = connection.prepareStatement(QUERY)) {
                connection.setAutoCommit(false);
                addStmt.setString(TRANSACTION_TYPE_PARAM, transaction.getTransactionType());
                addStmt.setString(TRANSACTION_DATE_PARAM, transaction.getTransactionDate());
                addStmt.setBigDecimal(TRANSACTION_AMOUNT_PARAM, transaction.getTransactionAmount());
                addStmt.setLong(TRANSACTION_CLIENT_ID_PARAM, client.getClientID());

                numOfRecordsInserted = addStmt.executeUpdate();
                if (numOfRecordsInserted > NO_RECORDS) {
                    transactionAdded = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transactionAdded;
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

    /**
     * Retrieve a transaction object from the database.
     * @param toRetrieve the transaction id to retrieve
     * @return transaction object with information on the transaction
     */
    @Override
    protected Transaction readRecord(Long toRetrieve) {
        final String QUERY = "SELECT transaction_id, transaction_type, transaction_date, transaction_amount " +
                "FROM transactions " +
                "WHERE transaction_id = ?";
        Transaction retrievedTransaction    = null;
        final int transactionID             = toRetrieve.intValue();
        final int TRANSACTION_ID_PARAM      = 1;
        final int TRANSACTION_TYPE_PARAM    = 2;
        final int TRANSACTION_DATE_PARAM    = 3;
        final int TRANSACTION_AMT_PARAM     = 4;
        final ResultSet rs;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement retrieveStmt = connection.prepareStatement(QUERY)) {
                retrieveStmt.setInt(TRANSACTION_ID_PARAM, transactionID);

                rs = retrieveStmt.executeQuery();
                if (rs.next()) {
                    final String type = rs.getString(TRANSACTION_TYPE_PARAM);
                    switch(type) {
                        case "withdraw" -> retrievedTransaction  = new Withdrawal(rs.getString(TRANSACTION_DATE_PARAM));
                        case "deposit"  -> retrievedTransaction  = new Deposit(rs.getString(TRANSACTION_DATE_PARAM));
                        default         -> throw new IllegalArgumentException("Type is invalid");
                    }
                    // Set the fields based on the information retrieved from the database
                    retrievedTransaction.setTransactionID(rs.getInt(TRANSACTION_ID_PARAM));
                    retrievedTransaction.setTransactionType(rs.getString(TRANSACTION_TYPE_PARAM));
                    retrievedTransaction.setTransactionDate(rs.getString(TRANSACTION_DATE_PARAM));
                    retrievedTransaction.setTransactionAmount(rs.getBigDecimal(TRANSACTION_AMT_PARAM));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retrievedTransaction;
    }

    /**
     * Deletes a transaction off the database
     * @param toDelete the transaction to delete
     * @return true if the query executed successfully. Otherwise, false.
     */
    @Override
    protected boolean deleteRecord(Transaction toDelete) {
        final String QUERY = "DELETE FROM transactions WHERE transaction_id = ?";
        final int transactionID = toDelete.getTransactionID();
        final int recordsDeleted;
        boolean deleteSuccess = false;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement deleteStmt = connection.prepareStatement(QUERY)) {
                connection.setAutoCommit(false);
                deleteStmt.setInt(TRANSACTION_ID_PARAM, transactionID);

                recordsDeleted = deleteStmt.executeUpdate();
                if (recordsDeleted > NO_RECORDS) {
                    deleteSuccess = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return deleteSuccess;
    }

    /**
     * Updates a transaction in the database
     * @param transaction the transaction to update
     * @param id the transaction id to locate in the database
     * @return true if the query executed successfully. Otherwise, false.
     */
    @Override
    protected boolean executeUpdate(Transaction transaction, Long id) {
        final String QUERY = "UPDATE transactions SET transaction_type = ?," +
                "transaction_date = ?, transaction_amount = ?" +
                "WHERE transaction_id = ?";
        final int transactionID = id.intValue();
        final int recordsUpdated;
        boolean updateSuccess = false;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement updateStmt = connection.prepareStatement(QUERY)) {
                connection.setAutoCommit(false);

                updateStmt.setString(TRANSACTION_AMOUNT_PARAM, transaction.getTransactionType());
                updateStmt.setString(TRANSACTION_DATE_PARAM, transaction.getTransactionDate());
                updateStmt.setBigDecimal(TRANSACTION_AMOUNT_PARAM, transaction.getTransactionAmount());
                updateStmt.setInt(TRANSACTION_CLIENT_ID_PARAM, transactionID);

                recordsUpdated = updateStmt.executeUpdate();
                if (recordsUpdated > NO_RECORDS) {
                    updateSuccess = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return updateSuccess;
    }
}
