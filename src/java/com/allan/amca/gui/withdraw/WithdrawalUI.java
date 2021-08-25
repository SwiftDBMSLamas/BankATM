package com.allan.amca.gui.withdraw;

import com.allan.amca.enums.TransactionType;
import com.allan.amca.gui.Frameable;
import com.allan.amca.gui.Screen;
import com.allan.amca.transaction.Transaction;
import com.allan.amca.transaction.TransactionFactory;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/**
 * Withdrawal subclass UI
 * @author allanaranzaso
 */
public class WithdrawalUI extends Screen implements Frameable {
    private JLabel              headWithdrawLabel;
    private JTextField          withdrawTxtField;
    private JButton             withdrawBtn;
    private JButton             returnBtn;
    private JPanel              withdrawPanel;
    private GridBagConstraints  constraints;
    private final JPanel        parentPane;
    private final CardLayout    parentCardLayout;
    private final Client        client;
    private WithdrawalResources r = new WithdrawalResources();

    {
        client = Client.getClient(GET_CLIENT_REQUEST);
        r.getPropertyValues();
    }

    public WithdrawalUI(final CardLayout layout, final JPanel pane) {
        this.parentCardLayout = layout;
        this.parentPane = pane;
    }

    @Override
    protected void updateUI() {
        // Header Label
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        withdrawPanel.add(headWithdrawLabel, constraints);

        // Withdraw Input Field
        constraints.gridy = 1;
        withdrawPanel.add(withdrawTxtField, constraints);

        // Deposit Btn
        constraints.gridy = 2;
        withdrawPanel.add(withdrawBtn, constraints);

        // Cancel Btn
        constraints.gridy = 3;
        withdrawPanel.add(returnBtn, constraints);

        parentPane.add(withdrawPanel, r.WITHDRAW_PANEL());
        parentCardLayout.show(parentPane, r.WITHDRAW_PANEL());
        frame.repaint();
    }

    @Override
    public void addListeners() {
        returnBtn.addActionListener(event -> parentCardLayout.show(parentPane, r.SELECTION_PANEL()));

        withdrawBtn.addActionListener( event -> {
            final boolean[] transactionSuccess      = new boolean[1];
            final int transactionTrue               = 0;
            final BigDecimal withdrawalAmt          = BigDecimal.valueOf( Double.parseDouble(withdrawTxtField.getText() ) );
            final Transaction withdrawalTransaction = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
            final JDialog dialog                    = new JDialog(frame, r.WITHDRAW_DIALOG_TITLE(), true);
            final JOptionPane confirmPane           = new JOptionPane(String.format(r.WITHDRAW_DIALOG_TXT(), withdrawalAmt),
                                                        JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);

            dialog.setLocationRelativeTo(null);
            dialog.setContentPane(confirmPane);
            // add listener for user selecting yes/no
            confirmPane.addPropertyChangeListener( listener -> {
                String prop = listener.getPropertyName();

                if (dialog.isVisible() &&
                        listener.getSource() == confirmPane &&
                        prop.equals(JOptionPane.VALUE_PROPERTY)) {

                    int value = (Integer) confirmPane.getValue();

                    if (value == JOptionPane.YES_OPTION) {
                        // if success, show the success pane
                        try {
                            transactionSuccess[transactionTrue] = withdrawalTransaction
                                                    .performTransaction(client.getClientID(), withdrawalAmt);
                        } catch (IllegalArgumentException ex) {
                            dialog.dispose();
                            withdrawTxtField.setText(null);
                            JOptionPane.showMessageDialog(
                                    frame,
                                    r.WITHDRAW_INSUFFICIENT_ERROR(),
                                    r.WITHDRAW_ERROR_TITLE(),
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        for (int i = 0; i < transactionSuccess.length; i++) {
                            if (transactionSuccess[i]) {
                                dialog.dispose();
                                JOptionPane.showMessageDialog(frame,
                                        r.WITHDRAW_SUCCESS_MSG());
                                setLocationRelativeTo(null);
                                parentCardLayout.show(parentPane, r.SELECTION_PANEL());
                            }
                        }
                    } else if (value == JOptionPane.NO_OPTION) {
                        withdrawTxtField.setText(null);
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
        headWithdrawLabel    = new JLabel(r.WITHDRAW_HEADER_TXT());
        withdrawTxtField     = new JTextField(10);
        withdrawBtn          = new JButton(r.WITHDRAW_BTN());
        returnBtn            = new JButton(r.WITHDRAW_RETURN_BTN());
        withdrawPanel        = new JPanel(new GridBagLayout());
        constraints          = new GridBagConstraints();
    }
}