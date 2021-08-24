package com.allan.amca.junit.user;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.user.Client;
import com.allan.amca.user.UserFactory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ClientTest extends PersonTest {

    @Test
    void getClientID() {
        Dao<Client, Long> clientDao = DaoFactory.createDao(DaoType.USER);
        Client client1 =  clientDao.retrieve(4519011123012001L);
        Client client2 =  clientDao.retrieve(4519011123012002L);
        Client client3 =  clientDao.retrieve(4519011123012003L);
        Client client4 =  clientDao.retrieve(4519011123012004L);
        Client client5 =  clientDao.retrieve(4519011123012005L);

        getClientIDFromDB(client1, 4519011123012001L);
        getClientIDFromDB(client2,4519011123012002L);
        getClientIDFromDB(client3, 4519011123012003L);
        getClientIDFromDB(client4, 4519011123012004L);
        getClientIDFromDB(client5, 4519011123012005L);
    }

    @Test
    void getFirstName() {
        getFirstName(new Client("Michael", "Smith", "1318"), "Michael");
        getFirstName(new Client("Jane", "Smith", "1234"), "Jane");
        getFirstName(new Client("James", "Nam", "1324"), "James");
        getFirstName(new Client("Jason", "Kim", "2222"), "Jason");
        getFirstName(new Client("Allan", "Aranzaso", "9999"), "Allan");
    }

    @Test
    void getLastName() {
        getLastName(new Client("Michael", "Smith", "1318"), "Smith");
        getLastName(new Client("Jane", "Smith", "1234"), "Smith");
        getLastName(new Client("James", "Nam", "1324"), "Nam");
        getLastName(new Client("Jason", "Kim", "2222"), "Kim");
        getLastName(new Client("Allan", "Aranzaso", "1111"), "Aranzaso");
    }

    @Test
    void getPin() {
        getPin(new Client("Michael", "Smith", "1318"), "1318");
        getPin(new Client("Jane", "Smith", "1200"), "1200");
        getPin(new Client("James", "Nam", "1234"), "1234");
        getPin(new Client("Jason", "Kim", "432111"), "432111");
        getPin(new Client("Allan", "Aranzaso", "55555"), "55555");
    }

    @Test
    void sendClientTest() {

        Client client1 = UserFactory.createUser("Jane", "Doe", "1234");
        Client client2 = UserFactory.createUser("John", "Appleseed", "0613");

        test(client1, 1);
        test(client2, 1);
    }

    @Test
    void sendClientBadTest() {
        Client client1 = UserFactory.createUser("Sam", "Song", "0613");
        Client client2 = UserFactory.createUser("Sam", "San", "1234");

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
        Client client1 = UserFactory.createUser("Sam", "Song", "1234");

        Client.sendClient(1, client1);
        Client.dispose();
        assertThat(null, equalTo(Client.getClient(1)));
    }

    @Test
    void newClientTest() {
        Client client1 = UserFactory.createUser("Sam", "Song", "1234");

        assertThat(client1, equalTo(client1));
    }

    @Test
    void badClient() {
        badClientConstructor(null, "Nam", "1234",
                IllegalArgumentException.class, "First name cannot be empty");
        badClientConstructor("Eric", null, "1234",
                IllegalArgumentException.class, "Last name cannot be empty");
        badClientConstructor("Eric", "Nam", "111",
                IllegalArgumentException.class, "PIN must be minimum 4 digits or more");
        badClientConstructor("", "Nam", "1234",
                IllegalArgumentException.class, "First name cannot be empty");
        badClientConstructor("Eric", "", "1234",
                IllegalArgumentException.class, "Last name cannot be empty");
        badClientConstructor("Eric", "Nam", "1",
                IllegalArgumentException.class, "PIN must be minimum 4 digits or more");
        badClientConstructor("Eric", "Nam", "12",
                IllegalArgumentException.class, "PIN must be minimum 4 digits or more");
    }

}