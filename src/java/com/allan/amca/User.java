package com.allan.amca;
public final class User implements UserFactory {

    private final String    firstName;
    private final String    lastName;
    private final String    password;

    protected User(final String firstName, final String lastName, final String password) {
        validateUser(firstName, lastName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    @Override
    final public User CreateUser(UserType type) {
        final User user;

        switch (type) {
            case USER -> user = new User(firstName, lastName, password);
            default -> throw new IllegalArgumentException("User is undefined");
        }
        return user;
    }


    private static void validateUser(final String firstName,
                                     final String lastName, final String password) {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        final String userInfo = this.getFirstName() + " " + this.getLastName();
        return userInfo;
    }
}
