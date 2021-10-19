package main.com.allan.amca.gui.login;

import main.com.allan.amca.gui.ScreenResources;

import java.util.Properties;

public final class LoginResources extends ScreenResources {

    private String LOGIN_PANEL;
    private String LOGIN_FRAME_TXT;
    private String LOGIN_CARD_LABEL;
    private String LOGIN_PIN_LABEL;
    private String LOGIN_SIGN_IN_BTN;
    private String LOGIN_REGISTER_BTN;
    private String LOGIN_PIN_TOOL_TIP;
    private String LOGIN_CARD_TOOL_TIP;
    private String LOGIN_FAIL_MESSAGE;
    private String LOGIN_FAIL_TITLE;
    private String LOGIN_REGISTER_FRAME;

    public String LOGIN_PANEL() { return LOGIN_PANEL; }

    public String LOGIN_FRAME_TXT() { return LOGIN_FRAME_TXT; }

    public String LOGIN_CARD_LABEL() {
        return LOGIN_CARD_LABEL;
    }

    public String LOGIN_PIN_LABEL() {
        return LOGIN_PIN_LABEL;
    }

    public String LOGIN_SIGN_IN_BTN() {
        return LOGIN_SIGN_IN_BTN;
    }

    public String LOGIN_REGISTER_BTN() {
        return LOGIN_REGISTER_BTN;
    }

    public String LOGIN_PIN_TOOL_TIP() {
        return LOGIN_PIN_TOOL_TIP;
    }

    public String LOGIN_CARD_TOOL_TIP() {
        return LOGIN_CARD_TOOL_TIP;
    }

    public String LOGIN_FAIL_MESSAGE() {
        return LOGIN_FAIL_MESSAGE;
    }

    public String LOGIN_FAIL_TITLE() {
        return LOGIN_FAIL_TITLE;
    }

    public String LOGIN_REGISTER_FRAME() {
        return LOGIN_REGISTER_FRAME;
    }

    @Override
    protected void getUIProperties(Properties props) {
        LOGIN_PANEL = props.getProperty("login.panel");
        LOGIN_FRAME_TXT = props.getProperty("login.frame");
        LOGIN_CARD_LABEL = props.getProperty("login.cardLabel");
        LOGIN_PIN_LABEL = props.getProperty("login.pinLabel");
        LOGIN_SIGN_IN_BTN = props.getProperty("login.signInBtn");
        LOGIN_REGISTER_BTN = props.getProperty("login.regBtn");
        LOGIN_PIN_TOOL_TIP = props.getProperty("login.PINTip");
        LOGIN_CARD_TOOL_TIP = props.getProperty("login.cardTip");
        LOGIN_FAIL_MESSAGE = props.getProperty("login.failMsg");
        LOGIN_FAIL_TITLE = props.getProperty("login.failTitle");
        LOGIN_REGISTER_FRAME = props.getProperty("login.regFrame");
    }
}
