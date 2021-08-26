package com.allan.amca.account;

import com.allan.amca.data.DaoAbstract;
import com.allan.amca.data.DataResources;
import com.allan.amca.user.Client;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Implements the data access object pattern.
 * @author allanaranzaso
 */
@SuppressWarnings("unchecked")
public class AccountDaoImpl<T, N> extends DaoAbstract<Client, Long> {
    private static final String DB_URI              = DataResources.getDBUri();
    private static final String DB_USER             = DataResources.getDBUsername();
    private static final String DB_PASSWORD         = DataResources.getDBPassword();
    private static final int    NO_RECORDS          = 0;

    /**
     * Retrieve the client's balance from the database.
     * @param accountID the client's ID to check for in the database
     * @return the client's account balance
     */
    @Override
    protected BigDecimal readRecord(final Long accountID) {
        final int ACCOUNT_ID_PARAM  = 1;
        final String QUERY          = "SELECT balance FROM accounts WHERE client_id = ?;";
        final int    BALANCE_COL    = 1;
        ResultSet rs;
        BigDecimal retrievedBalance = BigDecimal.valueOf(0.0);

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PASSWORD)) {
            try (PreparedStatement retrieveBal = connection.prepareStatement(QUERY)) {
                connection.setAutoCommit(false);
                retrieveBal.setLong(ACCOUNT_ID_PARAM, accountID);

                rs = retrieveBal.executeQuery();
                while (rs.next()) {
                    retrievedBalance = BigDecimal.valueOf(rs.getDouble(BALANCE_COL));
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retrievedBalance;
    }

    /**
     * Add client's account to the database. This is different from creating a client. This will "open" up
     * an account for the client. The client MUST exist in the client database, otherwise you will run into
     * an SQL exception for failing foreign key constraints
     * @param toCreate the client object to add to the database.
     * @return true if the query executed successfully. Otherwise, false.
     */
    @Override
    protected boolean addRecord(Client toCreate) {
        final String ADD_QUERY = "INSERT INTO accounts (client_id, balance) VALUES (?,?)";
        final int CLIENT_ID_PARAM = 1;
        final int BALANCE_PARAM = 2;
        final BigDecimal OPENING_BALANCE = BigDecimal.valueOf(0.0);
        final int recordsInserted;
        boolean successfullyAdded = false;

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PASSWORD)) {
            try (PreparedStatement addAccount = connection.prepareStatement(ADD_QUERY)) {
                connection.setAutoCommit(false);
                addAccount.setLong(CLIENT_ID_PARAM, toCreate.getClientID());
                addAccount.setBigDecimal(BALANCE_PARAM, OPENING_BALANCE);
                recordsInserted = addAccount.executeUpdate();
                if (recordsInserted > NO_RECORDS) {
                    successfullyAdded = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return successfullyAdded;
    }

    /**
     * The foreign key constraint with the transactions and client tables will prevent this method from executing.
     * An SQL exception will be thrown unless the foreign key is dropped and the record is deleted.
     * For purposes of this project, there is an implementation just for the sake of overriding the method.
     * The application's code does not call this method to close a client's account. It is not part of requirements
     * I have identified to deal with.
     * @param toDelete the client to delete from the database.
     * @return true if the query executed successfully. Otherwise, false.
     */
    @Override
    protected boolean deleteRecord(Client toDelete) {
        final String DELETE_QUERY = "DELETE FROM accounts WHERE client_id = ?";
        final long clientID = toDelete.getClientID();
        final int CLIENT_ID_PARAM = 1;
        final int recordsDeleted;
        boolean successfullyDeleted = false;

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PASSWORD)) {
            try (PreparedStatement deleteQuery = connection.prepareStatement(DELETE_QUERY)) {
                connection.setAutoCommit(false);
                deleteQuery.setLong(CLIENT_ID_PARAM, clientID);

                recordsDeleted = deleteQuery.executeUpdate();
                if (recordsDeleted > NO_RECORDS) {
                    successfullyDeleted = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return successfullyDeleted;
    }

    /**
     * Execute update for a client's account. While this method may not be needed, the implementation
     * exists. This will "update" a client's account balance or client ID.
     * @param client the client to update in the database
     * @param balance the new balance for the client's account to update in the database
     * @return true if the query executed successfully. Otherwise, false.
     */
    @Override
    protected boolean executeUpdate(Client client, Long balance) {
        final BigDecimal balanceToUpdate = BigDecimal.valueOf(balance.doubleValue());
        final long clientID = client.getClientID();
        final int BALANCE_PARAM = 1;
        final int CLIENT_ID_PARAM = 2;
        final String UPDATE_QUERY = "UPDATE accounts SET balance = ? WHERE client_id = ?";
        final int recordsUpdated;
        boolean updateSuccessful = false;

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PASSWORD)) {
            try (PreparedStatement updateQuery = connection.prepareStatement(UPDATE_QUERY)) {
                connection.setAutoCommit(false);
                updateQuery.setBigDecimal(BALANCE_PARAM, balanceToUpdate);
                updateQuery.setLong(CLIENT_ID_PARAM, clientID);

                recordsUpdated = updateQuery.executeUpdate();
                if (recordsUpdated > NO_RECORDS) {
                    updateSuccessful = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return updateSuccessful;
    }
}
