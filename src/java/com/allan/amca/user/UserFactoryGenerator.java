package com.allan.amca.user;

import com.allan.amca.enums.UserType;

public class UserFactoryGenerator implements UserFactory {

    @Override
    public Person CreateUser(UserType type, String firstName, String lastName, String password) {
        final Person user;

        if (type == UserType.USER) {
            user = new Client(firstName, lastName, password);
        } else{
<<<<<<< HEAD
            throw new IllegalArgumentException("com.allan.amca.Login.User is undefined");
=======
            throw new IllegalArgumentException("User is undefined");
>>>>>>> master
        }
        return user;
    }
}
