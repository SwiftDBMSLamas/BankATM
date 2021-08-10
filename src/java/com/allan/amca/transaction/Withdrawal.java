package com.allan.amca.transaction;

/**
 * Withdrawal subclass
 * @author allanaranzaso
 */
public class Withdrawal extends Transaction {

    /**
     * Constructor that takes one parameter
     * @param transactionDate the date the withdrawal took place. Automatically generated when you call
     *                        TransactionFactory. Current date and time are taken and passed through.
     */
    public Withdrawal(final String transactionDate) {
        super(transactionDate);
    }

    /**
     * Calculates the withdrawal amount from the account's current balance
     * @param currentBalance the balance to calculate. Retrieved from the database
     * @param amount the amount to calculate with account balance.
     * @return the amount of money left in the account
     */
    @Override
    protected double calculate(double currentBalance, double amount) {
        if (currentBalance < amount) {
            throw new IllegalStateException("You do not have enough funds to withdraw the entered amount");
        }
        return currentBalance - amount;
    }

}
