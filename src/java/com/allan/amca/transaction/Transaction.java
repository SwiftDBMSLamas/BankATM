package com.allan.amca.transaction;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactoryGenerator;
import com.allan.amca.data.Resources;
import com.allan.amca.enums.DaoType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Transaction super class
 * @author allanaranzaso
 */
public abstract class Transaction
        extends TransactionFactory
        implements Transactional {

    private final Dao accountDao            = DaoFactoryGenerator.createFactory().createDao(DaoType.ACCOUNT);
    private static final String DB_URI      = Resources.getDBUri();
    private static final String DB_USER     = Resources.getDBUsername();
    private static final String DB_PASS     = Resources.getDBPassword();
    private String              transactionType;
    private int                 transactionID;
    private String              transactionDate;
    private double              transactionAmount;

    /**
     * Transaction constructor that takes one parameter to set the transaction date.
     * @param transactionDate the day the transaction took place. Is automatically set when the
     *                        TransactionFactory class is instantiated. See TransactionFactory.
     */
    public Transaction(final String transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the date of the transaction
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * @return the transaction type
     */
    public String getTransactionType() {
        final String classType = this.getClass().toString().toLowerCase();
        String prettyType = null;

        if (classType.contains("withdrawal")) {
            prettyType = "withdrawal";
        } else if (classType.contains("deposit")) {
            prettyType = "deposit";
        }
        return prettyType;
    }

    /**
     * @return the transaction dollar amount
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * @return the transaction ID
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionAmount the amount to set
     */
    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * @param date the date of the transaction that took place to set
     */
    public void setTransactionDate(final String date) {
        this.transactionDate = date;
    }

    /**
     * @param type the type of transaction to set
     */
    public void setTransactionType(final String type) {
        this.transactionType = type;
    }

    /**
     * @param id the transaction ID to set
     */
    public void setTransactionID(final int id) {
        this.transactionID = id;
    }

    /**
     * Performs the transaction the user has requested.
     * @param clientID the client's ID to access the correct account in the database
     * @param amount the amount to process for the transaction
     * @return true if the query executed successfully. Otherwise, false.
     */
    @Override
    public final boolean performTransaction(final long clientID,
                                            final Double amount){
        final int BALANCE_PARAM     = 1;
        final int CLIENT_ID_PARAM   = 2;
        final int NO_RECORDS        = 0;
        final String UPDATE_QUERY   = "UPDATE account SET balance = ? WHERE clientID = ?;";
        final double INVALID_AMT    = 0.0;
        final String EX_MSG         = "You have entered an invalid amount";
        final Double currentBalance;
        final double newBalance;
        final int recordsUpdated;
        boolean transactionSuccess = false;

        transactionAmount = amount;
        currentBalance = (Double) accountDao.retrieve(clientID);

        if (amount <= INVALID_AMT) {
            throw new IllegalStateException(EX_MSG);
        }

        newBalance = calculate(currentBalance, transactionAmount);

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS)) {
            try (PreparedStatement transaction = connection.prepareStatement(UPDATE_QUERY)) {
                connection.setAutoCommit(false);
                transaction.setDouble(BALANCE_PARAM, newBalance);
                transaction.setLong(CLIENT_ID_PARAM, clientID);

                recordsUpdated = transaction.executeUpdate();
                if (recordsUpdated > NO_RECORDS) {
                    transactionSuccess = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transactionSuccess;
    }

    /**
     * Method for subclasses to override. Performs the appropriate calculation depending on the subclass instantiated.
     * @param currentBalance the balance to calculate. Retrieved from the database
     * @param amount the amount to calculate with account balance.
     * @return the sum after the calculation has been performed.
     */
    protected abstract double calculate(double currentBalance, double amount);
}
