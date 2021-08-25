package com.allan.amca.gui;

import com.allan.amca.enums.ScreenType;
import com.allan.amca.gui.balance.AccountBalanceUI;
import com.allan.amca.gui.deposit.DepositUI;
import com.allan.amca.gui.login.LoginUI;
import com.allan.amca.gui.menu.MenuUI;
import com.allan.amca.gui.register.RegisterUI;
import com.allan.amca.gui.withdraw.WithdrawalUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ScreenFactory {

    private static final HashMap<ScreenType, Screen> screenMap;
    private static final LoginUI screen;
    private static final JPanel     cardPane;
    private static final CardLayout cardLayout;

    static {
        screen = new LoginUI();
        cardPane = screen.getCardPanel();
        cardLayout = screen.getCardLayout();

        screenMap = new HashMap<>();
        screenMap.put(ScreenType.LOGIN, new LoginUI());
        screenMap.put(ScreenType.REGISTER, new RegisterUI(cardLayout, cardPane));
        screenMap.put(ScreenType.SELECTION_MENU, new MenuUI(cardLayout, cardPane));
        screenMap.put(ScreenType.DEPOSIT, new DepositUI(cardLayout, cardPane));
        screenMap.put(ScreenType.WITHDRAWAL, new WithdrawalUI(cardLayout, cardPane));
        screenMap.put(ScreenType.ACCOUNT_BALANCE, new AccountBalanceUI(cardLayout, cardPane));
    }

    public static Screen createScreen(ScreenType type) {
        System.err.println(cardPane);
        return screenMap.get(type);
    }

}
