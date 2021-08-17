package com.allan.amca.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ScreenResources {

    private String SELECTION_PANEL;
    private String DEPOSIT_PANEL;
    private String WITHDRAW_PANEL;
    private String BALANCE_PANEL;
    private String REGISTER_PANEL;
    private String LOGIN_PANEL;
    private String LOGIN_FRAME_TXT;
    private String LOGIN_CARD_LABEL;
    private String LOGIN_PIN_LABEL;
    private String LOGIN_SIGN_IN_BTN;
    private String REGISTER_FRAME_TXT;
    private String REGISTER_HEADER_TXT;
    private String REGISTER_FIRSTNAME_LABEL;
    private String REGISTER_LASTNAME_LABEL;
    private String REGISTER_PIN_LABEL;
    private String REGISTER_REG_BTN;
    private String REGISTER_SUCCESS_MSG;
    private String REGISTER_SUCCESS_TITLE;
    private String SELECTION_WELCOME_TXT;
    private String SELECTION_BALANCE_BTN;
    private String SELECTION_WITHDRAW_BTN;
    private String SELECTION_DEPOSIT_BTN;
    private String SELECTION_EXIT_BTN;
    private String SELECTION_FRAME_TITLE;
    private String BALANCE_FRAME_TITLE;
    private String WITHDRAW_FRAME_TITLE;
    private String DEPOSIT_FRAME_TITLE;
    private String SELECTION_DIALOG_TITLE;
    private String SELECTION_DIALOG_TEXT;
    private String DEPOSIT_DIALOG_TITLE;
    private String DEPOSIT_DIALOG_TEXT;
    private String DEPOSIT_SUCCESS_MSG;
    private String DEPOSIT_HEADER_TXT;
    private String DEPOSIT_BTN;
    private String DEPOSIT_RETURN_BTN;
    private String DEPOSIT_ERROR_TXT;
    private String WITHDRAW_DIALOG_TITLE;
    private String WITHDRAW_DIALOG_TXT;
    private String WITHDRAW_INSUFFICIENT_ERROR;
    private String WITHDRAW_ERROR_TITLE;
    private String WITHDRAW_SUCCESS_MSG;
    private String WITHDRAW_HEADER_TXT;
    private String WITHDRAW_BTN;
    private String WITHDRAW_RETURN_BTN;
    private String BALANCE_HEADER_TXT;
    private String BALANCE_PRINT_BTN;
    private String BALANCE_PRINT_LAST_BTN;
    private String BALANCE_PRINT_TRANSACTIONS_BTN;
    private String BALANCE_RETURN_BTN;

    private String propFileName = "res_en.properties";


    public String SELECTION_PANEL() {
        return SELECTION_PANEL;
    }

    public String DEPOSIT_PANEL() {
        return DEPOSIT_PANEL;
    }

    public String WITHDRAW_PANEL() {
        return WITHDRAW_PANEL;
    }

    public String BALANCE_PANEL() {
        return BALANCE_PANEL;
    }

    public String LOGIN_PANEL() {
        return LOGIN_PANEL;
    }

    public String REGISTER_PANEL() {
        return REGISTER_PANEL;
    }

    public String LOGIN_FRAME_TXT() {
        return LOGIN_FRAME_TXT;
    }

    public String LOGIN_CARD_LABEL() {
        return LOGIN_CARD_LABEL;
    }

    public String LOGIN_PIN_LABEL() {
        return LOGIN_PIN_LABEL;
    }

    public String LOGIN_SIGN_IN_BTN() {
        return LOGIN_SIGN_IN_BTN;
    }

    public String REGISTER_FRAME_TXT() {
        return REGISTER_FRAME_TXT;
    }

    public String REGISTER_HEADER_TXT() {
        return REGISTER_HEADER_TXT;
    }

    public String REGISTER_FIRSTNAME_LABEL() {
        return REGISTER_FIRSTNAME_LABEL;
    }

    public String REGISTER_LASTNAME_LABEL() {
        return REGISTER_LASTNAME_LABEL;
    }

    public String REGISTER_PIN_LABEL() {
        return REGISTER_PIN_LABEL;
    }

    public String REGISTER_REG_BTN() {
        return REGISTER_REG_BTN;
    }

    public String REGISTER_SUCCESS_MSG() {
        return REGISTER_SUCCESS_MSG;
    }

    public String REGISTER_SUCCESS_TITLE() {
        return REGISTER_SUCCESS_TITLE;
    }

    public String SELECTION_WELCOME_TXT() {
        return SELECTION_WELCOME_TXT;
    }

    public String SELECTION_BALANCE_BTN() {
        return SELECTION_BALANCE_BTN;
    }

    public String SELECTION_WITHDRAW_BTN() {
        return SELECTION_WITHDRAW_BTN;
    }

    public String SELECTION_DEPOSIT_BTN() {
        return SELECTION_DEPOSIT_BTN;
    }

    public String SELECTION_EXIT_BTN() {
        return SELECTION_EXIT_BTN;
    }

    public String SELECTION_FRAME_TITLE() {
        return SELECTION_FRAME_TITLE;
    }

    public String BALANCE_FRAME_TITLE() {
        return BALANCE_FRAME_TITLE;
    }

    public String WITHDRAW_FRAME_TITLE() {
        return WITHDRAW_FRAME_TITLE;
    }

    public String DEPOSIT_FRAME_TITLE() {
        return DEPOSIT_FRAME_TITLE;
    }

    public String SELECTION_DIALOG_TITLE() {
        return SELECTION_DIALOG_TITLE;
    }

    public String SELECTION_DIALOG_TEXT() {
        return SELECTION_DIALOG_TEXT;
    }

    public String DEPOSIT_DIALOG_TITLE() {
        return DEPOSIT_DIALOG_TITLE;
    }

    public String DEPOSIT_DIALOG_TEXT() {
        return DEPOSIT_DIALOG_TEXT;
    }

    public String DEPOSIT_SUCCESS_MSG() {
        return DEPOSIT_SUCCESS_MSG;
    }

    public String DEPOSIT_HEADER_TXT() {
        return DEPOSIT_HEADER_TXT;
    }

    public String DEPOSIT_BTN() {
        return DEPOSIT_BTN;
    }

    public String DEPOSIT_ERROR_TXT() {
        return DEPOSIT_ERROR_TXT;
    }

    public String DEPOSIT_RETURN_BTN() {
        return DEPOSIT_RETURN_BTN;
    }

    public String WITHDRAW_DIALOG_TITLE() {
        return WITHDRAW_DIALOG_TITLE;
    }

    public String WITHDRAW_DIALOG_TXT() {
        return WITHDRAW_DIALOG_TXT;
    }

    public String WITHDRAW_INSUFFICIENT_ERROR() {
        return WITHDRAW_INSUFFICIENT_ERROR;
    }

    public String WITHDRAW_ERROR_TITLE() {
        return WITHDRAW_ERROR_TITLE;
    }

    public String WITHDRAW_SUCCESS_MSG() {
        return WITHDRAW_SUCCESS_MSG;
    }

    public String WITHDRAW_HEADER_TXT() {
        return WITHDRAW_HEADER_TXT;
    }

    public String WITHDRAW_BTN() {
        return WITHDRAW_BTN;
    }

    public String WITHDRAW_RETURN_BTN() {
        return WITHDRAW_RETURN_BTN;
    }

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

    public void getValues() {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            Properties properties = new Properties();
            if (input == null) {
                throw new FileNotFoundException(String.format("Property file: %s not found.", propFileName));
            }
            properties.load(input);

            SELECTION_PANEL             = properties.getProperty("selection.panel");
            DEPOSIT_PANEL               = properties.getProperty("deposit.panel");
            WITHDRAW_PANEL              = properties.getProperty("withdraw.panel");
            BALANCE_PANEL               = properties.getProperty("balance.panel");
            REGISTER_PANEL              = properties.getProperty("register.panel");
            LOGIN_PANEL                 = properties.getProperty("login.panel");
            LOGIN_FRAME_TXT             = properties.getProperty("login.frame");
            LOGIN_CARD_LABEL            = properties.getProperty("login.cardLabel");
            LOGIN_PIN_LABEL             = properties.getProperty("login.pinLabel");
            LOGIN_SIGN_IN_BTN           = properties.getProperty("login.signInBtn");
            REGISTER_FRAME_TXT          = properties.getProperty("register.frame");
            REGISTER_HEADER_TXT         = properties.getProperty("register.header");
            REGISTER_FIRSTNAME_LABEL    = properties.getProperty("register.firstNameLabel");
            REGISTER_LASTNAME_LABEL     = properties.getProperty("register.lastNameLabel");
            REGISTER_PIN_LABEL          = properties.getProperty("register.pinLabel");
            REGISTER_REG_BTN            = properties.getProperty("register.regBtn");
            REGISTER_SUCCESS_MSG        = properties.getProperty("register.successMsg");
            REGISTER_SUCCESS_TITLE      = properties.getProperty("register.successTitle");
            SELECTION_WELCOME_TXT       = properties.getProperty("selection.welcomeHeader");
            SELECTION_BALANCE_BTN       = properties.getProperty("selection.balanceBtn");
            SELECTION_WITHDRAW_BTN      = properties.getProperty("selection.withdrawalBtn");
            SELECTION_DEPOSIT_BTN       = properties.getProperty("selection.depositBtn");
            SELECTION_EXIT_BTN          = properties.getProperty("selection.exitBtn");
            SELECTION_FRAME_TITLE       = properties.getProperty("selection.frameTitle");
            BALANCE_FRAME_TITLE         = properties.getProperty("balance.frameTitle");
            WITHDRAW_FRAME_TITLE        = properties.getProperty("withdraw.frameTitle");
            DEPOSIT_FRAME_TITLE         = properties.getProperty("deposit.frameTitle");
            SELECTION_DIALOG_TITLE      = properties.getProperty("selection.exitDialogTitle");
            SELECTION_DIALOG_TEXT       = properties.getProperty("selection.exitDialogText");
            DEPOSIT_DIALOG_TITLE        = properties.getProperty("deposit.confirmDialogTitle");
            DEPOSIT_DIALOG_TEXT         = properties.getProperty("deposit.confirmDialogText");
            DEPOSIT_SUCCESS_MSG         = properties.getProperty("deposit.successMessage");
            DEPOSIT_HEADER_TXT          = properties.getProperty("deposit.headerLabel");
            DEPOSIT_BTN                 = properties.getProperty("deposit.btn");
            DEPOSIT_RETURN_BTN          = properties.getProperty("deposit.returnBtn");
            DEPOSIT_ERROR_TXT           = properties.getProperty("deposit.incorrectAmt");
            WITHDRAW_DIALOG_TITLE       = properties.getProperty("withdraw.confirmDialogTitle");
            WITHDRAW_DIALOG_TXT         = properties.getProperty("withdraw.confirmDialogText");
            WITHDRAW_INSUFFICIENT_ERROR = properties.getProperty("withdraw.insufficientError");
            WITHDRAW_ERROR_TITLE        = properties.getProperty("withdraw.errorTitle");
            WITHDRAW_SUCCESS_MSG        = properties.getProperty("withdraw.successMessage");
            WITHDRAW_HEADER_TXT         = properties.getProperty("withdraw.headLabel");
            WITHDRAW_BTN                = properties.getProperty("withdraw.btn");
            WITHDRAW_RETURN_BTN         = properties.getProperty("withdraw.returnBtn");
            BALANCE_HEADER_TXT          = properties.getProperty("balance.headLabel");
            BALANCE_PRINT_BTN           = properties.getProperty("balance.printBtn");
            BALANCE_PRINT_LAST_BTN      = properties.getProperty("balance.printLastBtn");
            BALANCE_PRINT_TRANSACTIONS_BTN = properties.getProperty("balance.printTransactionBtn");
            BALANCE_RETURN_BTN          = properties.getProperty("balance.returnBtn");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
