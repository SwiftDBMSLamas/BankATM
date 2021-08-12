package com.allan.amca.junit.login;

import com.allan.amca.login.Login;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class LoginTest {

    @Test
    void loginTest() {
        Login login = Login.getInstance();

        assertThat(login.login(4519011123012444L, 1234), equalTo(true));
        assertThat(login.login(4519011123012420L, 1234), equalTo(true));
        assertThat(login.login(4519011123012398L, 1234), equalTo(true));
        assertThat(login.login(4519011123012414L, 1234), equalTo(true));
        assertThat(login.login(4519011123012394L, 1234), equalTo(true));
    }

    @Test
    void badLogin() {
        Login login = Login.getInstance();

        assertThat(login.login(4519011123012444L, 4312), equalTo(false));
        assertThat(login.login(4519011123012420L, 1111), equalTo(false));
        assertThat(login.login(4519011123012398L, 9999), equalTo(false));
        assertThat(login.login(4519011123012414L, 12345), equalTo(false));
        assertThat(login.login(4519011123012394L, 101010), equalTo(false));

    }
}