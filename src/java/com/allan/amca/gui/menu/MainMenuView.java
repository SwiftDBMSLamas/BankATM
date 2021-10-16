package com.allan.amca.gui.menu;

import com.allan.amca.gui.*;
import com.allan.amca.gui.balance.AccountBalanceView;
import com.allan.amca.gui.deposit.DepositView;
import com.allan.amca.gui.withdraw.WithdrawalView;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;

public final class MainMenuView extends Screen implements Frameable {

    private JLabel              transactionMenuWelcomeLabel;
    private JButton             balanceTransactionButton;
    private JButton             withdrawalTransactionButton;
    private JButton             depositTransactionButton;
    private JButton             exitTransactionButton;
    private JPanel              selectionPanel;
    private GridBagConstraints  constraints;
    private final JPanel        parentPane;
    private final CardLayout    parentCardLayout;
    private final MenuResources r = new MenuResources();

    {
        r.getPropertyValues();
    }

    public MainMenuView(final CardLayout layout, final JPanel pane) {
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
        constraints.gridy = 1;
        selectionPanel.add(balanceTransactionButton, constraints);

        // Withdraw btn
        constraints.gridy = 2;
        selectionPanel.add(withdrawalTransactionButton, constraints);

        // Deposit btn
        constraints.gridy = 3;
        selectionPanel.add(depositTransactionButton, constraints);

        // Exit btn
        constraints.gridy = 4;
        selectionPanel.add(exitTransactionButton, constraints);
        parentPane.add(selectionPanel, r.SELECTION_PANEL());
        parentCardLayout.show(parentPane, r.SELECTION_PANEL());
        frame.repaint();
    }

    @Override
    public void addListeners() {
        balanceTransactionButton.addActionListener( event -> {
            final AccountBalanceView screen = new AccountBalanceView(parentCardLayout, parentPane);
            screen.createUI();
            frame.setTitle(r.BALANCE_FRAME_TITLE());
        });

        withdrawalTransactionButton.addActionListener( event -> {
            final WithdrawalView screen = new WithdrawalView(parentCardLayout, parentPane);
            screen.createUI();
            frame.setTitle(r.WITHDRAW_FRAME_TITLE());
        });

        depositTransactionButton.addActionListener( event -> {
            final DepositView screen = new DepositView(parentCardLayout, parentPane);
            screen.createUI();
            frame.setTitle(r.DEPOSIT_FRAME_TITLE());
        });

        exitTransactionButton.addActionListener( event -> {
            final JDialog dialog            = new JDialog(frame, r.SELECTION_DIALOG_TITLE(), true);
            final JOptionPane logoutPane    = new JOptionPane(r.SELECTION_DIALOG_TEXT(),
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
        Client client = Client.getClient(GET_CLIENT_REQUEST);
        transactionMenuWelcomeLabel     = new JLabel(String.format(
                                                        r.SELECTION_WELCOME_TXT(),
                                                        client.getFirstName(),
                                                        client.getLastName()));
        balanceTransactionButton        = new JButton(r.SELECTION_BALANCE_BTN());
        withdrawalTransactionButton     = new JButton(r.SELECTION_WITHDRAW_BTN());
        depositTransactionButton        = new JButton(r.SELECTION_DEPOSIT_BTN());
        exitTransactionButton           = new JButton(r.SELECTION_EXIT_BTN());
        selectionPanel                  = new JPanel(new GridBagLayout());
        constraints                     = new GridBagConstraints();
    }
}
