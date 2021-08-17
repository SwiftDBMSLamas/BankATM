package com.allan.amca.gui;

import com.allan.amca.enums.TransactionType;
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
public class WithdrawalGUI extends Screen implements Frameable {
    private JLabel              headWithdrawLabel;
    private JTextField          withdrawTxtField;
    private JButton             withdrawBtn;
    private JButton             returnBtn;
    private JPanel              withdrawPanel;
    private GridBagConstraints  constraints;
    private final JPanel        parentPane;
    private final CardLayout    parentCardLayout;
    private final Client        client;

    {
        client = Client.getClient(GET_CLIENT_REQUEST);
        resource.getValues();
    }

    public WithdrawalGUI(final CardLayout layout, final JPanel pane) {
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
        withdrawPanel.add(headWithdrawLabel, constraints);

        // Withdraw Input Field
        constraints.gridx = 0;
        constraints.gridy = 1;
        withdrawPanel.add(withdrawTxtField, constraints);

        // Deposit Btn
        constraints.gridx = 0;
        constraints.gridy = 2;
        withdrawPanel.add(withdrawBtn, constraints);

        // Cancel Btn
        constraints.gridy = 3;
        constraints.gridx = 0;
        withdrawPanel.add(returnBtn, constraints);

        parentPane.add(withdrawPanel, resource.WITHDRAW_PANEL());
        parentCardLayout.show(parentPane, resource.WITHDRAW_PANEL());
        frame.repaint();
    }

    @Override
    public void addListeners() {
        returnBtn.addActionListener(event -> parentCardLayout.show(parentPane, resource.SELECTION_PANEL()));

        withdrawBtn.addActionListener( event -> {
            // withdraw method
            final boolean[] transactionSuccess      = new boolean[1];
            final int transactionTrue               = 0;
            final BigDecimal withdrawalAmt          = BigDecimal.valueOf( Double.parseDouble(withdrawTxtField.getText() ) );
            final Transaction withdrawalTransaction = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
            final JDialog dialog                    = new JDialog(frame, resource.WITHDRAW_DIALOG_TITLE(), true);
            final JOptionPane confirmPane           = new JOptionPane(String.format(resource.WITHDRAW_DIALOG_TXT(), withdrawalAmt),
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
                                    resource.WITHDRAW_INSUFFICIENT_ERROR(),
                                    resource.WITHDRAW_ERROR_TITLE(),
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        for (int i = 0; i < transactionSuccess.length; i++) {
                            if (transactionSuccess[i]) {
                                dialog.dispose();
                                JOptionPane.showMessageDialog(frame,
                                        resource.WITHDRAW_SUCCESS_MSG());
                                setLocationRelativeTo(null);
                                parentCardLayout.show(parentPane, resource.SELECTION_PANEL());
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
        headWithdrawLabel    = new JLabel(resource.WITHDRAW_HEADER_TXT());
        withdrawTxtField     = new JTextField(15);
        withdrawBtn          = new JButton(resource.WITHDRAW_BTN());
        returnBtn            = new JButton(resource.WITHDRAW_RETURN_BTN());
        withdrawPanel        = new JPanel(new GridBagLayout());
        constraints          = new GridBagConstraints();
    }
}