package com.allan.amca.junit.register;

import com.allan.amca.register.Register;

import org.junit.jupiter.api.function.Executable;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterTests {

    protected void registerNewUser(final String firstName,
                                   final String lastName,
                                   final String pin) {
        Register register = Register.newInstance();
        try {
            assertThat(register.register(firstName, lastName, pin), equalTo(true));
        } catch (SQLException ex) {
            ex.printStackTrace();
            assert false;
        }
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
        Register register = Register.newInstance();
        badRegister(() -> register.register(firstName, lastName, pin), expectedException, expectedMessage);
    }
}
