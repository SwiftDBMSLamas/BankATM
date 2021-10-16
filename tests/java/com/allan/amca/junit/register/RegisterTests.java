package com.allan.amca.junit.register;

import com.allan.amca.register.RegisterViewModel;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterTests {

    protected void registerNewUser(final String firstName,
                                   final String lastName,
                                   final String pin) {
        RegisterViewModel registerViewModel = RegisterViewModel.newInstance();
        assertThat(registerViewModel.register(firstName, lastName, pin), equalTo(true));
    }

    protected void badRegister(final Executable executable,
                               final Class<? extends Exception> expectedException,
                               final String expectedMessage) {
        final Throwable ex;

        ex = assertThrows(expectedException, executable);
        assertThat(ex.getMessage(), equalTo(expectedMessage));
    }

    protected void invalidRegister(final String firstName,
                                   final String lastName,
                                   final String pin,
                                   final Class<? extends Exception> expectedException,
                                   final String expectedMessage) {
        RegisterViewModel registerViewModel = RegisterViewModel.newInstance();
        badRegister(() -> registerViewModel.register(firstName, lastName, pin), expectedException, expectedMessage);
    }
}
