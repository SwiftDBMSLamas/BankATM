package com.allan.amca.transaction;

/**
 * Deposit subclass
 * @author allanaranzaso
 */
public class Deposit extends Transaction {
    /**
     * Constructor that takes one parameter
     * @param transactionDate the date the deposit took place. Is generated automatically when you call
     *                        TransactionFactory.createTransaction. Takes current date and time.
     */
    public Deposit(final String transactionDate) {
        super(transactionDate);
    }

    /**
     * Calculates the deposit amount from the account's current balance
     * @param currentBalance the balance to calculate. Retrieved from the database
     * @param amount the amount to calculate with account balance.
     * @return the amount of money left in the account
     */
    @Override
    protected double calculate(final double currentBalance, final double amount) {
        return currentBalance + amount;
    }
}
