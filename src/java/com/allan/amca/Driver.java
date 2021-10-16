package com.allan.amca;

import com.allan.amca.data.DatabaseHelper;
import com.allan.amca.enums.ScreenType;
import com.allan.amca.gui.Screen;
import com.allan.amca.gui.ScreenFactory;

import javax.swing.*;

public class Driver {

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(Driver::runUI);

    }

    private static void runUI() {
        final Screen screen = ScreenFactory.createScreen(ScreenType.LOGIN);
        screen.createUI();

        screen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.frame.setSize(414, 496);
        screen.frame.setLocationRelativeTo(null);
        screen.frame.setVisible(true);
        // Create database first
        // Then create the tables
        DatabaseHelper.createDatabase();
    }
}
