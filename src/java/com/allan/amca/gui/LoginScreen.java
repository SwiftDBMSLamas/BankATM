package com.allan.amca.gui;

import com.allan.amca.data.UserDaoImpl;
import com.allan.amca.login.Login;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginScreen extends JFrame {

    public JFrame                   frame;
    private JPanel                  panel;
    private static JTextField       clientCardInputField;
    private static JPasswordField   clientPINPasswordField;
    private JLabel                  enterPINLabel;
    private JLabel                  enterCardLabel;
    private JButton                 loginButton;
    private JMenuBar                menuBar;
    private JMenu                   fileMenu;
    private JMenu                   editMenu;
    private Client                  client;
    private static final int        SEND_CLIENT_REQUEST = 1;

    public LoginScreen(final String title) {
        super(title);
        initLogin();
    }

    private void initLogin() {
        Login login = Login.getInstance();
        setLoginComponents();
        addMenuBar();
        addListeners();
        System.out.println("Launching LOGIN screen...");

        frame.setLayout( new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS) );

        frame.add(enterCardLabel);
        frame.add(Box.createRigidArea(new Dimension(100, 0)));
        frame.add(clientCardInputField);
        frame.add(Box.createRigidArea(new Dimension(100, 0)));
        clientCardInputField.setMaximumSize(new Dimension(325, 30));
        frame.add(Box.createRigidArea(new Dimension(100, 0)));

        frame.add(Box.createVerticalGlue());
        panel.add(Box.createRigidArea(new Dimension(25, 0)));
        panel.add(enterCardLabel);
        panel.add(clientCardInputField);
        panel.add(Box.createRigidArea(new Dimension(85, 0)));
        panel.add(enterPINLabel);
        panel.add(clientPINPasswordField);
        panel.add(Box.createRigidArea(new Dimension(85, 0)));
        panel.add(loginButton);
        frame.add(panel);
        frame.add(Box.createVerticalGlue());
        frame.repaint();

        System.out.println("Finished initializing the LOGIN screen...");
        //TODO: add error messages when login form is not filled out or does not match user/pass
        loginButton.addActionListener(evt -> {
            final boolean loginSuccessful;
            final String clientCardStr = clientCardInputField.getText();
            final long clientCard = Long.parseLong(clientCardStr);
            final String password = String.valueOf(clientPINPasswordField.getPassword());

            loginSuccessful = login.login(clientCard, password);
            if (loginSuccessful) {
                client = UserDaoImpl.newInstance().getClient(clientCard);

                Client.sendClient(SEND_CLIENT_REQUEST, client);
                frame.dispose();

                SelectionMenuScreen screen = new SelectionMenuScreen("Menu");
                screen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                screen.frame.setSize(400, 280);
                screen.frame.setVisible(true);
            }
            //TODO: initialize the screens without saying new
        });
    }

    private void setLoginComponents() {
        frame                   = new JFrame("ATM - Login");
        panel                   = new JPanel();
        enterCardLabel          = new JLabel("Client Card:");
        enterPINLabel           = new JLabel("PIN:");
        clientCardInputField    = new JTextField( 15 );
        clientPINPasswordField  = new JPasswordField( 15 );
        loginButton             = new JButton("Sign in");
        menuBar                 = new JMenuBar();
        fileMenu                = new JMenu("File");
        editMenu                = new JMenu("Edit");

    }

    private void addMenuBar() {
        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }

    private void addListeners() {
        clientCardInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        clientCardInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientCardInputField.getText().length() < 16) {
                    throw new IllegalStateException("Card length is invalid");
                }
            }
        });
    }
}
