package com.allan.amca.transaction;

public interface Transactional {

    boolean performTransaction(long client, Double amount);
}
