package com.allan.amca.user;

/**
 * Generates the factory and the ability to create a new user
 * @author allanaranzaso
 * @version 1.0
 */
public final class UserFactory {
    /**
     * @param firstName the user's first name. Cannot be null or empty.
     * @param lastName the user's last name. Cannot be null or empty.
     * @param pin the user's password. Cannot be null or empty.
     * @return the client
     */
    public static Client createUser(String firstName, String lastName, String pin) {
        return new Client(firstName, lastName, pin);
    }
}
