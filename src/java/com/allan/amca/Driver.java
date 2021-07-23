package com.allan.amca;

import com.allan.amca.data.DatabaseHelper;
import com.allan.amca.gui.Screen;
import com.allan.amca.login.Login;
import com.allan.amca.user.UserFactory;
import com.allan.amca.user.UserFactoryGenerator;

import javax.swing.*;
import com.allan.amca.user.UserFactory;
import com.allan.amca.user.UserFactoryGenerator;

public class Driver {

    public static void main(String[] args) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance();
        final UserFactory userFactory = new UserFactoryGenerator();
        final Login login = Login.getInstance();
        final Screen screen = new Screen("ATM");

        screen.Mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.Mainframe.setSize(400, 280);
        screen.Mainframe.setVisible( true );

    }
}
