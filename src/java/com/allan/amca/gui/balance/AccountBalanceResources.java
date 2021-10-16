package com.allan.amca.gui.balance;

import com.allan.amca.gui.ScreenResources;

import java.util.Properties;

public final class AccountBalanceResources extends ScreenResources {
    private String BALANCE_HEADER_TXT;
    private String CURRENT_BALANCE;
    private String ACCOUNT_BALANCE_TITLE;
    private String BALANCE_PRINT_BTN;
    private String BALANCE_PRINT_LAST_BTN;
    private String LAST_TRANSACTION_DETAILS;
    private String TRANSACTION_DETAILS_TITLE;
    private String BALANCE_PRINT_TRANSACTIONS_BTN;
    private String BALANCE_RETURN_BTN;

    public String BALANCE_HEADER_TXT() {
        return BALANCE_HEADER_TXT;
    }

    public String CURRENT_BALANCE() {
        return CURRENT_BALANCE;
    }

    public String ACCOUNT_BALANCE_TITLE() {
        return ACCOUNT_BALANCE_TITLE;
    }

    public String BALANCE_PRINT_BTN() {
        return BALANCE_PRINT_BTN;
    }

    public String BALANCE_PRINT_LAST_BTN() {
        return BALANCE_PRINT_LAST_BTN;
    }

    public String LAST_TRANSACTION_DETAILS() {
        return LAST_TRANSACTION_DETAILS;
    }

    public String TRANSACTION_DETAILS_TITLE() {
        return TRANSACTION_DETAILS_TITLE;
    }

    public String BALANCE_PRINT_TRANSACTIONS_BTN() {
        return BALANCE_PRINT_TRANSACTIONS_BTN;
    }

    public String BALANCE_RETURN_BTN() {
        return BALANCE_RETURN_BTN;
    }

    @Override
    protected void getUIProperties(Properties properties) {
        BALANCE_HEADER_TXT          = properties.getProperty("balance.headLabel");
        CURRENT_BALANCE             = properties.getProperty("balance.currentBalance");
        ACCOUNT_BALANCE_TITLE       = properties.getProperty("balance.currentBalanceTitle");
        BALANCE_PRINT_BTN           = properties.getProperty("balance.printBtn");
        BALANCE_PRINT_LAST_BTN      = properties.getProperty("balance.printLastBtn");
        LAST_TRANSACTION_DETAILS    = properties.getProperty("balance.lastTransactionMsg");
        TRANSACTION_DETAILS_TITLE = properties.getProperty("balance.lastTransactionTitle");
        BALANCE_PRINT_TRANSACTIONS_BTN = properties.getProperty("balance.printTransactionBtn");
        BALANCE_RETURN_BTN          = properties.getProperty("balance.returnBtn");
    }
}
