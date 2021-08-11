package com.allan.amca.junit.transaction;

import com.allan.amca.enums.TransactionType;
import com.allan.amca.transaction.Transaction;
import com.allan.amca.transaction.TransactionFactory;
import org.junit.jupiter.api.Test;

class TransactionTest {

    @Test
    void getTransactionDate() {
    }

    @Test
    void getTransactionType() {
    }

    @Test
    void getTransactionAmount() {
    }

    @Test
    void getTransactionID() {
    }

    @Test
    void performTransaction() {
        Transaction transaction = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
        transaction.performTransaction(4519011123012444L, 1000.00);
    }
}