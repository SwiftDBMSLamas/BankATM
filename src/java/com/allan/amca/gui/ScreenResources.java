package com.allan.amca.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ScreenResources {

    private String SELECTION_PANEL;
    private String DEPOSIT_PANEL;
    private String WITHDRAW_PANEL;
    private String BALANCE_PANEL;
    private String REGISTER_PANEL;
    private String LOGIN_PANEL;
    private String REGISTER_FRAME_TXT;
    private String SELECTION_FRAME_TITLE;

    protected final String propertyFileName = "res_en.properties";
    protected final Properties properties = new Properties();


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

    public String REGISTER_FRAME_TXT() {
        return REGISTER_FRAME_TXT;
    }

    public String SELECTION_FRAME_TITLE() {
        return SELECTION_FRAME_TITLE;
    }

    public void getPropertyValues() {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertyFileName)) {

            if (input == null) {
                throw new FileNotFoundException(String.format("Property file: %s not found.", propertyFileName));
            }
            properties.load(input);

            getUIProperties(properties);

            SELECTION_PANEL             = properties.getProperty("selection.panel");
            DEPOSIT_PANEL               = properties.getProperty("deposit.panel");
            WITHDRAW_PANEL              = properties.getProperty("withdraw.panel");
            BALANCE_PANEL               = properties.getProperty("balance.panel");
            REGISTER_PANEL              = properties.getProperty("register.panel");
            LOGIN_PANEL                 = properties.getProperty("login.panel");
            REGISTER_FRAME_TXT          = properties.getProperty("register.frame");
            SELECTION_FRAME_TITLE       = properties.getProperty("selection.frameTitle");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected abstract void getUIProperties(Properties properties);
}
