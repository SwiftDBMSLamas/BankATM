package com.allan.amca;

import com.allan.amca.gui.LoginGUI;
import com.allan.amca.gui.Screen;

import javax.swing.*;

public class Driver {

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(Driver::runUI);

    }

    private static void runUI() {
        final Screen screen = new LoginGUI();
        screen.createUI();

        screen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.frame.setSize(400, 280);
        screen.frame.setLocationRelativeTo(null);
        screen.frame.setVisible(true);
    }
}
