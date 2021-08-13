package com.allan.amca.transaction;

import java.math.BigDecimal;

/**
 * @author allanaranzaso
 */
public interface Transactional {
    /**
     * Template method to implement in the abstract class, Transaction.
     * @param client the client's ID to search up
     * @param amount the amount to either withdraw or deposit.
     * @return true if the query executed successfully. Otherwise, false.
     */
    boolean performTransaction(long client, BigDecimal amount);
}
