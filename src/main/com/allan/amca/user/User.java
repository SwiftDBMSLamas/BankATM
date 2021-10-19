package main.com.allan.amca.user;

public abstract class User implements Person {

    private String          firstName;
    private String          lastName;

    /**
     * Constructor to create a new user
     * @param firstName The user's first name. Cannot be null or empty.
     * @param lastName The user's last name. Cannot be null or empty.
     */
    protected User(final String firstName, final String lastName) {
        validateUser(firstName, lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Validates the arguments passed through the constructor.
     * @param firstName the user's first name. Cannot be null or empty.
     * @param lastName the user's last name. Cannot be null or empty.
     * @throws IllegalArgumentException if user's information is not valid.
     */
    private static void validateUser(final String firstName,
                                     final String lastName) {
        final String FIRST_EMPTY = "First name cannot be empty";
        final String LAST_EMPTY  = "Last name cannot be empty";

        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException(FIRST_EMPTY);
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException(LAST_EMPTY);
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
     *
     * @return User's first and last name formatted
     */
    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
