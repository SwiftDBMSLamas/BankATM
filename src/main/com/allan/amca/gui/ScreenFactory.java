package main.com.allan.amca.gui;

import main.com.allan.amca.enums.ScreenType;
import main.com.allan.amca.gui.balance.AccountBalanceView;
import main.com.allan.amca.gui.deposit.DepositView;
import main.com.allan.amca.gui.login.LoginView;
import main.com.allan.amca.gui.menu.MainMenuView;
import main.com.allan.amca.gui.register.RegisterView;
import main.com.allan.amca.gui.withdraw.WithdrawalView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ScreenFactory {

    private static final HashMap<ScreenType, Screen> screenMap;
    private static final LoginView screen;
    private static final JPanel     cardPane;
    private static final CardLayout cardLayout;

    static {
        screen = new LoginView();
        cardPane = screen.getCardPanel();
        cardLayout = screen.getCardLayout();

        screenMap = new HashMap<>();
        screenMap.put(ScreenType.LOGIN, new LoginView());
        screenMap.put(ScreenType.REGISTER, new RegisterView(cardLayout, cardPane));
        screenMap.put(ScreenType.SELECTION_MENU, new MainMenuView(cardLayout, cardPane));
        screenMap.put(ScreenType.DEPOSIT, new DepositView(cardLayout, cardPane));
        screenMap.put(ScreenType.WITHDRAWAL, new WithdrawalView(cardLayout, cardPane));
        screenMap.put(ScreenType.ACCOUNT_BALANCE, new AccountBalanceView(cardLayout, cardPane));
    }

    public static Screen createScreen(ScreenType type) {
        System.err.println(cardPane);
        return screenMap.get(type);
    }

}
