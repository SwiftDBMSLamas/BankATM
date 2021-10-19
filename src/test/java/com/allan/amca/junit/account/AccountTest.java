package com.allan.amca.junit.account;

import org.junit.jupiter.api.Test;

import main.com.allan.amca.data.Dao;
import main.com.allan.amca.data.DaoFactory;
import main.com.allan.amca.enums.DaoType;
import main.com.allan.amca.user.Client;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class AccountTest {

    @Test
    void getAccountBalance() {
        getBalance(4500123410000000L, BigDecimal.valueOf(2000.0));
        getBalance(4500123410000004L, BigDecimal.valueOf(12000.0));
        getBalance(4500123410000003L, BigDecimal.valueOf(0.0));
    }

    protected void getBalance(final long        clientID,
                              final BigDecimal  expectedBalance) {
        Dao<Client, Long> account = DaoFactory.createDao(DaoType.ACCOUNT);
        BigDecimal balance = account.retrieve(clientID);
        assertThat(balance, equalTo(expectedBalance));
    }
}