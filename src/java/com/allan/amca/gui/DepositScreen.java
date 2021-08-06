package com.allan.amca.gui;

import javax.swing.*;
import java.awt.*;

public class DepositScreen extends JFrame implements Frameable {

    private JFrame frame;
    private JLabel headDepositLabel;
    private JTextField depositTxtField;
    private JButton depositBtn;
    private JButton cancelBtn;

    public DepositScreen(final String title) {
        super(title);
        createScreen();
    }

    public JFrame getFrame() {
        return frame;
    }

    private void createScreen() {
        initComponents();
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.PAGE_AXIS));

        frame.add(Box.createVerticalGlue());
        frame.add(headDepositLabel);
        headDepositLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(depositTxtField);
        depositTxtField.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositTxtField.setMaximumSize(new Dimension(200,100));
        frame.add(depositBtn);
        depositBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(cancelBtn);
        cancelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(Box.createVerticalGlue());
        frame.repaint();
    }
    @Override
    public void initComponents() {
        frame = new JFrame("ATM - Deposit");
        headDepositLabel = new JLabel("Enter the amount you wish to deposit");
        depositTxtField = new JTextField(15);
        depositBtn = new JButton("Deposit Amount");
        cancelBtn = new JButton("Cancel transaction");
    }

    @Override
    public void addListeners() {
        cancelBtn.addActionListener( event -> {
            // return to prev screen
        } );
        depositBtn.addActionListener( event -> {
            // deposit method
        });
    }

}
