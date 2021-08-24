package com.allan.amca.junit.login;

import com.allan.amca.login.Login;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class LoginTest {

    @Test
    void loginTest() {
        Login login = Login.getInstance();

        assertThat(login.login(4519011123012000L, "1111"), equalTo(true));
        assertThat(login.login(4519011123012001L, "1234"), equalTo(true));
        assertThat(login.login(4519011123012002L, "1234"), equalTo(true));
        assertThat(login.login(4519011123012003L, "1234"), equalTo(true));
        assertThat(login.login(4519011123012004L, "1234"), equalTo(true));
    }

    @Test
    void badLogin() {
        Login login = Login.getInstance();

        assertThat(login.login(4519011123012000L, "4312"), equalTo(false));
        assertThat(login.login(4519011123012001L, "1111"), equalTo(false));
        assertThat(login.login(4519011123012002L, "9999"), equalTo(false));
        assertThat(login.login(4519011123012003L, "12345"), equalTo(false));
        assertThat(login.login(4519011123012004L, "101010"), equalTo(false));

    }
}