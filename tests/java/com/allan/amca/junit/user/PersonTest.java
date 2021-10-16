package com.allan.amca.junit.user;

import com.allan.amca.user.Client;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {

    protected void testClientMap(final Client  client,
                                 final int     request) {
        assertThat(Client.sendClient(request, client), equalTo(Client.getClient(request)));
    }

    protected void getFirstName(final Client client,
                                final String expectedFirstName) {
        assertThat(client.getFirstName(), equalTo(expectedFirstName));
    }

    protected void getLastName(final Client client,
                               final String expectedLastName) {
        assertThat(client.getLastName(), equalTo(expectedLastName));
    }

    protected void getClientIDFromDB(final Client client,
                               final long expectedID) {
        assertThat(client.getClientID(), equalTo(expectedID));
    }

    protected void badSend(final Executable executable,
                           final Class<? extends Exception> expectedException,
                           final String                     expectedMessage) {
        final Throwable ex;

        ex = assertThrows(expectedException, executable);
        assertThat(ex.getMessage(), equalTo(expectedMessage));
    }

    protected void badSendClient(final Client                       client,
                                 final int                           request,
                                 final Class<? extends Exception>    expectedException,
                                 final String                        expectedMessage) {
        badSend(() -> Client.sendClient(request, client), expectedException, expectedMessage);
    }

    protected void badConstructor(final Executable                  executable,
                                  final Class<? extends Exception>  expectedException,
                                  final String                      expectedMessage) {
        final Throwable ex;

        ex = assertThrows(expectedException, executable);
        assertThat(ex.getMessage(), equalTo(expectedMessage));
    }

    protected void badClientConstructor(final String                      firstName,
                                        final String                      lastName,
                                        final Class<? extends Exception>  expectedException,
                                        final String                      expectedMessage) {

        badConstructor(() -> new Client(firstName, lastName), expectedException, expectedMessage);
    }
}
