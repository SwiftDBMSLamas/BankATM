package com.allan.amca.gui;

import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;

public class SelectionMenuScreen extends JFrame {

    public JFrame frame;
    private JLabel transactionMenuWelcomeLabel;
    private JButton balanceTransactionButton;
    private JButton withdrawalTransactionButton;
    private JButton depositTransactionButton;
    private JButton exitTransactionButton;

    public SelectionMenuScreen(final String title) {
        super(title);
        createMenu();
    }

    public void createMenu() {
        addSelectionMenuComponents();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        frame.add(Box.createVerticalGlue());
        frame.add(transactionMenuWelcomeLabel);
        transactionMenuWelcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(balanceTransactionButton);
        balanceTransactionButton.setBackground(Color.BLUE);
        balanceTransactionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(withdrawalTransactionButton);
        withdrawalTransactionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(depositTransactionButton);
        depositTransactionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(exitTransactionButton);
        exitTransactionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(Box.createVerticalGlue());
        frame.repaint();

        addListeners();
    }

    private void addSelectionMenuComponents() {
        transactionMenuWelcomeLabel     = new JLabel("Welcome, what would you like to do today?");
        balanceTransactionButton        = new JButton("1 - Balance");
        withdrawalTransactionButton     = new JButton("2 - Withdrawal");
        depositTransactionButton        = new JButton("3 - Deposit");
        exitTransactionButton           = new JButton("4 - Exit");
        frame                           = new JFrame("ATM - Menu");
    }

    private void addListeners() {
        balanceTransactionButton.addActionListener( event -> {


        });

        withdrawalTransactionButton.addActionListener( event -> {
            frame.dispose();
            WithdrawalScreen withdrawalScreen = new WithdrawalScreen();
            withdrawalScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            withdrawalScreen.frame.setSize(400, 280);
            withdrawalScreen.frame.setVisible(true);
        });

        depositTransactionButton.addActionListener( event -> {
            frame.dispose();
            DepositScreen depositScreen = new DepositScreen("ATM - Deposit");
            depositScreen.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            depositScreen.getFrame().setSize(400, 280);
            depositScreen.getFrame().setVisible(true);
        });

        exitTransactionButton.addActionListener( event -> {
            frame.dispose();
            Client.dispose();
        });
    }

    private void rebuildFrame(JFrame screen) {
        screen.setSize(600, 280);
        screen.setVisible(true);

    }
}
