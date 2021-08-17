package com.allan.amca.gui;

import com.allan.amca.enums.ScreenType;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ScreenFactory {

    private static final HashMap<ScreenType, Screen> screenMap;
    private static final Screen     screen;
    private static final JPanel     cardPane;
    private static final CardLayout cardLayout;

    static {
        screen = new LoginGUI();
        cardPane = screen.getCardPane();
        cardLayout = screen.getCardLayout();

        screenMap = new HashMap<>();
        screenMap.put(ScreenType.LOGIN, new LoginGUI());
        screenMap.put(ScreenType.REGISTER, new RegisterScreen(cardLayout, cardPane));
        screenMap.put(ScreenType.SELECTION_MENU, new MenuScreen(cardLayout, cardPane));
        screenMap.put(ScreenType.DEPOSIT, new DepositGUI(cardLayout, cardPane));
        screenMap.put(ScreenType.WITHDRAWAL, new WithdrawalGUI(cardLayout, cardPane));
        screenMap.put(ScreenType.ACCOUNT_BALANCE, new AccountBalanceGUI(cardLayout, cardPane));
    }

    public static Screen createScreen(ScreenType type) {
        return screenMap.get(type);
    }

}
