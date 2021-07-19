package com.allan.amca;

import com.allan.amca.data.DatabaseHelper;

public class Driver {

    public static void main(String[] args) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        final User user = new User("Allan", "Aranzaso", "testtest");
        final User user2 = user.CreateUser(UserType.USER);

//        dbHelper.addClient(user);
        System.out.println(user2.getPassword());
    }
}
