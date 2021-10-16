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