package com.allan.amca.gui;

import javax.swing.*;

/**
 * @deprecated see Screen.java
 */
public class ScreenUnused extends JFrame {
    public JFrame Mainframe;
    public static JTextField clientCardInputField;
    public static JPasswordField clientPINPasswordField;
    public static JTextField Inputfield3;
    public static JTextField Inputfield4;
    public JLabel enterPINLabel;
    public JLabel balanceTransactionLabel; // displays message of game status
    public JLabel withdrawalTransactionLabel;
    public JLabel enterCardLabel;
    public JLabel transactionMenuWelcomeLabel;
    public JLabel depositTransactionLabel;
    public JLabel exitTransactionLabel;
    public JLabel messageJLabel10;
    public JButton loginbutton; // creates new game
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JButton button5;
    public JButton Exit;
    public int accnum = 0;
    public int PIN = 0;
    public JLabel messageJLabel6;
    public JLabel messageJLabel7;


    public ScreenUnused(final String title) {
        super(title);

    }
    // displays a message without a carriage return
    public void displayMessage(String message)
    {
        System.out.print(message);
    }

    // display a message with a carriage return
    public void displayMessageLine(String message)
    {
        System.out.println(message);
    }

    // display a dollar amount
    public void displayDollarAmount(double amount)
    {
        System.out.printf("$%,.2f", amount);
    }


    public void createBalanceGUI() {
        Mainframe.getContentPane().removeAll();
    }
}
