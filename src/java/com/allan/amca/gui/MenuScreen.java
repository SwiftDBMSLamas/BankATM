package com.allan.amca.gui;

import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends Screen implements Frameable {
    private JLabel              transactionMenuWelcomeLabel;
    private JButton             balanceTransactionButton;
    private JButton             withdrawalTransactionButton;
    private JButton             depositTransactionButton;
    private JButton             exitTransactionButton;
    private JPanel              selectionPanel;
    private GridBagConstraints  constraints;
    private final JPanel        parentPane;
    private final CardLayout    parentCardLayout;
    private Client        client;

    public MenuScreen(final CardLayout layout, final JPanel pane) {
        super();
        this.parentCardLayout = layout;
        this.parentPane = pane;
    }

    @Override
    protected void updateUI() {
        // Welcome - label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0,0,0,0);
        selectionPanel.add(transactionMenuWelcomeLabel, constraints);

        // Balance btn
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        selectionPanel.add(balanceTransactionButton, constraints);

        // Withdraw btn
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        selectionPanel.add(withdrawalTransactionButton, constraints);

        // Deposit btn
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        selectionPanel.add(depositTransactionButton, constraints);

        // Exit btn
        constraints.gridy = 4;
        selectionPanel.add(exitTransactionButton, constraints);
        parentPane.add(selectionPanel, resource.SELECTION_PANEL());
        parentCardLayout.show(parentPane, resource.SELECTION_PANEL());
        frame.repaint();
    }

    @Override
    public void addListeners() {
        balanceTransactionButton.addActionListener( event -> {
            final AccountBalanceGUI screen = new AccountBalanceGUI(parentCardLayout, parentPane);
            screen.createUI();
            frame.setTitle(resource.BALANCE_FRAME_TITLE());
        });

        withdrawalTransactionButton.addActionListener( event -> {
            final WithdrawalGUI screen = new WithdrawalGUI(parentCardLayout, parentPane);
            screen.createUI();
            frame.setTitle(resource.WITHDRAW_FRAME_TITLE());
        });

        depositTransactionButton.addActionListener( event -> {
            final DepositGUI screen = new DepositGUI(parentCardLayout, parentPane);
            screen.createUI();
            frame.setTitle(resource.DEPOSIT_FRAME_TITLE());
        });

        exitTransactionButton.addActionListener( event -> {
            final JDialog dialog            = new JDialog(frame, resource.SELECTION_DIALOG_TITLE(), true);
            final JOptionPane logoutPane    = new JOptionPane(resource.SELECTION_DIALOG_TEXT(),
                                                JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

            dialog.setLocationRelativeTo(null);
            dialog.setContentPane(logoutPane);
            // add listener for user selecting yes/no
            logoutPane.addPropertyChangeListener( listener -> {
                String prop = listener.getPropertyName();

                if (dialog.isVisible() &&
                        listener.getSource() == logoutPane &&
                        prop.equals(JOptionPane.VALUE_PROPERTY)) {

                    int value = (Integer) logoutPane.getValue();
                    if (value == JOptionPane.YES_OPTION) {
                        dialog.setVisible(false);
                        parentCardLayout.show(parentPane, resource.LOGIN_PANEL());
                        Client.dispose();
                    } else if (value == JOptionPane.NO_OPTION) {
                        dialog.setVisible(false);
                    }
                }
            });
            dialog.pack();
            dialog.setVisible(true);
        });
    }

    @Override
    public void initComponents() {
        client                          = Client.getClient(GET_CLIENT_REQUEST);
        transactionMenuWelcomeLabel     = new JLabel(String.format(
                                                        resource.SELECTION_WELCOME_TXT(),
                                                        client.getFirstName(),
                                                        client.getLastName()));
        balanceTransactionButton        = new JButton(resource.SELECTION_BALANCE_BTN());
        withdrawalTransactionButton     = new JButton(resource.SELECTION_WITHDRAW_BTN());
        depositTransactionButton        = new JButton(resource.SELECTION_DEPOSIT_BTN());
        exitTransactionButton           = new JButton(resource.SELECTION_EXIT_BTN());
        selectionPanel                  = new JPanel(new GridBagLayout());
        constraints                     = new GridBagConstraints();
    }
}
