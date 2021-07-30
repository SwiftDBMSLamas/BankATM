package com.allan.amca.transaction;

import com.allan.amca.enums.TransactionType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;

public class TransactionFactory {

    protected static HashMap<TransactionType, Transaction> transactionMap;
    private static final int TRANSACTION_MONTH = Calendar.getInstance().get(Calendar.MONTH);
    private static final int TRANSACTION_DAY = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    private static final int TRANSACTION_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final int TRANSACTION_HOUR = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static final int TRANSACTION_MIN = Calendar.getInstance().get(Calendar.MINUTE);
    private static final int TRANSACTION_SEC = Calendar.getInstance().get(Calendar.SECOND);
    private static final String currentDate;


    static {
        transactionMap = new HashMap<>();
        currentDate = formatTime();
        transactionMap.put(TransactionType.DEPOSIT, new Deposit(currentDate));
        transactionMap.put(TransactionType.WITHDRAWAL, new Withdrawal(currentDate));
    }

    public static Transaction createTransaction(final TransactionType type) {
        final Transaction transaction;

        transaction = transactionMap.get(type);

        return transaction;
    }

    @Contract(pure = true)
    private static @NotNull String formatTime() {
        final String date = TRANSACTION_YEAR + "-" + TRANSACTION_MONTH + "-" + TRANSACTION_DAY + " " +
                TRANSACTION_HOUR + ":" + TRANSACTION_MIN + ":" + TRANSACTION_SEC;
        return date;
    }

}
