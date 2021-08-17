package com.allan.amca.gui;

import com.allan.amca.enums.ScreenType;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ScreenFactory {

    private static final HashMap<ScreenType, Screen> screenMap;

    static {
        screenMap = new HashMap<>();
    }

    public static Screen createScreen(ScreenType type) {
        Screen      screen      = new LoginScreen();
        JPanel      cardPane    = screen.getCardPane();
        CardLayout  cardLayout  = screen.getCardLayout();

        switch(type) {
            case LOGIN ->           screen = new LoginScreen();
            case REGISTER ->        screen = new RegisterScreen(cardLayout, cardPane);
            case SELECTION_MENU ->  screen = new MenuScreen(cardLayout, cardPane);
            case DEPOSIT ->         screen = new DepositScreen(cardLayout, cardPane);
            case WITHDRAWAL ->      screen = new WithdrawScreen(cardLayout, cardPane);
            case ACCOUNT_BALANCE -> screen = new AccountBalanceUI(cardLayout, cardPane);
            default -> throw new IllegalArgumentException("Screen does not exist");
        }
        return screen;
    }
}
