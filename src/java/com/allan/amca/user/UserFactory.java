package com.allan.amca.user;

public interface UserFactory {

    Client createUser(String firstName, String lastName, String password);

}
