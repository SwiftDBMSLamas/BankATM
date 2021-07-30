package com.allan.amca.user;

public class UserFactoryGenerator implements UserFactory {

    public static Client CreateUser(String firstName, String lastName, String password) {

        return new Client(firstName, lastName, password);

    }
}
