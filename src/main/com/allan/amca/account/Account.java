package main.com.allan.amca.account;

import main.com.allan.amca.user.Client;

import java.math.BigDecimal;

/**
 * This class is not currently used in the application. Will keep around if needed.
 *
 */
public class Account extends AccountDaoImpl<Client, BigDecimal> {

    private BigDecimal accountBalance;
    private static final double INVALID_AMT = 0;
    private static final double ZERO = 0;

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

    /**
     * Validates the balance being input. For now, set to throw exception if value is negative.
     * @param balance the balance to validate. Cannot be negative.
     * @throws IllegalArgumentException if value is negative
     */
    private static void validateBalance(final BigDecimal balance) {
        final BigDecimal zero = BigDecimal.valueOf(ZERO);

        if (balance.compareTo(zero) < INVALID_AMT) {
            throw new IllegalArgumentException("Balance cannot go into overdraft");
        }
    }
}
