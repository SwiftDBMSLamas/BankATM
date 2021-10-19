package com.allan.amca.junit.user;

import main.com.allan.amca.data.Dao;
import main.com.allan.amca.data.DaoFactory;
import main.com.allan.amca.enums.DaoType;
import main.com.allan.amca.user.Client;
import main.com.allan.amca.user.UserFactory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

class ClientTest extends PersonTest {

    @Test
    void getClientID() {
        Dao<Client, Long> clientDao = DaoFactory.createDao(DaoType.USER);
        Client client1 =  clientDao.retrieve(4500123410000000L);
        Client client2 =  clientDao.retrieve(4500123410000002L);
        Client client3 =  clientDao.retrieve(4500123410000003L);
        Client client4 =  clientDao.retrieve(4500123410000004L);
        Client client5 =  clientDao.retrieve(4500123410000005L);

        getClientIDFromDB(client1, 4500123410000000L);
        getClientIDFromDB(client2,4500123410000002L);
        getClientIDFromDB(client3, 4500123410000003L);
        getClientIDFromDB(client4, 4500123410000004L);
        getClientIDFromDB(client5, 4500123410000005L);
    }

    @Test
    void getFirstName() {
        getFirstName(new Client("Michael", "Smith"), "Michael");
        getFirstName(new Client("Jane", "Smith"), "Jane");
        getFirstName(new Client("James", "Nam"), "James");
        getFirstName(new Client("Jason", "Kim"), "Jason");
        getFirstName(new Client("Allan", "Aranzaso"), "Allan");
    }

    @Test
    void getLastName() {
        getLastName(new Client("Michael", "Smith"), "Smith");
        getLastName(new Client("Jane", "Smith"), "Smith");
        getLastName(new Client("James", "Nam"), "Nam");
        getLastName(new Client("Jason", "Kim"), "Kim");
        getLastName(new Client("Allan", "Aranzaso"), "Aranzaso");
    }

    @Test
    void sendClientTest() {

        Client client1 = UserFactory.createUser("Jane", "Doe");
        Client client2 = UserFactory.createUser("John", "Appleseed");

        testClientMap(client1, 1);
        testClientMap(client2, 1);
    }

    @Test
    void sendClientBadTest() {
        Client client1 = UserFactory.createUser("Sam", "Song");
        Client client2 = UserFactory.createUser("Sam", "San");

        badSendClient(client1, 0, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(client1, 2, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(client1, 5, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(client2, 0, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(client2, 2, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(client2, 10, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(null, 1, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(null, 0, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(null, 2, IllegalArgumentException.class, "Request or Client argument is invalid.");
        badSendClient(null, 100, IllegalArgumentException.class, "Request or Client argument is invalid.");
    }

    @Test
    void disposeTest() {
        Client client1 = UserFactory.createUser("Sam", "Song");

        Client.sendClient(1, client1);
        Client.dispose();
        assertThat(null, equalTo(Client.getClient(1)));
    }

    @Test
    void newClientTest() {
        Client client1 = UserFactory.createUser("Sam", "Song");

        assertThat(client1, equalTo(client1));
    }

    @Test
    void badClient() {
        badClientConstructor(null, "Nam", IllegalArgumentException.class, "First name cannot be empty");
        badClientConstructor("Eric", null, IllegalArgumentException.class, "Last name cannot be empty");
        badClientConstructor("", "Nam", IllegalArgumentException.class, "First name cannot be empty");
        badClientConstructor("Eric", "", IllegalArgumentException.class, "Last name cannot be empty");
    }

}