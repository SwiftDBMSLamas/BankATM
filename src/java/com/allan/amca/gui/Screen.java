package com.allan.amca.gui;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.enums.TransactionType;
import com.allan.amca.listeners.InputListener;
import com.allan.amca.login.Login;
import com.allan.amca.transaction.Transaction;
import com.allan.amca.transaction.TransactionFactory;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Screen extends JFrame {

    public JFrame                   frame;
    private JPanel                  panel, cardPane;
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
    private CardLayout              cardLayout;
    private GridBagConstraints      constraints;
    JPanel keyPanel = new JPanel(new GridLayout(4,3));
    JButton numPad;
    private ScreenResources resource = new ScreenResources();
    {
        resource.getValues();
    }

    public Screen() {
        initLogin();
        System.out.println("Current client logged in: " + Client.getClient(SEND_CLIENT_REQUEST));
    }


    public JFrame getFrame() {
        return frame;
    }

    private void initLogin() {
        setLoginComponents();
        addMenuBar();
        addListeners();
        createKeypad();
        panel.setBackground(Color.ORANGE);

        // Card Label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        constraints.gridx = 0;
        panel.add(enterCardLabel, constraints);

        // Card Input
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        panel.add(clientCardInputField, constraints);

        // PIN Label
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridx = 0;
        panel.add(enterPINLabel, constraints);

        // PIN Input
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        panel.add(clientPINPasswordField, constraints);

        // Login Btn
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.insets = new Insets(0,0,0,60);
        panel.add(loginButton, constraints);


        clientCardInputField.setToolTipText("Client cards are 16-digits long");
        cardPane.setLayout(cardLayout);
        cardPane.add(panel, resource.loginPanel());
        frame.add(cardPane);
        frame.repaint();

        System.out.println("Finished initializing the LOGIN screen...");

    }

    public void createKeypad() {

        final String buttonNums = "1234567890";

        for (int i = 0; i < buttonNums.length(); i++) {
            numPad = new JButton(buttonNums.substring(i, i+ 1));
            keyPanel.add(numPad);
            numPad.addActionListener( e -> {
                String pin = e.getActionCommand();
                clientPINPasswordField.replaceSelection(pin);
            });
        }

    }

    private void setLoginComponents() {
        frame                   = new JFrame(resource.LOGIN_FRAME_TXT());
        panel                   = new JPanel( new GridBagLayout());
        cardPane                = new JPanel();
        enterCardLabel          = new JLabel(resource.LOGIN_CARD_LABEL());
        enterPINLabel           = new JLabel(resource.LOGIN_PIN_LABEL());
        clientCardInputField    = new JTextField(  );
        clientPINPasswordField  = new JPasswordField(  );
        loginButton             = new JButton(resource.LOGIN_SIGN_IN_BTN());
        menuBar                 = new JMenuBar();
        fileMenu                = new JMenu("File");
        editMenu                = new JMenu("Edit");
        cardLayout = new CardLayout();
        constraints = new GridBagConstraints();
    }

    private void addMenuBar() {
        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }

    private void addListeners() {
//TODO: add error messages when login form is not filled out or does not match user/pass

        loginButton.addActionListener(evt -> {
            final Login login = Login.getInstance();
            final boolean authenticated;
            final String clientCardStr = clientCardInputField.getText();
            final long clientCard = Long.parseLong(clientCardStr);
            final String pinStr = String.valueOf(clientPINPasswordField.getPassword());
            final int pin = Integer.parseInt(pinStr);

            authenticated = login.login(clientCard, pin);
            if (authenticated) {
                Dao<Client, Long> account = DaoFactory.createDao(DaoType.USER);
                client = account.retrieve(clientCard);

                Client.sendClient(SEND_CLIENT_REQUEST, client);
                SelectionMenu sm = new SelectionMenu();
                sm.createMenu();
                clientCardInputField.setText(null);
                clientPINPasswordField.setText(null);
                cardLayout.show(cardPane, resource.selectionPanel());
                //TODO: bug- when user logs out, exception is thrown when another user tries to log in
            } else {
                clientCardInputField.getDocument().addDocumentListener((InputListener) listen -> {
                    clientCardInputField.setBackground(Color.RED);
                    clientPINPasswordField.setBackground(Color.RED);
                });
            }
            //TODO: initialize the screens without saying new
        });
        clientPINPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //TODO: next iteration add keypad
                constraints.gridx = 2;
                constraints.gridy = 3;
                panel.add(keyPanel, constraints);
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

    // Selection Menu
    class SelectionMenu implements Frameable {
        private JLabel transactionMenuWelcomeLabel;
        private JButton balanceTransactionButton;
        private JButton withdrawalTransactionButton;
        private JButton depositTransactionButton;
        private JButton exitTransactionButton;
        private JPanel selectionPanel;

        public void createMenu() {
            initComponents();

            // Welcome label
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.insets = new Insets(0,0,0,0);
            selectionPanel.add(transactionMenuWelcomeLabel, constraints);

            // Balance btn
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 1;
            selectionPanel.add(balanceTransactionButton, constraints);

            // Withdraw btn
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 2;
            selectionPanel.add(withdrawalTransactionButton, constraints);

            // Deposit btn
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 3;
            selectionPanel.add(depositTransactionButton, constraints);

            // Exit btn
            constraints.gridy = 4;
            selectionPanel.add(exitTransactionButton, constraints);
            cardPane.add(selectionPanel, resource.selectionPanel());
            frame.repaint();
            addListeners();
        }

        @Override
        public void initComponents() {
            transactionMenuWelcomeLabel     = new JLabel(resource.SELECTION_WELCOME_TXT());
            balanceTransactionButton        = new JButton(resource.SELECTION_BALANCE_BTN());
            withdrawalTransactionButton     = new JButton(resource.SELECTION_WITHDRAW_BTN());
            depositTransactionButton        = new JButton(resource.SELECTION_DEPOSIT_BTN());
            exitTransactionButton           = new JButton(resource.SELECTION_EXIT_BTN());
            frame.setTitle(resource.SELECTION_FRAME_TITLE());
            selectionPanel                  = new JPanel(new GridBagLayout());
        }

        @Override
        public void addListeners() {
            balanceTransactionButton.addActionListener( event -> {
                AccountBalanceScreen balance = new AccountBalanceScreen();
                balance.run();
                frame.setTitle(resource.BALANCE_FRAME_TITLE());
            });

            withdrawalTransactionButton.addActionListener( event -> {
                WithdrawalScreen withdraw = new WithdrawalScreen();
                withdraw.run();
                frame.setTitle(resource.WITHDRAW_FRAME_TITLE());
            });

            depositTransactionButton.addActionListener( event -> {
                DepositScreen deposit = new DepositScreen();
                deposit.run();
                frame.setTitle(resource.DEPOSIT_FRAME_TITLE());
            });

            exitTransactionButton.addActionListener( event -> {
                final JDialog dialog = new JDialog(frame, resource.SELECTION_DIALOG_TITLE(), true);
                final JOptionPane logoutPane = new JOptionPane(resource.SELECTION_DIALOG_TEXT(), JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
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
                            cardLayout.show(cardPane, resource.loginPanel());
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
    }

    class DepositScreen extends Screen implements Frameable {
        private JLabel headDepositLabel;
        private JTextField depositTxtField;
        private JButton depositBtn;
        private JButton returnBtn;
        private JPanel depositPanel;

        public void run() {
            initComponents();

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

            cardPane.add(depositPanel, resource.depositPanel());
            System.err.println(resource.depositPanel());
            cardLayout.show(cardPane, resource.depositPanel());
            frame.repaint();
            addListeners();

        }

        @Override
        public void addListeners() {
            returnBtn.addActionListener(event -> cardLayout.show(cardPane, resource.selectionPanel()));
            depositBtn.addActionListener( event -> {

                final boolean[] transactionSuccess = new boolean[1];
                final double depositAmt = Double.parseDouble(depositTxtField.getText());
                final Transaction depositTransaction = TransactionFactory.createTransaction(TransactionType.DEPOSIT);
                final JDialog dialog = new JDialog(frame, resource.DEPOSIT_DIALOG_TITLE(), true);
                final JOptionPane confirmPane = new JOptionPane(String.format(resource.DEPOSIT_DIALOG_TEXT(), depositAmt), JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
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
                            transactionSuccess[0] = depositTransaction.performTransaction(client.getClientID(), depositAmt);
                            for (int i = 0; i < transactionSuccess.length; i++) {
                                if (transactionSuccess[0]) {
                                    dialog.dispose();
                                    JOptionPane.showMessageDialog(frame,
                                            resource.DEPOSIT_SUCCESS_MSG());
                                    setLocationRelativeTo(null);
                                    cardLayout.show(cardPane, resource.selectionPanel());
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
        }
    }

    class WithdrawalScreen extends Screen implements Frameable {
        private JLabel headWithdrawLabel;
        private JTextField withdrawTxtField;
        private JButton withdrawBtn;
        private JButton returnBtn;
        private JPanel withdrawPanel;

        void run() {
            initComponents();

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

            cardPane.add(withdrawPanel, resource.withdrawPanel());
            cardLayout.show(cardPane, resource.withdrawPanel());
            frame.repaint();
            addListeners();
        }

        @Override
        public void addListeners() {
            returnBtn.addActionListener(event -> cardLayout.show(cardPane, resource.selectionPanel()));

            withdrawBtn.addActionListener( event -> {
                // withdraw method
                final boolean[] transactionSuccess = new boolean[1];
                final double withdrawalAmt = Double.parseDouble(withdrawTxtField.getText());
                final Transaction withdrawalTransaction = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);
                final JDialog dialog = new JDialog(frame, resource.WITHDRAW_DIALOG_TITLE(), true);
                final JOptionPane confirmPane = new JOptionPane(String.format(resource.WITHDRAW_DIALOG_TXT(), withdrawalAmt), JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
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
                            transactionSuccess[0] = withdrawalTransaction.performTransaction(client.getClientID(), withdrawalAmt);
                            for (int i = 0; i < transactionSuccess.length; i++) {
                                if (!transactionSuccess[0]) {
                                    JOptionPane.showMessageDialog(
                                            frame,
                                            resource.WITHDRAW_INSUFFICIENT_ERROR(),
                                            resource.WITHDRAW_ERROR_TITLE(),
                                            JOptionPane.WARNING_MESSAGE);
                                } else {
                                    dialog.dispose();
                                    JOptionPane.showMessageDialog(frame,
                                            resource.WITHDRAW_SUCCESS_MSG());
                                    setLocationRelativeTo(null);
                                    cardLayout.show(cardPane, resource.selectionPanel());
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
        }
    }

    class AccountBalanceScreen extends Screen implements Frameable {
        private JLabel  accountBalanceHeaderLabel;
        private JButton printBalanceBtn;
        private JButton printLastTransactionBtn;
        private JButton printTransactionsBtn;
        private JButton returnBtn;
        private JPanel  accountBalancePanel;

        void run() {
            initComponents();
            // Header Label
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 0;
            accountBalancePanel.add(accountBalanceHeaderLabel, constraints);

            // View Balance button
            constraints.gridy = 1;
            accountBalancePanel.add(printBalanceBtn, constraints);

            // View Last Transaction Btn
            constraints.gridy = 2;
            accountBalancePanel.add(printLastTransactionBtn, constraints);

            // View Transactions Btn
            constraints.gridy = 3;
            accountBalancePanel.add(printTransactionsBtn, constraints);

            // Return btn
            constraints.anchor = GridBagConstraints.FIRST_LINE_START;
            constraints.gridwidth = 1;
            constraints.gridy= -1;
            accountBalancePanel.add(returnBtn, constraints);
            addListeners();

            cardPane.add(accountBalancePanel, resource.balancePanel());
            cardLayout.show(cardPane, resource.balancePanel());
            frame.repaint();
        }

        @Override
        public void addListeners() {
            //TODO: determine the behaviour of these buttons
            returnBtn.addActionListener( event -> cardLayout.show(cardPane, resource.selectionPanel()));

            printBalanceBtn.addActionListener( event -> {
                // show dialog with current balance
                System.err.println("Client's current balance: ");
                Dao<Client, Long> accountDao = DaoFactory.createDao(DaoType.ACCOUNT);
                Double balance = accountDao.retrieve(client.getClientID());
                System.out.println(balance);
            });
            printTransactionsBtn.addActionListener( event -> {
                // implement next iteration
                JOptionPane.showMessageDialog(frame, "Not implemented yet");
            });
            printLastTransactionBtn.addActionListener( event -> {
                // show dialog with last transaction made on the account
                JOptionPane.showMessageDialog(frame, "Not implemented yet");
            });
        }

        @Override
        public void initComponents() {
            accountBalanceHeaderLabel   = new JLabel(resource.BALANCE_HEADER_TXT());
            printBalanceBtn             = new JButton(resource.BALANCE_PRINT_BTN());
            printLastTransactionBtn     = new JButton(resource.BALANCE_PRINT_LAST_BTN());
            printTransactionsBtn        = new JButton(resource.BALANCE_PRINT_TRANSACTIONS_BTN());
            accountBalancePanel         = new JPanel(new GridBagLayout());
            returnBtn                   = new JButton(resource.BALANCE_RETURN_BTN());
        }
    }
}


