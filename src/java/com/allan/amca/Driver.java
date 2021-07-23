package com.allan.amca;

<<<<<<< HEAD
import com.allan.amca.data.DatabaseHelper;
import com.allan.amca.gui.Screen;
import com.allan.amca.login.Login;
import com.allan.amca.user.UserFactory;
import com.allan.amca.user.UserFactoryGenerator;

import javax.swing.*;
=======
import Data.DatabaseHelper;
import com.allan.amca.user.UserFactory;
import com.allan.amca.user.UserFactoryGenerator;
>>>>>>> master

public class Driver {

    public static void main(String[] args) {
<<<<<<< HEAD
        DatabaseHelper dbHelper = DatabaseHelper.getInstance();
        final UserFactory userFactory = new UserFactoryGenerator();
        final Login login = Login.getInstance();
        final Screen screen = new Screen("ATM");

        screen.Mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.Mainframe.setSize(400, 280);
        screen.Mainframe.setVisible( true );
=======
        DatabaseHelper dbHelper = new DatabaseHelper();
        final UserFactory userFactory = new UserFactoryGenerator();
        final Login login = Login.getInstance();
//        final Personable user = userFactory.CreateUser(UserType.USER, "Junho", "Lee", "testing1pass");
//        final Personable user2 = userFactory.CreateUser(UserType.USER, "Minjun", "Kim", "testing2pass");
//        System.out.println(user.getClientID());
//        System.out.println(user2.getClientID());
//        dbHelper.addClient(user2);
        login.login("4519011123012301", "testing1pass");
>>>>>>> master
    }
}
