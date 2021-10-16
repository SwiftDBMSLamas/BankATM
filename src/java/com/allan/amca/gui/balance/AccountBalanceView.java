package com.allan.amca.gui.balance;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.gui.Frameable;
import com.allan.amca.gui.Screen;
import com.allan.amca.transaction.Transaction;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/**
 * Account balance subclass
 * @author allanaranzaso
 */
public class AccountBalanceView extends Screen implements Frameable {
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
    private final AccountBalanceResources r = new AccountBalanceResources();

    {
        client = Client.getClient(GET_CLIENT_REQUEST);
        r.getPropertyValues();
    }

    public AccountBalanceView(final CardLayout layout, final JPanel pane) {
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

        parentPane.add(accountBalancePanel, r.BALANCE_PANEL());
        parentCardLayout.show(parentPane, r.BALANCE_PANEL());
        frame.repaint();
    }

    @Override
    public void addListeners() {
        returnBtn.addActionListener( event -> parentCardLayout.show(parentPane, r.SELECTION_PANEL()));

        printBalanceBtn.addActionListener( event -> {
            Dao<Client, Long> accountDao = DaoFactory.createDao(DaoType.ACCOUNT);
            BigDecimal balance = accountDao.retrieve(client.getClientID());
            JOptionPane.showMessageDialog(
                    frame,
                    String.format(r.CURRENT_BALANCE(), balance),
                    r.ACCOUNT_BALANCE_TITLE(),
                    JOptionPane.INFORMATION_MESSAGE);

        });
        printTransactionsBtn.addActionListener( event -> {
            // TODO: print last 5 transactions
            // Create DAO.. for every element in HashMap.. print?
            JOptionPane.showMessageDialog(frame, "Not implemented yet");
        });
        printLastTransactionBtn.addActionListener( event -> {
            final Dao<Transaction, Long> transactionDao = DaoFactory.createDao(DaoType.TRANSACTION);
            final Transaction lastTransaction = transactionDao.retrieve(client.getClientID());
            JOptionPane.showMessageDialog(
                    frame,
                    String.format(r.LAST_TRANSACTION_DETAILS(),
                            lastTransaction.getTransactionDate(), lastTransaction.getTransactionType(),
                            lastTransaction.getTransactionAmount()),
                    r.TRANSACTION_DETAILS_TITLE(),
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    @Override
    public void initComponents() {
        accountBalanceHeaderLabel   = new JLabel(r.BALANCE_HEADER_TXT());
        printBalanceBtn             = new JButton(r.BALANCE_PRINT_BTN());
        printLastTransactionBtn     = new JButton(r.BALANCE_PRINT_LAST_BTN());
        printTransactionsBtn        = new JButton(r.BALANCE_PRINT_TRANSACTIONS_BTN());
        accountBalancePanel         = new JPanel(new GridBagLayout());
        returnBtn                   = new JButton(r.BALANCE_RETURN_BTN());
        constraints                 = new GridBagConstraints();
    }
}