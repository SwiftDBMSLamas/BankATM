package com.allan.amca.junit.register;

import org.junit.jupiter.api.Test;

public class RegisterControllerTest extends RegisterTest {

    @Test
    void register() {
        registerNewUser("Jane", "Kim", "1111");
        registerNewUser("Noah", "Doe", "9123");
        registerNewUser("John", "Au", "9000");
        registerNewUser("David", "Vu", "4442");
        registerNewUser("Allan", "Aranzaso", "1923");
    }

    @Test
    void badRegister() {

        invalidRegister("", "Doe", "1111",
                IllegalArgumentException.class, "First name cannot be empty");
        invalidRegister("John", "", "1111",
                IllegalArgumentException.class, "Last name cannot be empty");
        invalidRegister("John", "Doe", "",
                IllegalArgumentException.class, "PIN must be minimum 4 digits or more");
        invalidRegister(null, "Doe", "1111",
                IllegalArgumentException.class, "First name cannot be empty");
        invalidRegister("John", null, "1111",
                IllegalArgumentException.class, "Last name cannot be empty");
        invalidRegister("John", "Doe", null,
                IllegalArgumentException.class, "PIN must be minimum 4 digits or more");
        invalidRegister(null, null, "1111",
                IllegalArgumentException.class, "First name cannot be empty");
        invalidRegister(null, "Doe", null,
                IllegalArgumentException.class, "PIN must be minimum 4 digits or more");
        invalidRegister("John", null, null,
                IllegalArgumentException.class, "PIN must be minimum 4 digits or more");
    }
}
