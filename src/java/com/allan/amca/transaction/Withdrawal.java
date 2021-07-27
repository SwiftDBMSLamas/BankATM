package com.allan.amca.transaction;

import com.allan.amca.account.Account;
import com.allan.amca.data.DatabaseHandler;

public class Withdrawal extends Transaction {


    public Withdrawal(final Long accountNumber) {
        super(accountNumber);
    }

    @Override
    protected void executeTransaction(final Long client,
                                      final Double amountToWithdraw) {
        final DatabaseHandler db     = DatabaseHandler.newInstance();
        final Double currentBalance;
        final Double newBalance;

       // update balance
        Account account = new Account();
        currentBalance = account.retrieveBalance(client);
        System.out.println("Current balance: $" + currentBalance);

        if (currentBalance < amountToWithdraw) {
            throw new IllegalStateException("You do not have enough funds to withdraw the entered amount");
        }
        newBalance = currentBalance - amountToWithdraw;
        System.out.println("New balance: $" +newBalance);
        final String updateQuery    = "UPDATE account SET balance = " + newBalance + " WHERE clientID = "
                + client + ";";
//        db.executeSQL(updateQuery, client);
    }

}
