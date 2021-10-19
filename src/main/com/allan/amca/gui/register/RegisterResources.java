package main.com.allan.amca.gui.register;

import main.com.allan.amca.gui.ScreenResources;

import java.util.Properties;

public final class RegisterResources extends ScreenResources {

    private String REGISTER_HEADER_TXT;
    private String REGISTER_FIRSTNAME_LABEL;
    private String REGISTER_LASTNAME_LABEL;
    private String REGISTER_PIN_LABEL;
    private String REGISTER_REG_BTN;
    private String REGISTER_BACK_BTN;
    private String REGISTER_SUCCESS_MSG;
    private String REGISTER_SUCCESS_TITLE;
    private String REGISTER_ERROR_MSG;
    private String REGISTER_ERROR_TITLE;

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

    public String REGISTER_BACK_BTN() {
        return REGISTER_BACK_BTN;
    }

    public String REGISTER_SUCCESS_MSG() {
        return REGISTER_SUCCESS_MSG;
    }

    public String REGISTER_SUCCESS_TITLE() {
        return REGISTER_SUCCESS_TITLE;
    }

    public String REGISTER_ERROR_MSG() { return REGISTER_ERROR_MSG; }

    public String REGISTER_ERROR_TITLE() { return REGISTER_ERROR_TITLE; }

    @Override
    protected void getUIProperties(Properties props) {
        REGISTER_HEADER_TXT         = props.getProperty("register.header");
        REGISTER_FIRSTNAME_LABEL    = props.getProperty("register.firstNameLabel");
        REGISTER_LASTNAME_LABEL     = props.getProperty("register.lastNameLabel");
        REGISTER_PIN_LABEL          = props.getProperty("register.pinLabel");
        REGISTER_REG_BTN            = props.getProperty("register.regBtn");
        REGISTER_BACK_BTN           = props.getProperty("register.backBtn");
        REGISTER_SUCCESS_MSG        = props.getProperty("register.successMsg");
        REGISTER_SUCCESS_TITLE      = props.getProperty("register.successTitle");
        REGISTER_ERROR_MSG          = props.getProperty("register.errorMsg");
        REGISTER_ERROR_TITLE        = props.getProperty("register.errorTitle");
    }
}
