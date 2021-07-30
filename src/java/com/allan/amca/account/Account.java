package com.allan.amca.account;

public class Account extends AccountDatabaseImpl {

    private double accountBalance;

    /**
     * Get the account's balance in dollar format
     * @return the account's balance as a double data type
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(final double balance) {
        validateBalance(balance);
        this.accountBalance = balance;
    }

    //open account method, gets client ID- inserts it into the account db with balance set to 0?

    private static void validateBalance(final double balance) {
        if (balance < 0.0) {
            throw new IllegalArgumentException("Balance cannot go into overdraft");
        }
    }
}
