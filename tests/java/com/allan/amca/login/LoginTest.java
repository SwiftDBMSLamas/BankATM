package com.allan.amca.login;

import com.allan.amca.data.DatabaseHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        final DatabaseHandler db = DatabaseHandler.newInstance();
        final Login login = Login.getInstance();
//        expected, actual
        assertFalse(login.login(4519011123012301L, "pass2"));
        assertTrue(login.login(4519011123012301L, "testing1pass"));

    }
}