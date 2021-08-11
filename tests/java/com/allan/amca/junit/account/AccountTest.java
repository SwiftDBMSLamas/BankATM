package com.allan.amca.junit.account;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactoryGenerator;
import com.allan.amca.enums.DaoType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class AccountTest {

    @Test
    void getAccountBalance() {
        getBalance(4519011123012372L, 25000.00);
        getBalance(4519011123012370L, 1243.51);
        getBalance(4519011123012380L, 121212.0);
    }

    protected void getBalance(final long clientID,
                              final double expectedBalance) {
        Dao account = DaoFactoryGenerator.createFactory().createDao(DaoType.ACCOUNT);
        double balance = (double) account.retrieve(clientID);
        assertThat(balance, equalTo(expectedBalance));
    }
}