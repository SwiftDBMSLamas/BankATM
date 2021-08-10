package com.allan.amca.gui;

import com.allan.amca.data.DaoAbstract;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.data.DaoFactoryGenerator;
import com.allan.amca.data.UserDaoImpl;
import com.allan.amca.enums.DaoType;
import com.allan.amca.enums.TransactionType;
import com.allan.amca.login.Login;
import com.allan.amca.transaction.Transaction;
import com.allan.amca.transaction.TransactionFactory;
import com.allan.amca.user.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private CardLayout card;
    private GridBagConstraints c;
    private DaoFactory dao;

    public Screen() {
        initLogin();
        dao = DaoFactoryGenerator.createFactory();
        System.out.println("Current client: " + Client.getClient(1));
    }

    public JFrame getFrame() {
        return frame;
    }

    private void initLogin() {
        Login login = Login.getInstance();
        setLoginComponents();
        addMenuBar();
        addListeners();
        System.out.println("Launching LOGIN screen...");
        panel.setBackground(Color.ORANGE);
        // Card Label
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridx = 0;
        panel.add(enterCardLabel, c);

        // Card Input
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridy = 0;
        c.gridx = 1;
        panel.add(clientCardInputField, c);

        // PIN Label
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 0;
        panel.add(enterPINLabel, c);

        // PIN Input
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridy = 1;
        c.gridx = 1;
        panel.add(clientPINPasswordField, c);

        // Login Btn
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0,0,0,60);
        panel.add(loginButton, c);

        clientCardInputField.setToolTipText("Client cards are 16-digits long");
        cardPane.setLayout(card);
        cardPane.add(panel, "Login");
        frame.add(cardPane);
        frame.repaint();

        System.out.println("Finished initializing the LOGIN screen...");
        //TODO: add error messages when login form is not filled out or does not match user/pass
        loginButton.addActionListener(evt -> {

            final boolean authenticated;
            final String clientCardStr = clientCardInputField.getText();
            final long clientCard = Long.parseLong(clientCardStr);
            final String password = String.valueOf(clientPINPasswordField.getPassword());

            authenticated = login.login(clientCard, password);
            if (authenticated) {
                client = UserDaoImpl.newInstance().retrieve(clientCard);

                Client.sendClient(SEND_CLIENT_REQUEST, client);
                SelectionMenu sm = new SelectionMenu();
                sm.createMenu();
                clientCardInputField.setText(null);
                clientPINPasswordField.setText(null);
                card.show(cardPane, "Selection");
            }
            //TODO: initialize the screens without saying new
        });
    }

    private void setLoginComponents() {
        frame                   = new JFrame("ATM - Login");
        panel                   = new JPanel( new GridBagLayout());
        cardPane                = new JPanel();
        enterCardLabel          = new JLabel("Client Card:");
        enterPINLabel           = new JLabel("PIN:");
        clientCardInputField    = new JTextField(  );
        clientPINPasswordField  = new JPasswordField(  );
        loginButton             = new JButton("Sign in");
        menuBar                 = new JMenuBar();
        fileMenu                = new JMenu("File");
        editMenu                = new JMenu("Edit");
        card                    = new CardLayout();
        c                       = new GridBagConstraints();
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
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            selectionPanel.add(transactionMenuWelcomeLabel, c);

            // Balance btn
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            selectionPanel.add(balanceTransactionButton, c);

            // Withdraw btn
            c.gridx = 0;
            c.gridy = 2;
            selectionPanel.add(withdrawalTransactionButton, c);

            // Deposit btn
            c.gridx = 0;
            c.gridy = 3;
            selectionPanel.add(depositTransactionButton, c);

            // Exit btn
            c.gridy = 4;
            selectionPanel.add(exitTransactionButton, c);
            cardPane.add(selectionPanel, "Selection");
            frame.repaint();
            addListeners();
        }

        @Override
        public void initComponents() {
            transactionMenuWelcomeLabel     = new JLabel("Welcome, what would you like to do today?");
            balanceTransactionButton        = new JButton("1 - Balance");
            withdrawalTransactionButton     = new JButton("2 - Withdrawal");
            depositTransactionButton        = new JButton("3 - Deposit");
            exitTransactionButton           = new JButton("4 - Exit");
            frame.setTitle("ATM - Menu");
            selectionPanel                  = new JPanel(new GridBagLayout());
        }

        @Override
        public void addListeners() {
            balanceTransactionButton.addActionListener( event -> {
                AccountBalanceScreen balance = new AccountBalanceScreen();
                balance.run();
                frame.setTitle("ATM - Account Balance");
            });

            withdrawalTransactionButton.addActionListener( event -> {
                WithdrawalScreen withdraw = new WithdrawalScreen();
                withdraw.run();
                frame.setTitle("ATM - Withdraw");
            });

            depositTransactionButton.addActionListener( event -> {
                DepositScreen deposit = new DepositScreen();
                deposit.run();
                frame.setTitle("ATM - Deposit");
            });

            exitTransactionButton.addActionListener( event -> {
                card.show(cardPane, "Login");
                Client.dispose();
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
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            depositPanel.add(headDepositLabel, c);

            // Deposit Field
            c.gridy = 1;
            depositPanel.add(depositTxtField, c);

            // Deposit Btn
            c.gridy = 2;
            depositPanel.add(depositBtn, c);

            // Cancel Btn
            c.gridy = 3;
            depositPanel.add(returnBtn, c);

            cardPane.add(depositPanel, "Deposit");
            card.show(cardPane, "Deposit");
            frame.repaint();
            addListeners();

        }

        @Override
        public void addListeners() {
            returnBtn.addActionListener(event -> card.show(cardPane, "Selection"));
            depositBtn.addActionListener( event -> {
                // deposit method
                final double depositAmt = Double.parseDouble(depositTxtField.getText());
                final Transaction depositTransaction = TransactionFactory.createTransaction(TransactionType.DEPOSIT);

                depositTransaction.performTransaction(client.getClientID(), depositAmt);
            });
        }

        @Override
        public void initComponents() {
            headDepositLabel    = new JLabel("Enter the amount you wish to deposit");
            depositTxtField     = new JTextField(15);
            depositBtn          = new JButton("Deposit Amount");
            returnBtn           = new JButton("Back");
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
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            withdrawPanel.add(headWithdrawLabel, c);

            // Withdraw Input Field
            c.gridx = 0;
            c.gridy = 1;
            withdrawPanel.add(withdrawTxtField, c);

            // Deposit Btn
            c.gridx = 0;
            c.gridy = 2;
            withdrawPanel.add(withdrawBtn, c);

            // Cancel Btn
            c.gridy = 3;
            c.gridx = 0;
            withdrawPanel.add(returnBtn, c);

            cardPane.add(withdrawPanel, "Withdraw");
            card.show(cardPane, "Withdraw");
            frame.repaint();
            addListeners();
        }

        @Override
        public void addListeners() {
            returnBtn.addActionListener(event -> card.show(cardPane, "Selection"));
            withdrawBtn.addActionListener( event -> {
                // withdraw method
                final double withdrawalAmt = Double.parseDouble(withdrawTxtField.getText());
                final Transaction withdrawalT = TransactionFactory.createTransaction(TransactionType.WITHDRAWAL);

                withdrawalT.performTransaction(client.getClientID(),withdrawalAmt);
            });
        }

        @Override
        public void initComponents() {
            headWithdrawLabel    = new JLabel("Enter the amount you wish to withdraw");
            withdrawTxtField     = new JTextField(15);
            withdrawBtn          = new JButton("Withdraw");
            returnBtn            = new JButton("Back");
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
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            accountBalancePanel.add(accountBalanceHeaderLabel, c);

            // View Balance button
            c.gridx = 0;
            c.gridy = 1;
            accountBalancePanel.add(printBalanceBtn, c);

            // View Last Transaction Btn
            c.gridx = 0;
            c.gridy = 2;
            accountBalancePanel.add(printLastTransactionBtn, c);

            // View Transactions Btn
            c.gridy = 3;
            c.gridx = 0;
            accountBalancePanel.add(printTransactionsBtn, c);

            // Return btn
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy= -1;
            accountBalancePanel.add(returnBtn, c);
            addListeners();

            cardPane.add(accountBalancePanel, "Balance");
            card.show(cardPane, "Balance");
            frame.repaint();
        }

        @Override
        public void addListeners() {
            returnBtn.addActionListener( event -> {
                card.show(cardPane, "Selection");
            });
            printBalanceBtn.addActionListener( event -> {

                System.err.println("Client's current balance: ");
                DaoAbstract accountDao = dao.createDao(DaoType.ACCOUNT);
                Double balance = (Double) accountDao.retrieve(client.getClientID());
                System.out.println(balance);
            });
        }

        @Override
        public void initComponents() {
            accountBalanceHeaderLabel   = new JLabel("Select an option below");
            printBalanceBtn             = new JButton("View Account Balance");
            printLastTransactionBtn     = new JButton("View Last Transaction");
            printTransactionsBtn        = new JButton("View Transactions");
            accountBalancePanel         = new JPanel(new GridBagLayout());
            returnBtn                   = new JButton("Back");
        }
    }
}


