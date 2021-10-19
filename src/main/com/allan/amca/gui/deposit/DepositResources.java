package main.com.allan.amca.gui.deposit;

import main.com.allan.amca.gui.ScreenResources;

import java.util.Properties;

public final class DepositResources extends ScreenResources {
    private String DEPOSIT_DIALOG_TITLE;
    private String DEPOSIT_DIALOG_TEXT;
    private String DEPOSIT_SUCCESS_MSG;
    private String DEPOSIT_HEADER_TXT;
    private String DEPOSIT_BTN;
    private String DEPOSIT_RETURN_BTN;
    private String DEPOSIT_ERROR_TXT;
    private String DEPOSIT_ERROR_TITLE;

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

    public String DEPOSIT_ERROR_TITLE() {
        return DEPOSIT_ERROR_TITLE;
    }

    public String DEPOSIT_RETURN_BTN() {
        return DEPOSIT_RETURN_BTN;
    }

    @Override
    protected void getUIProperties(Properties props) {
        DEPOSIT_DIALOG_TITLE        = props.getProperty("deposit.confirmDialogTitle");
        DEPOSIT_DIALOG_TEXT         = props.getProperty("deposit.confirmDialogText");
        DEPOSIT_SUCCESS_MSG         = props.getProperty("deposit.successMessage");
        DEPOSIT_HEADER_TXT          = props.getProperty("deposit.headerLabel");
        DEPOSIT_BTN                 = props.getProperty("deposit.btn");
        DEPOSIT_RETURN_BTN          = props.getProperty("deposit.returnBtn");
        DEPOSIT_ERROR_TXT           = props.getProperty("deposit.incorrectAmt");
        DEPOSIT_ERROR_TITLE         = props.getProperty("deposit.errorTitle");
    }
}
