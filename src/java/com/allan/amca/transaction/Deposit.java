package com.allan.amca.transaction;

import com.allan.amca.account.Account;
import com.allan.amca.data.DatabaseHandler;

public class Deposit extends Transaction {

    public Deposit(final Long accountNumber) {
        super(accountNumber);
    }

    @Override
    protected void executeTransaction(final Long client,
                                      final Double amountToDeposit) {
        final DatabaseHandler db     = DatabaseHandler.newInstance();
        final Double INVALID_AMT    = 0.0;
        final Double currentBalance;
        final Double newBalance;

        // update balance
        Account account = new Account();
        currentBalance = account.retrieveBalance(client);

        if (amountToDeposit < INVALID_AMT) {
            throw new IllegalStateException("You have entered an invalid amount");
        }
        newBalance = currentBalance + amountToDeposit;
        final String updateQuery    = "UPDATE account SET balance = " + newBalance + " WHERE clientID = "
                + client + ";";
//        db.executeSQL(updateQuery, client);
    }
}
