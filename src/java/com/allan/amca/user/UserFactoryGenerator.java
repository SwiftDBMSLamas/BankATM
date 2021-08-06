package com.allan.amca.user;

public class UserFactoryGenerator implements UserFactory {
    @Override
    public Client createUser(String firstName, String lastName, String password) {

        return new Client(firstName, lastName, password);

    }

    public static UserFactory createFactory() {
        final UserFactory factory = new UserFactoryGenerator();
        return factory;
    }
}
