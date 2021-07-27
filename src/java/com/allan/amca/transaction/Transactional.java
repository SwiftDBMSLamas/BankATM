package com.allan.amca.transaction;

import com.allan.amca.user.Client;

public interface Transactional {

    void performTransaction(TransactionType type, Long client, Double amount);
}
