package com.allan.amca;

import com.allan.amca.data.UserDaoImpl;
import com.allan.amca.gui.Screen;
import com.allan.amca.gui.ScreenUnused;
import com.allan.amca.login.Login;
import com.allan.amca.user.UserFactory;
import com.allan.amca.user.UserFactoryGenerator;

import javax.swing.*;

public class Driver {

    public static void main(String[] args) {
        UserDaoImpl dbHelper = UserDaoImpl.newInstance();
        final UserFactory userFactory = new UserFactoryGenerator();
        final Login login = Login.getInstance();
        final ScreenUnused screen = new ScreenUnused("ATM");
        final Screen loginScreen = new Screen();

//        screen.Mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        screen.Mainframe.setSize(400, 280);
//        screen.Mainframe.setVisible( true );

        loginScreen.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginScreen.frame.setSize(400, 280);
        loginScreen.frame.setVisible(true);
    }

    private static void runUI() {
        final Screen screen = new Screen();

        screen.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.frame.setSize(400, 280);
        screen.frame.setVisible(true);

    }
}
