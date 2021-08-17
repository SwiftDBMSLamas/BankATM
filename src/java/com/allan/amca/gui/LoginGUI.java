package com.allan.amca.gui;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.login.Login;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Login screen subclass
 * @author allanaranzaso
 */
public class LoginGUI extends Screen implements Frameable {
    private static final int        SEND_CLIENT_REQUEST = 1;
    private static JTextField       clientCardInputField;
    private static JPasswordField   clientPINPasswordField;
    private JLabel                  enterPINLabel;
    private JLabel                  enterCardLabel;
    private JButton                 loginBtn;
    private JButton                 registerBtn;
    private CardLayout              parentCardLayout;
    private JPanel                  parentPane;
    private JPanel                  loginPanel;
    private GridBagConstraints      constraints;
    private JPanel                  keyPanel;
    private Client                  client;


    public LoginGUI() {
        super();
    }

    @Override
    protected void updateUI() {
        createKeypad();

        loginPanel.setBackground(Color.ORANGE);

        // Card Label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        constraints.gridx = 0;
        loginPanel.add(enterCardLabel, constraints);

        // Card Input
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        loginPanel.add(clientCardInputField, constraints);

        // PIN Label
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridx = 0;
        loginPanel.add(enterPINLabel, constraints);

        // PIN Input
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        loginPanel.add(clientPINPasswordField, constraints);

        // Login Btn
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.insets = new Insets(0,0,0,60);
        loginPanel.add(loginBtn, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 3;
        loginPanel.add(registerBtn, constraints);


        clientCardInputField.setToolTipText("Client cards are 16-digits long");
        parentPane.setLayout(parentCardLayout);
        parentPane.add(loginPanel, resource.LOGIN_PANEL());
        frame.add(parentPane);
        frame.repaint();
    }

    private void createKeypad() {
        final String buttonNums = "1234567890";

        for (int i = 0; i < buttonNums.length(); i++) {
            JButton numPad = new JButton(buttonNums.substring(i, i + 1));
            keyPanel.add(numPad);
            numPad.addActionListener(e -> {
                String pin = e.getActionCommand();
                clientPINPasswordField.replaceSelection(pin);
            });
        }
    }

    @Override
    public void addListeners() {
    //TODO: add error messages when login form is not filled out or does not match user/pass
        registerBtn.addActionListener( evt -> {
            final Screen registerScreen = new RegisterScreen(parentCardLayout, parentPane);
            registerScreen.createUI();
            frame.setTitle("Register");
        });

        loginBtn.addActionListener(evt -> {
            final boolean userAuthenticated;
            final MenuScreen menuScreen;
            final Login login           = Login.getInstance();
            final String clientCardStr  = clientCardInputField.getText();
            final long clientCard       = Long.parseLong(clientCardStr);
            final String pinStr         = String.valueOf(clientPINPasswordField.getPassword());

            userAuthenticated = login.login(clientCard, pinStr);
            if (userAuthenticated) {
                Dao<Client, Long> account = DaoFactory.createDao(DaoType.USER);
                client = account.retrieve(clientCard);
                Client.sendClient(SEND_CLIENT_REQUEST, client);

                menuScreen = new MenuScreen(parentCardLayout, parentPane);
                menuScreen.createUI();
                clientCardInputField.setText(null);
                clientPINPasswordField.setText(null);
            }
        });

        clientPINPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //TODO: next iteration fix keypad
                constraints.gridx = 2;
                constraints.gridy = 3;
                loginPanel.add(keyPanel, constraints);
                clientCardInputField.setVisible(false);
                enterCardLabel.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // keypad disappears
                enterCardLabel.setVisible(true);
                enterCardLabel.revalidate();
                enterCardLabel.repaint();
                clientCardInputField.setVisible(true);
                clientCardInputField.revalidate();
                clientCardInputField.repaint();
            }
        });
    }

    @Override
    public void initComponents() {
        enterCardLabel          = new JLabel(resource.LOGIN_CARD_LABEL());
        enterPINLabel           = new JLabel(resource.LOGIN_PIN_LABEL());
        clientCardInputField    = new JTextField(  );
        clientPINPasswordField  = new JPasswordField(  );
        loginBtn                = new JButton(resource.LOGIN_SIGN_IN_BTN());
        registerBtn             = new JButton("Create Account");
        loginPanel              = new JPanel( new GridBagLayout() );
        constraints             = new GridBagConstraints();
        parentCardLayout        = new CardLayout();
        parentPane              = new JPanel();
        keyPanel                = new JPanel(new GridLayout(4,3));
    }
}