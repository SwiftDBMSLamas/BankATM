package com.allan.amca.account;

import java.math.BigDecimal;

public class Account extends AccountDaoImpl {

    private BigDecimal accountBalance;

    /**
     * Get the account's current balance
     * @return the account's balance as a double data type
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * Set the account's new balance
     * @param balance the account's new balance to set. Cannot be negative.
     */
    public void setAccountBalance(final BigDecimal balance) {
        validateBalance(balance);
        this.accountBalance = balance;
    }

    //open account method, gets client ID- inserts it into the account db with balance set to 0?

    /**
     * Validates the balance being input. For now, set to throw exception if value is negative.
     * @param balance the balance to validate. Cannot be negative.
     * @throws IllegalArgumentException if value is negative
     */
    private static void validateBalance(final BigDecimal balance) {
        final BigDecimal zero = new BigDecimal(0);
        int test = balance.compareTo(zero);
        System.err.println(test);
        if (balance.compareTo(zero) < 0) {
            throw new IllegalArgumentException("Balance cannot go into overdraft");
        }
    }

    public static void main(String[] args) {
        BigDecimal test = new BigDecimal(-2);
        validateBalance(test);
    }
}
