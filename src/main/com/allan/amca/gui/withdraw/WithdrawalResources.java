package main.com.allan.amca.gui.withdraw;

import main.com.allan.amca.gui.ScreenResources;

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
    protected void getUIProperties(Properties props) {
        WITHDRAW_DIALOG_TITLE       = props.getProperty("withdraw.confirmDialogTitle");
        WITHDRAW_DIALOG_TXT         = props.getProperty("withdraw.confirmDialogText");
        WITHDRAW_INSUFFICIENT_ERROR = props.getProperty("withdraw.insufficientError");
        WITHDRAW_ERROR_TITLE        = props.getProperty("withdraw.errorTitle");
        WITHDRAW_SUCCESS_MSG        = props.getProperty("withdraw.successMessage");
        WITHDRAW_HEADER_TXT         = props.getProperty("withdraw.headLabel");
        WITHDRAW_BTN                = props.getProperty("withdraw.btn");
        WITHDRAW_RETURN_BTN         = props.getProperty("withdraw.returnBtn");
    }

}
