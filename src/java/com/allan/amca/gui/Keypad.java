package com.allan.amca.gui;

import javax.swing.*;
import java.awt.*;

public class Keypad extends JFrame{

    public JFrame frame;
    private JPanel panel;
    private JButton B1;

    public Keypad() {

    }

    public void createKeypad() {
        panel.setLayout(new GridLayout(4,3));
        final String buttonNums = "123456789*0#";

        for (int i = 0; i < buttonNums.length(); i++) {
            final JButton buttons = new JButton(buttonNums.substring(i, i+ 1));
            panel.add(buttons);
        }
    }

    public static void main(String[] args) {
        Keypad keypad = new Keypad();

    }
}
