package com.allan.amca.user;

import com.allan.amca.enums.UserType;

public interface UserFactory {
    Person CreateUser(UserType type, String firstName, String lastName, String password);

}
