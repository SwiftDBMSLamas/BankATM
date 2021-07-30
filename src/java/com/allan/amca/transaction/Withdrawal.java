package com.allan.amca.transaction;

public class Withdrawal extends Transaction {

    public Withdrawal(final String transactionDate) {
        super(transactionDate);
    }

    @Override
    protected double calculate(double currentBalance, double amount) {
        return currentBalance - amount;
    }

}
