package com.allan.amca.junit.account;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.user.Client;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class AccountTest {

    @Test
    void getAccountBalance() {
        getBalance(4519011123012372L, BigDecimal.valueOf(37000.0));
        getBalance(4519011123012370L, BigDecimal.valueOf(1243.51));
        getBalance(4519011123012380L, BigDecimal.valueOf(2000.0));
    }

    protected void getBalance(final long        clientID,
                              final BigDecimal  expectedBalance) {
        Dao<Client, Long> account = DaoFactory.createDao(DaoType.ACCOUNT);
        BigDecimal balance = account.retrieve(clientID);
        assertThat(balance, equalTo(expectedBalance));
    }
}