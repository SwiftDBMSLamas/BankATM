package com.allan.amca.gui.deposit;

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
 * Deposit subclass UI
 * @author allanaranzaso
 */
public class DepositView extends Screen implements Frameable {
    private JLabel headDepositLabel;
    private JTextField depositTxtField;
    private JButton depositBtn;
    private JButton returnBtn;
    private JPanel depositPanel;
    private GridBagConstraints constraints;
    private final JPanel parentPane;
    private final CardLayout parentCardLayout;
    private final DepositResources r = new DepositResources();
    private final Client client = Client.getClient(1);

    {
        r.getPropertyValues();
    }

    public DepositView(final CardLayout layout, final JPanel pane) {
        this.parentCardLayout = layout;
        this.parentPane = pane;
    }

    @Override
    protected void updateUI() {
        // Header Label
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

        parentPane.add(depositPanel, r.DEPOSIT_PANEL());
        parentCardLayout.show(parentPane, r.DEPOSIT_PANEL());
        frame.repaint();
    }

    @Override
    public void addListeners() {
        returnBtn.addActionListener(event -> parentCardLayout.show(parentPane, r.SELECTION_PANEL()));
        depositBtn.addActionListener( event -> {

            final boolean[] transactionSuccess      = new boolean[1];
            final BigDecimal depositAmt             = BigDecimal.valueOf( Double.parseDouble( depositTxtField.getText() ) );
            final Transaction depositTransaction    = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
            final JDialog dialog                    = new JDialog(frame, r.DEPOSIT_DIALOG_TITLE(), true);
            final JOptionPane confirmPane           = new JOptionPane(String.format(r.DEPOSIT_DIALOG_TEXT(), depositAmt),
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
                                    r.DEPOSIT_ERROR_TXT(),
                                    r.DEPOSIT_ERROR_TITLE(),
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        for (boolean success : transactionSuccess) {
                            if (success) {
                                dialog.dispose();
                                JOptionPane.showMessageDialog(frame,
                                        r.DEPOSIT_SUCCESS_MSG());
                                setLocationRelativeTo(null);
                                parentCardLayout.show(parentPane, r.SELECTION_PANEL());
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
        headDepositLabel    = new JLabel(r.DEPOSIT_HEADER_TXT());
        depositTxtField     = new JTextField(10);
        depositBtn          = new JButton(r.DEPOSIT_BTN());
        returnBtn           = new JButton(r.DEPOSIT_RETURN_BTN());
        depositPanel        = new JPanel(new GridBagLayout());
        constraints         = new GridBagConstraints();
    }
}
