package com.allan.amca.junit.transaction;

import com.allan.amca.transaction.Transaction;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TransactionTest {

    protected void getDate(final Transaction transaction,
                           final String      expectedDate) {
        assertThat(transaction.getTransactionDate(), equalTo(expectedDate));
    }

    protected void getType(final Transaction transaction,
                           final String      expectedType) {
        assertThat(transaction.getTransactionType(), equalTo(expectedType));
    }

    protected void getAmount(final Transaction transaction,
                             final BigDecimal  expectedAmount) {
        assertThat(transaction.getTransactionAmount(), equalTo(expectedAmount));
    }

    protected void getID(final Transaction transaction,
                         final int         expectedTID) {
        assertThat(transaction.getTransactionID(), equalTo(expectedTID));
    }
}