package com.allan.amca.user;

/**
 * Generates the factory and the ability to create a new user
 * @author allanaranzaso
 * @version 1.0
 */
public class UserFactoryGenerator implements UserFactory {
    /**
     * @param firstName the user's first name. Cannot be null or empty.
     * @param lastName the user's last name. Cannot be null or empty.
     * @param password the user's password. Cannot be null or empty.
     * @return the client
     */
    @Override
    public Client createUser(String firstName, String lastName, String password) {
        return new Client(firstName, lastName, password);
    }

    /**
     * Static method to create the factory when you call this method. Allows you to call createUser
     * @return instantiation of UserFactoryGenerator
     */
    public static UserFactory createFactory() {
        final UserFactory factory = new UserFactoryGenerator();
        return factory;
    }
}
