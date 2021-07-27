package com.allan.amca.transaction;

import com.allan.amca.user.Client;

public abstract class Transaction implements Transactional {
    private Long accountNumber;

    public Transaction(final Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public final void performTransaction(final TransactionType type,
                                         final Long client,
                                         final Double amount){
//        if (!(type == TransactionType.DEPOSIT) || !(type == TransactionType.WITHDRAWAL)) {
//            throw new IllegalArgumentException("Unknown transaction type.. aborting");
//        }
        executeTransaction(client, amount);
    }

    public final void updateBalance(final Client client, final Double amount) {
        final String updateBal = "UPDATE account " +
                "SET balance =  '" ;

    }

    protected abstract void executeTransaction(Long client, Double amount);
}
