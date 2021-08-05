package com.allan.amca.transaction;

import com.allan.amca.account.AccountDaoImpl;
import com.allan.amca.data.Resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Transaction
        extends TransactionFactory
        implements Transactional {

    private final String        transactionDate;
    private double              transactionAmount;
    private final AccountDaoImpl accountDao = new AccountDaoImpl();
    private static final String DB_URI      = Resources.getDBUri();
    private static final String DB_USER     = Resources.getDBUsername();
    private static final String DB_PASS     = Resources.getDBPassword();

    public Transaction(final String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

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

    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public final boolean performTransaction(final long clientID,
                                            final Double amount){
        final String UPDATE_QUERY = "UPDATE account SET balance = ? WHERE clientID = ?;";
        final double INVALID_AMT = 0.0;
        final double currentBalance;
        final double newBalance;
        final int recordsUpdated;
        boolean transactionSuccess = false;
        transactionAmount = amount;
        currentBalance = accountDao.getAccountBalance(clientID);

        if (currentBalance < amount) {
            throw new IllegalStateException("You do not have enough funds to withdraw the entered amount");
        } else if (amount <= INVALID_AMT) {
            throw new IllegalStateException("You have entered an invalid amount to deposit");
        }

        newBalance = calculate(currentBalance, transactionAmount);
        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS)) {
            try (PreparedStatement transaction = connection.prepareStatement(UPDATE_QUERY)) {
                connection.setAutoCommit(false);
                transaction.setDouble(1, newBalance);
                transaction.setLong(2, clientID);

                recordsUpdated = transaction.executeUpdate();
                if (recordsUpdated > 0) {
                    transactionSuccess = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return transactionSuccess;
    }

    protected abstract double calculate(double currentBalance, double amount);
}
