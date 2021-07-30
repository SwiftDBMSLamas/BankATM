package com.allan.amca.transaction;

public class Deposit extends Transaction {

    public Deposit(final String transactionDate) {
        super(transactionDate);
    }

    @Override
    protected double calculate(final double currentBalance, final double amount) {
        return currentBalance + amount;
    }
}
