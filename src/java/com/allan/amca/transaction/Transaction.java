package com.allan.amca.transaction;

public abstract class Transaction {
    private Long accountNumber;

    public Transaction(final Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void performTransaction(){
        execute();
    }

    protected abstract void execute();
}
