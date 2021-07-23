package com.allan.amca.user;
public abstract class User implements Person {

    private String          firstName;
    private String          lastName;
<<<<<<< HEAD:src/java/com/allan/amca/user/User.java
    private String          password;
=======
    private final String    password;
>>>>>>> master:src/java/com/allan/amca/User.java

    protected User(final String firstName, final String lastName, final String password) {
        validateUser(firstName, lastName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

<<<<<<< HEAD:src/java/com/allan/amca/user/User.java
    public void setPassword(final String password) {
        this.password = password;
    }

=======
>>>>>>> master:src/java/com/allan/amca/User.java
    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
