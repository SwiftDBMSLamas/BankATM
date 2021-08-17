package com.allan.amca.gui;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class AccountBalanceUI extends Screen implements Frameable {
    private JLabel              accountBalanceHeaderLabel;
    private JButton             printBalanceBtn;
    private JButton             printLastTransactionBtn;
    private JButton             printTransactionsBtn;
    private JButton             returnBtn;
    private JPanel              accountBalancePanel;
    private GridBagConstraints  constraints;
    private final JPanel        parentPane;
    private final CardLayout    parentCardLayout;
    private final Client        client;

    {
        client = Client.getClient(GET_CLIENT_REQUEST);
        resource.getValues();
    }

    public AccountBalanceUI(final CardLayout layout, final JPanel pane) {
        this.parentCardLayout = layout;
        this.parentPane = pane;
    }

    @Override
    protected void updateUI() {
        // Header Label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        accountBalancePanel.add(accountBalanceHeaderLabel, constraints);

        // View Balance button
        constraints.gridy = 1;
        accountBalancePanel.add(printBalanceBtn, constraints);

        // View Last Transaction Btn
        constraints.gridy = 2;
        accountBalancePanel.add(printLastTransactionBtn, constraints);

        // View Transactions Btn
        constraints.gridy = 3;
        accountBalancePanel.add(printTransactionsBtn, constraints);

        // Return btn
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridwidth = 1;
        constraints.gridy= -1;
        accountBalancePanel.add(returnBtn, constraints);

        parentPane.add(accountBalancePanel, resource.BALANCE_PANEL());
        parentCardLayout.show(parentPane, resource.BALANCE_PANEL());
        frame.repaint();
    }

    @Override
    public void addListeners() {
        //TODO: determine the behaviour of these buttons
        returnBtn.addActionListener( event -> parentCardLayout.show(parentPane, resource.SELECTION_PANEL()));

        printBalanceBtn.addActionListener( event -> {
            // show dialog with current balance
            System.err.println("Client's current balance: ");
            Dao<Client, Long> accountDao = DaoFactory.createDao(DaoType.ACCOUNT);
            BigDecimal balance = accountDao.retrieve(client.getClientID());
            System.out.println(balance);
            JOptionPane.showMessageDialog(
                    frame,
                    String.format("Your current balance is: $%s", balance),
                    "Account Balance",
                    JOptionPane.INFORMATION_MESSAGE);

        });
        printTransactionsBtn.addActionListener( event -> {
            // implement next iteration
            JOptionPane.showMessageDialog(frame, "Not implemented yet");
        });
        printLastTransactionBtn.addActionListener( event -> {
            // show dialog with last transaction made on the account
            JOptionPane.showMessageDialog(frame, "Not implemented yet");
        });
    }

    @Override
    public void initComponents() {
        accountBalanceHeaderLabel   = new JLabel(resource.BALANCE_HEADER_TXT());
        printBalanceBtn             = new JButton(resource.BALANCE_PRINT_BTN());
        printLastTransactionBtn     = new JButton(resource.BALANCE_PRINT_LAST_BTN());
        printTransactionsBtn        = new JButton(resource.BALANCE_PRINT_TRANSACTIONS_BTN());
        accountBalancePanel         = new JPanel(new GridBagLayout());
        returnBtn                   = new JButton(resource.BALANCE_RETURN_BTN());
        constraints                 = new GridBagConstraints();
    }
}
