package com.allan.amca.gui.login;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.gui.Frameable;
import com.allan.amca.gui.menu.MenuUI;
import com.allan.amca.gui.Screen;
import com.allan.amca.gui.register.RegisterUI;
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
public class LoginUI extends Screen implements Frameable {
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
    private final Login login       = Login.getInstance();
    private final LoginResources r  = new LoginResources();

    {
        r.getPropertyValues();
    }


    public LoginUI() {
        super();
    }

    public CardLayout getCardLayout() {
        return parentCardLayout;
    }

    public JPanel getCardPanel() {
        return parentPane;
    }

    @Override
    protected void updateUI() {
        createKeypad();

        loginPanel.setBackground(Color.ORANGE);

        // Card Label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(enterCardLabel, constraints);

        // Card Input
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        loginPanel.add(clientCardInputField, constraints);

        // PIN Label
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        loginPanel.add(enterPINLabel, constraints);

        // PIN Input
        constraints.gridwidth = 2;
        constraints.gridy = 3;
        loginPanel.add(clientPINPasswordField, constraints);

        // Login Btn
        constraints.gridwidth = 1;
        constraints.gridy = 4;
        loginPanel.add(loginBtn, constraints);

        // Register Btn
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        loginPanel.add(registerBtn, constraints);

        // Keypad panel
        constraints.gridy = 6;
        loginPanel.add(keyPanel, constraints);

        parentPane.setLayout(parentCardLayout);
        parentPane.add(loginPanel, r.LOGIN_PANEL());
        frame.add(parentPane);
        frame.repaint();
    }

    private void createKeypad() {
        final String buttonNums = "123456789*0#";

        for (int i = 0; i < buttonNums.length(); i++) {
            JButton numPad = new JButton(buttonNums.substring(i, i + 1));
            keyPanel.add(numPad);
            numPad.addActionListener(e -> {
                final String pin = e.getActionCommand();
                clientPINPasswordField.replaceSelection(pin);
            });
        }
    }

    @Override
    public void addListeners() {
        registerBtn.addActionListener( evt -> {
            final Screen registerScreen = new RegisterUI(parentCardLayout, parentPane);
            registerScreen.createUI();
            frame.setTitle(r.LOGIN_REGISTER_FRAME());
            System.out.println(frame.getTitle());
        });

        loginBtn.addActionListener(evt -> {
            final boolean userAuthenticated;
            final MenuUI menuUI;
            final String clientCardStr  = clientCardInputField.getText();
            final long clientCard       = Long.parseLong(clientCardStr);
            final String pinStr         = String.valueOf(clientPINPasswordField.getPassword());

            userAuthenticated = login.login(clientCard, pinStr);
            if (!userAuthenticated) {
                JOptionPane.showMessageDialog(frame,
                                    r.LOGIN_FAIL_MESSAGE(),
                                        r.LOGIN_FAIL_TITLE(), JOptionPane.WARNING_MESSAGE);
                clientCardInputField.setBackground(Color.RED);
                clientPINPasswordField.setBackground(Color.RED);

                clientCardInputField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        clientCardInputField.setBackground(Color.WHITE);
                        clientCardInputField.setToolTipText(r.LOGIN_CARD_TOOL_TIP());
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // do nothing
                    }
                });

                clientPINPasswordField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        clientPINPasswordField.setBackground(Color.WHITE);
                        clientPINPasswordField.setToolTipText(r.LOGIN_PIN_TOOL_TIP());
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // do nothing
                    }
                });
            } else {

                Dao<Client, Long> account = DaoFactory.createDao(DaoType.USER);
                client = account.retrieve(clientCard);
                Client.sendClient(SEND_CLIENT_REQUEST, client);

                menuUI = new MenuUI(parentCardLayout, parentPane);
                menuUI.createUI();
                clientCardInputField.setText(null);
                clientPINPasswordField.setText(null);
            }
        });
    }

    @Override
    public void initComponents() {
        enterCardLabel          = new JLabel(r.LOGIN_CARD_LABEL());
        enterPINLabel           = new JLabel(r.LOGIN_PIN_LABEL());
        clientCardInputField    = new JTextField();
        clientPINPasswordField  = new JPasswordField();
        loginBtn                = new JButton(r.LOGIN_SIGN_IN_BTN());
        registerBtn             = new JButton(r.LOGIN_REGISTER_BTN());
        loginPanel              = new JPanel( new GridBagLayout() );
        constraints             = new GridBagConstraints();
        parentCardLayout        = new CardLayout();
        parentPane              = new JPanel();
        keyPanel                = new JPanel(new GridLayout(4,3));
    }
}