package com.allan.amca.gui.withdraw;

import com.allan.amca.gui.ScreenResources;

import java.util.Properties;

public final class WithdrawalResources extends ScreenResources {

    private String WITHDRAW_DIALOG_TITLE;
    private String WITHDRAW_DIALOG_TXT;
    private String WITHDRAW_INSUFFICIENT_ERROR;
    private String WITHDRAW_ERROR_TITLE;
    private String WITHDRAW_SUCCESS_MSG;
    private String WITHDRAW_HEADER_TXT;
    private String WITHDRAW_BTN;
    private String WITHDRAW_RETURN_BTN;

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

    @Override
    protected void getUIProperties(Properties properties) {
        WITHDRAW_DIALOG_TITLE       = properties.getProperty("withdraw.confirmDialogTitle");
        WITHDRAW_DIALOG_TXT         = properties.getProperty("withdraw.confirmDialogText");
        WITHDRAW_INSUFFICIENT_ERROR = properties.getProperty("withdraw.insufficientError");
        WITHDRAW_ERROR_TITLE        = properties.getProperty("withdraw.errorTitle");
        WITHDRAW_SUCCESS_MSG        = properties.getProperty("withdraw.successMessage");
        WITHDRAW_HEADER_TXT         = properties.getProperty("withdraw.headLabel");
        WITHDRAW_BTN                = properties.getProperty("withdraw.btn");
        WITHDRAW_RETURN_BTN         = properties.getProperty("withdraw.returnBtn");
    }

}
