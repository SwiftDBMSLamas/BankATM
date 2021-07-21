package com.allan.amca;

import Data.DatabaseHelper;
import com.allan.amca.user.UserFactory;
import com.allan.amca.user.UserFactoryGenerator;

public class Driver {

    public static void main(String[] args) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        final UserFactory userFactory = new UserFactoryGenerator();
        final Login login = Login.getInstance();
//        final Personable user = userFactory.CreateUser(UserType.USER, "Junho", "Lee", "testing1pass");
//        final Personable user2 = userFactory.CreateUser(UserType.USER, "Minjun", "Kim", "testing2pass");
//        System.out.println(user.getClientID());
//        System.out.println(user2.getClientID());
//        dbHelper.addClient(user2);
        login.login("4519011123012301", "testing1pass");
    }
}
