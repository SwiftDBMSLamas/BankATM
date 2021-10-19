package main.com.allan.amca.transaction;

import main.com.allan.amca.data.DaoAbstract;
import main.com.allan.amca.data.DataResources;
import main.com.allan.amca.user.Client;

import java.sql.*;
import java.util.HashMap;

/**
 * Transaction Dao
 * @author allanaranzaso
 * @version 1.0
 */
public class TransactionDaoImpl<T, N> extends DaoAbstract<Transaction, Long> {
    private static final DataResources res   = DataResources.getInstance();
    private static final String DB_URL       = res.getDBUrl();
    private static final String DB_USER      = res.getDBUsername();
    private static final String DB_PW        = res.getDBPassword();
    private static final int TRANSACTION_TYPE_PARAM;
    private static final int TRANSACTION_DATE_PARAM;
    private static final int TRANSACTION_AMOUNT_PARAM;
    private static final int NO_RECORDS;
    private static final int TRANSACTION_CLIENT_ID_PARAM;
    private static final int CLIENT_REQUEST;
    private static final int TRANSACTION_ID_PARAM;

    static {
        res.loadPropsFile();
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
    protected final boolean addRecord(final Transaction transaction) {
        final int numOfRecordsInserted;
        final String QUERY = "INSERT INTO transactions (transaction_type, transaction_date, transaction_amount, client_id) " +
                             "VALUES (?, ?, ?, ?);";
        boolean transactionAdded = false;
        Client client = Client.getClient(CLIENT_REQUEST);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW)) {
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
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW)) {
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
     * @param clientIdToRetrieve the transaction id to retrieve
     * @return transaction object with information on the transaction
     */
    @Override
    protected Transaction readRecord(Long clientIdToRetrieve) {
        final String QUERY = "SELECT transaction_id, transaction_type, transaction_date, transaction_amount " +
                "FROM transactions WHERE client_id = ? ORDER BY client_id DESC LIMIT 1";
        Transaction retrievedTransaction    = null;
        final int CLIENT_ID_PARAM      = 1;
        final int TRANSACTION_TYPE_PARAM    = 2;
        final int TRANSACTION_DATE_PARAM    = 3;
        final int TRANSACTION_AMT_PARAM     = 4;
        final ResultSet rs;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW)) {
            try (PreparedStatement retrieveStmt = connection.prepareStatement(QUERY)) {
                retrieveStmt.setLong(CLIENT_ID_PARAM, clientIdToRetrieve);

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
     * Retrieves the previous 5 transactions made by the user.
     * @param accountID The client's account ID to search for in the database. Must be 16 digits long
     * @return a Map of the transactions found in the database that match the account ID
     */
    public HashMap<Integer, Transaction> recordsRetrieved(final Long accountID) {
        HashMap<Integer, Transaction> transactionHashMap = new HashMap<>();
        final ResultSet rs;
        Transaction retrievedTransaction;
        final String QUERY  = "SELECT transaction_id, transaction_type, transaction_date, transaction_amount" +
                " FROM transactions WHERE client_id = ? LIMIT 5;";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW)) {
            try (PreparedStatement retrieveTransactions = connection.prepareStatement(QUERY)) {
                retrieveTransactions.setLong(1, accountID);
                connection.setAutoCommit(false);
                rs = retrieveTransactions.executeQuery();
                while (rs.next()) {
                    final String type = rs.getString(2);
                    switch(type) {
                        case "withdrawal" -> retrievedTransaction  = new Withdrawal(rs.getString(3));
                        case "deposit"  -> retrievedTransaction  = new Deposit(rs.getString(3));
                        default         -> throw new IllegalArgumentException("Type is invalid: " + type);
                    }
                    retrievedTransaction.setTransactionID(rs.getInt(1));
                    retrievedTransaction.setTransactionType(rs.getString(2));
                    retrievedTransaction.setTransactionDate(rs.getString(3));
                    retrievedTransaction.setTransactionAmount(rs.getBigDecimal(4));
                    transactionHashMap.put(retrievedTransaction.getTransactionID(), retrievedTransaction);
                    System.out.println("Transaction: " + transactionHashMap.get(retrievedTransaction.getTransactionID()));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transactionHashMap;
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

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW)) {
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

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW)) {
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
