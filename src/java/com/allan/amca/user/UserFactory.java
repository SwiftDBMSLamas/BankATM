package com.allan.amca.user;

/**
 * Factory interface
 * @author allanaranzaso
 * @version 1.0
 */
public interface UserFactory {
    /**
     * Create a user passing in the appropriate arguments.
     * @param firstName the user's first name. Cannot be null or empty.
     * @param lastName the user's last name. Cannot be null or empty.
     * @param password the user's password. Cannot be null or empty.
     * @return the client
     */
    Client createUser(String firstName, String lastName, String password);
}
