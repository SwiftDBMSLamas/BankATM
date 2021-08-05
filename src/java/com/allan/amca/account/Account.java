package com.allan.amca.account;

public class Account extends AccountDaoImpl {

    private double accountBalance;

    /**
     * Get the account's current balance
     * @return the account's balance as a double data type
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Set the account's new balance
     * @param balance the account's new balance to set. Cannot be negative.
     */
    public void setAccountBalance(final double balance) {
        validateBalance(balance);
        this.accountBalance = balance;
    }

    //open account method, gets client ID- inserts it into the account db with balance set to 0?

    /**
     * Validates the balance being input. For now, set to throw exception if value is negative.
     * @param balance the balance to validate. Cannot be negative.
     * @throws IllegalArgumentException if value is negative
     */
    private static void validateBalance(final double balance) {
        if (balance < 0.0) {
            throw new IllegalArgumentException("Balance cannot go into overdraft");
        }
    }
}
