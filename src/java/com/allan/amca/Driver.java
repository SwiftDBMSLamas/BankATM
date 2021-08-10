package com.allan.amca;

import com.allan.amca.gui.Screen;

import javax.swing.*;

public class Driver {

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> runUI());
    }

    private static void runUI() {
        final Screen screen = new Screen();

        screen.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.frame.setSize(400, 280);
        screen.frame.setVisible(true);
    }
}
