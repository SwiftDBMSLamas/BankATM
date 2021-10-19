package main.com.allan.amca.gui.menu;

import main.com.allan.amca.gui.ScreenResources;

import java.util.Properties;

public final class MenuResources extends ScreenResources {

    private String SELECTION_WELCOME_TXT;
    private String SELECTION_BALANCE_BTN;
    private String SELECTION_WITHDRAW_BTN;
    private String SELECTION_DEPOSIT_BTN;
    private String SELECTION_EXIT_BTN;
    private String BALANCE_FRAME_TITLE;
    private String WITHDRAW_FRAME_TITLE;
    private String DEPOSIT_FRAME_TITLE;
    private String SELECTION_DIALOG_TITLE;
    private String SELECTION_DIALOG_TEXT;

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

    @Override
    protected void getUIProperties(Properties props) {
        SELECTION_WELCOME_TXT       = props.getProperty("selection.welcomeHeader");
        SELECTION_BALANCE_BTN       = props.getProperty("selection.balanceBtn");
        SELECTION_WITHDRAW_BTN      = props.getProperty("selection.withdrawalBtn");
        SELECTION_DEPOSIT_BTN       = props.getProperty("selection.depositBtn");
        SELECTION_EXIT_BTN          = props.getProperty("selection.exitBtn");
        BALANCE_FRAME_TITLE         = props.getProperty("balance.frameTitle");
        WITHDRAW_FRAME_TITLE        = props.getProperty("withdraw.frameTitle");
        DEPOSIT_FRAME_TITLE         = props.getProperty("deposit.frameTitle");
        SELECTION_DIALOG_TITLE      = props.getProperty("selection.exitDialogTitle");
        SELECTION_DIALOG_TEXT       = props.getProperty("selection.exitDialogText");
    }
}
