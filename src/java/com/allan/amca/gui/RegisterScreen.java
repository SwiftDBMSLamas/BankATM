package com.allan.amca.gui;

import com.allan.amca.register.Register;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RegisterScreen extends Screen implements Frameable {
    private JLabel              registerHeaderLabel;
    private JLabel              firstNameLabel;
    private JLabel              lastNameLabel;
    private JLabel              pinLabel;
    private JTextField          firstNameTextField;
    private JTextField          lastNameTextField;
    private JPasswordField      pinField;
    private JButton             confirmRegBtn;
    private JPanel              registerPanel;
    private GridBagConstraints  constraints;
    private final JPanel        parentPane;
    private final CardLayout    parentCardLayout;

    public RegisterScreen(final CardLayout layout, final JPanel pane) {
        this.parentCardLayout   = layout;
        this.parentPane         = pane;
    }

    @Override
    protected void updateUI() {
        // Register label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        registerPanel.add(registerHeaderLabel, constraints);

        // First name label
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        registerPanel.add(firstNameLabel, constraints);

        // First name text field
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 1;
        registerPanel.add(firstNameTextField, constraints);

        // Last name label
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        registerPanel.add(lastNameLabel, constraints);

        // Last name text field
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 2;
        registerPanel.add(lastNameTextField, constraints);

        // PIN label
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        registerPanel.add(pinLabel, constraints);

        // PIN field
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 3;
        registerPanel.add(pinField, constraints);

        // Confirm Reg Btn
        constraints.gridy = 4;
        registerPanel.add(confirmRegBtn, constraints);

        parentPane.add(registerPanel, resource.REGISTER_PANEL());
        parentCardLayout.show(parentPane, resource.REGISTER_PANEL());
        frame.repaint();
    }
    
    @Override
    public void addListeners() {
        confirmRegBtn.addActionListener( evt -> {
            final String firstName = firstNameTextField.getText();
            final String lastName  = lastNameTextField.getText();
            final String pin       = String.valueOf( pinField.getPassword() );
            final Register register= Register.newInstance();
            final boolean registerSuccessful;

            try {
                registerSuccessful = register.register(firstName, lastName, pin);
                if (registerSuccessful) {
                    JOptionPane.showMessageDialog(frame,
                            resource.REGISTER_SUCCESS_MSG(),
                            resource.REGISTER_SUCCESS_TITLE(), JOptionPane.INFORMATION_MESSAGE);
                    final MenuScreen menuScreen = new MenuScreen(parentCardLayout, parentPane);
                    menuScreen.createUI();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void initComponents() {
        constraints = new GridBagConstraints();
        registerHeaderLabel = new JLabel(resource.REGISTER_HEADER_TXT());
        firstNameLabel      = new JLabel(resource.REGISTER_FIRSTNAME_LABEL());
        lastNameLabel       = new JLabel(resource.REGISTER_LASTNAME_LABEL());
        pinLabel            = new JLabel(resource.REGISTER_PIN_LABEL());
        firstNameTextField  = new JTextField();
        lastNameTextField   = new JTextField();
        pinField            = new JPasswordField();
        confirmRegBtn       = new JButton(resource.REGISTER_REG_BTN());
        registerPanel       = new JPanel(new GridBagLayout());
    }
}