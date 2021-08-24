package com.allan.amca.gui.register;

import com.allan.amca.gui.ScreenResources;

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
    protected void getUIProperties(Properties properties) {
        REGISTER_HEADER_TXT         = properties.getProperty("register.header");
        REGISTER_FIRSTNAME_LABEL    = properties.getProperty("register.firstNameLabel");
        REGISTER_LASTNAME_LABEL     = properties.getProperty("register.lastNameLabel");
        REGISTER_PIN_LABEL          = properties.getProperty("register.pinLabel");
        REGISTER_REG_BTN            = properties.getProperty("register.regBtn");
        REGISTER_BACK_BTN           = properties.getProperty("register.backBtn");
        REGISTER_SUCCESS_MSG        = properties.getProperty("register.successMsg");
        REGISTER_SUCCESS_TITLE      = properties.getProperty("register.successTitle");
        REGISTER_ERROR_MSG          = properties.getProperty("register.errorMsg");
        REGISTER_ERROR_TITLE        = properties.getProperty("register.errorTitle");
    }
}
