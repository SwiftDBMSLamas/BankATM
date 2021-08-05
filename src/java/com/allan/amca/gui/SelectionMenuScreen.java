package com.allan.amca.gui;

import javax.swing.*;
import java.awt.*;

public class SelectionMenuScreen extends JFrame {

    public JFrame frame;
    private JLabel transactionMenuWelcomeLabel;
    private JButton balanceTransactionLabel;
    private JButton withdrawalTransactionLabel;
    private JButton depositTransactionLabel;
    private JButton exitTransactionLabel;

    public SelectionMenuScreen(final String title) {
        super(title);
        createMenu();
    }

    public void createMenu() {
        transactionMenuWelcomeLabel     = new JLabel("Welcome");
        balanceTransactionLabel         = new JButton("1 - Balance");
        withdrawalTransactionLabel      = new JButton("2 - Withdrawal");
        depositTransactionLabel         = new JButton("3 - Deposit");
        exitTransactionLabel            = new JButton("4 - Exit");
        frame                           = new JFrame("ATM - Menu");

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.add(Box.createVerticalGlue());
        frame.add(transactionMenuWelcomeLabel);
        transactionMenuWelcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(balanceTransactionLabel);
        balanceTransactionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(withdrawalTransactionLabel);
        withdrawalTransactionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(depositTransactionLabel);
        depositTransactionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(exitTransactionLabel);
        exitTransactionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(Box.createVerticalGlue());

        frame.repaint();
    }
}
