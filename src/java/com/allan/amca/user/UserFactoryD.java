package com.allan.amca.user;

/**
 * Factory interface
 * @author allanaranzaso
 * @version 1.0
 * @deprecated see UserFactory.java
 */
public interface UserFactoryD {
    /**
     * Create a user passing in the appropriate arguments.
     * @deprecated
     * @param firstName the user's first name. Cannot be null or empty.
     * @param lastName  the user's last name. Cannot be null or empty.
     * @param password  the user's password. Cannot be null or empty.
     * @return the client
     */
    static Client createUser(String firstName, String lastName, int password) {
        return new Client(firstName, lastName, password);
    }
}
