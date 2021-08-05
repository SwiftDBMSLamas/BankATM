package com.allan.amca.user;
public abstract class User implements Person {

    private String          firstName;
    private String          lastName;
    private String          password;

    /**
     * No-args default constructor
     */
    protected User(){}

    /**
     * Constructor to create a new user
     * @param firstName The user's first name. Cannot be null or empty.
     * @param lastName The user's last name. Cannot be null or empty.
     * @param password The user's password. Cannot be null. Password length must be greater than 7 characters.
     */
    protected User(final String firstName, final String lastName, final String password) {
        validateUser(firstName, lastName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    /**
     * Validates the arguments passed through the constructor.
     * @param firstName the user's first name. Cannot be null or empty.
     * @param lastName the user's last name. Cannot be null or empty.
     * @param password the user's password. Cannot be null. Password length must be greater than 7 characters.
     * @throws IllegalArgumentException if user's information is not valid.
     */
    private static void validateUser(final String firstName,
                                     final String lastName,
                                     final String password) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (password == null || password.length() < 7) {
            throw new IllegalArgumentException("Password must be more than 8 characters");
        }
    }

    /**
     * Get user's first name
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get user's last name
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get user's password
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user's first name
     * @param firstName user's first name to set.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set user's last name
     * @param lastName user's last name to set.
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set user's password
     * @param password - user's password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     *
     * @return User's first and last name formatted
     */
    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
