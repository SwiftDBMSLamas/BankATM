package com.allan.amca.gui;

import com.allan.amca.enums.TransactionType;
import com.allan.amca.transaction.Transaction;
import com.allan.amca.transaction.TransactionFactory;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class DepositScreen extends Screen implements Frameable {
    private JLabel headDepositLabel;
    private JTextField depositTxtField;
    private JButton depositBtn;
    private JButton returnBtn;
    private JPanel depositPanel;
    private GridBagConstraints constraints;
    private final JPanel parentPane;
    private final CardLayout parentCardLayout;
    private final ScreenResources resource = new ScreenResources();
    private final Client client = Client.getClient(1);

    {
        resource.getValues();
    }

    public DepositScreen(final CardLayout layout, final JPanel pane) {
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
        depositPanel.add(headDepositLabel, constraints);

        // Deposit Field
        constraints.gridy = 1;
        depositPanel.add(depositTxtField, constraints);

        // Deposit Btn
        constraints.gridy = 2;
        depositPanel.add(depositBtn, constraints);

        // Cancel Btn
        constraints.gridy = 3;
        depositPanel.add(returnBtn, constraints);

        parentPane.add(depositPanel, resource.DEPOSIT_PANEL());
        parentCardLayout.show(parentPane, resource.DEPOSIT_PANEL());
        frame.repaint();
    }

    @Override
    public void addListeners() {
        returnBtn.addActionListener(event -> parentCardLayout.show(parentPane, resource.SELECTION_PANEL()));
        depositBtn.addActionListener( event -> {

            final boolean[] transactionSuccess      = new boolean[1];
            final BigDecimal depositAmt             = BigDecimal.valueOf( Double.parseDouble( depositTxtField.getText() ) );
            final Transaction depositTransaction    = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
            final JDialog dialog                    = new JDialog(frame, resource.DEPOSIT_DIALOG_TITLE(), true);
            final JOptionPane confirmPane           = new JOptionPane(String.format(resource.DEPOSIT_DIALOG_TEXT(), depositAmt),
                                                        JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);

            dialog.setLocationRelativeTo(null);
            dialog.setContentPane(confirmPane);

            // adds listener for user selecting yes/no
            confirmPane.addPropertyChangeListener( listener -> {
                String prop = listener.getPropertyName();

                if (dialog.isVisible() &&
                        listener.getSource() == confirmPane &&
                        prop.equals(JOptionPane.VALUE_PROPERTY)) {

                    int value = (Integer) confirmPane.getValue();

                    if (value == JOptionPane.YES_OPTION) {
                        // if success, show the success pane
                        try {
                            transactionSuccess[0] = depositTransaction.performTransaction(client.getClientID(), depositAmt);
                        } catch (IllegalArgumentException ex) {
                            depositTxtField.setText(null);
                            dialog.dispose();
                            JOptionPane.showMessageDialog(
                                    frame,
                                    resource.DEPOSIT_ERROR_TXT(),
                                    resource.WITHDRAW_ERROR_TITLE(),
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        for (int i = 0; i < transactionSuccess.length; i++) {
                            if (transactionSuccess[0]) {
                                dialog.dispose();
                                JOptionPane.showMessageDialog(frame,
                                        resource.DEPOSIT_SUCCESS_MSG());
                                setLocationRelativeTo(null);
                                parentCardLayout.show(parentPane, resource.SELECTION_PANEL());
                            }
                        }
                    } else if (value == JOptionPane.NO_OPTION) {
                        depositTxtField.setText(null);
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
        headDepositLabel    = new JLabel(resource.DEPOSIT_HEADER_TXT());
        depositTxtField     = new JTextField(15);
        depositBtn          = new JButton(resource.DEPOSIT_BTN());
        returnBtn           = new JButton(resource.DEPOSIT_RETURN_BTN());
        depositPanel        = new JPanel(new GridBagLayout());
        constraints         = new GridBagConstraints();
    }
}
