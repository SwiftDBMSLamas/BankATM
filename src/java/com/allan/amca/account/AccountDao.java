package com.allan.amca.account;

import java.util.List;

public interface AccountDao {
    List<Account> getAllAccounts();
    Account getAccount(long clientID);
    double getAccountBalance(long clientID);
    boolean updateAccountBalance(double newBalance);
}
