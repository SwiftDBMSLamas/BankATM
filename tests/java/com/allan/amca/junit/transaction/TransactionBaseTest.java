package com.allan.amca.junit.transaction;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.enums.TransactionType;
import com.allan.amca.transaction.Transaction;
import com.allan.amca.transaction.TransactionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TransactionBaseTest extends TransactionTest {

    @Test
    void getTransactionDate() {
        Transaction transaction1 = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
        Transaction transaction2 = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
        Transaction transaction3 = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
        Transaction transaction4 = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
        Transaction transaction5 = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);

        // pulls the current date as the Factory class retrieves the exact current date and time
        getDate(transaction1, transaction1.getTransactionDate());
        getDate(transaction2, transaction2.getTransactionDate());
        getDate(transaction3, transaction3.getTransactionDate());
        getDate(transaction4, transaction4.getTransactionDate());
        getDate(transaction5, transaction5.getTransactionDate());
    }

    @Test
    void getTransactionType() {
        Transaction transaction1 = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
        Transaction transaction2 = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
        Transaction transaction3 = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
        Transaction transaction4 = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
        Transaction transaction5 = TransactionFactory.createTransaction(TransactionType.DEPOSIT);

        getType(transaction1, "deposit");
        getType(transaction2, "withdrawal");
        getType(transaction3, "deposit");
        getType(transaction4, "withdrawal");
        getType(transaction5, "deposit");
    }

    @Test
    void getTransactionAmount() {
        Transaction withdraw = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
        Transaction deposit = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
        Transaction withdraw2 = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
        Transaction deposit2 = TransactionFactory.createTransaction(TransactionType.DEPOSIT);

        deposit.performTransaction(4500123410000001L, BigDecimal.valueOf(100.0));
        withdraw.performTransaction(4500123410000001L, BigDecimal.valueOf(100.0));

        deposit2.performTransaction(4500123410000002L, BigDecimal.valueOf(2143.0));
        withdraw2.performTransaction(4500123410000002L, BigDecimal.valueOf(2143.0));

        getAmount(withdraw, withdraw.getTransactionAmount());
        getAmount(deposit, deposit.getTransactionAmount());
        getAmount(deposit2, deposit2.getTransactionAmount());
        getAmount(withdraw2, withdraw2.getTransactionAmount());
    }

    @Test
    void getTransactionID() {
        Dao<Transaction, Long> transactionDao = DaoFactory.createDao(DaoType.TRANSACTION);
        Transaction retrievedT = transactionDao.retrieve(4500123410000002L);

        getID(retrievedT, 3);
    }

    @Test
    void performTransaction() {
        Transaction withdraw = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
        Transaction deposit = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
        Transaction withdraw2 = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
        Transaction deposit2 = TransactionFactory.createTransaction(TransactionType.DEPOSIT);

        Assertions.assertTrue(withdraw.performTransaction(4500123410000000L, BigDecimal.valueOf(100.0)));
        Assertions.assertTrue(deposit.performTransaction(4500123410000000L, BigDecimal.valueOf(100.0)));

        Assertions.assertTrue(deposit2.performTransaction(4500123410000004L, BigDecimal.valueOf(2143.0)));
        Assertions.assertTrue(withdraw2.performTransaction(4500123410000004L, BigDecimal.valueOf(2143.0)));
    }
}