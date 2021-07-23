package com.allan.amca.gui;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    public JFrame Mainframe;
    public static JTextField Inputfield1;
    public static JTextField Inputfield2;
    public static JTextField Inputfield3;
    public static JTextField Inputfield4;
    public JLabel enterPINLabel;
    public JLabel messageJLabel2; // displays message of game status
    public JLabel messageJLabel3;
    public JLabel insertCardLabel;
    public JLabel messageJLabel5;
    public JLabel messageJLabel8;
    public JLabel messageJLabel9;
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

    public Screen(final String title) {
        super(title);
        initLogin();
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

    public void initLogin() {
        Mainframe = new JFrame("ATM");
        System.out.println("Starting...");
        Mainframe.setLayout( new FlowLayout() );
        insertCardLabel = new JLabel("Insert your card then");
        enterPINLabel = new JLabel("  Enter your PIN:   ");

        Inputfield1 = new JTextField( 10 );
        messageJLabel2 = new JLabel("");
        Inputfield2 = new JTextField( 10 );
        loginbutton = new JButton("Login");
        messageJLabel3 = new JLabel("");

        Mainframe.add(insertCardLabel);
        Mainframe.add(enterPINLabel);

        Mainframe.add(Inputfield2);
        Mainframe.add(messageJLabel2);
        Mainframe.add(loginbutton);
        Mainframe.add(messageJLabel2);
        Inputfield2.setEditable(false);
        Mainframe.repaint();
        System.out.println("Finished initializing...");
    }
}
