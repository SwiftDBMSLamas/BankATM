package com.allan.amca.junit.register;

import org.junit.jupiter.api.function.Executable;

import main.com.allan.amca.register.RegisterViewModel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterTest {

    protected void registerNewUser(final String firstName,
                                   final String lastName,
                                   final String pin) {
        final RegisterViewModel registerViewModel = RegisterViewModel.newInstance();
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
        final RegisterViewModel registerViewModel = RegisterViewModel.newInstance();

        // Executes the register() method and assert that an expectedException is thrown with the expectedMessage
        badRegister(() -> registerViewModel.register(firstName, lastName, pin), expectedException, expectedMessage);
    }
}
