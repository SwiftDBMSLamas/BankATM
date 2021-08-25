package com.allan.amca.gui.balance;

import com.allan.amca.gui.ScreenResources;

import java.util.Properties;

public final class AccountBalanceResources extends ScreenResources {
    private String BALANCE_HEADER_TXT;
    private String BALANCE_PRINT_BTN;
    private String BALANCE_PRINT_LAST_BTN;
    private String BALANCE_PRINT_TRANSACTIONS_BTN;
    private String BALANCE_RETURN_BTN;

    public String BALANCE_HEADER_TXT() {
        return BALANCE_HEADER_TXT;
    }

    public String BALANCE_PRINT_BTN() {
        return BALANCE_PRINT_BTN;
    }

    public String BALANCE_PRINT_LAST_BTN() {
        return BALANCE_PRINT_LAST_BTN;
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
        BALANCE_PRINT_BTN           = properties.getProperty("balance.printBtn");
        BALANCE_PRINT_LAST_BTN      = properties.getProperty("balance.printLastBtn");
        BALANCE_PRINT_TRANSACTIONS_BTN = properties.getProperty("balance.printTransactionBtn");
        BALANCE_RETURN_BTN          = properties.getProperty("balance.returnBtn");
    }
}
