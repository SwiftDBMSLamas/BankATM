package com.allan.amca.data;

import com.allan.amca.user.Client;
import io.cucumber.java.After;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    DatabaseHandler db;
    Client newUser;
    Client deletedUser;
    Client updatedUser;
    Long deleteID;

    @ParameterType(".*")
    public Client client(String first, String last, String pw) {
        return new Client(first, last, pw);
    }

    @After
    public void tearDown() {
//        db.deleteClient(newUser);
    }

//    Creating new user
    @Given("I want to create a new user")
    public void i_want_to_create_a_new_user() {
        db = DatabaseHandler.newInstance();
    }
    @When("I enter my first name as {string}, my last name as {string}, and my password as {string}")
    public void i_enter_my_first_name_as_my_last_name_as_and_my_password_as(final String fName,
                                                                            final String lName,
                                                                            final String pw) {
        newUser = new Client(fName, lName, pw);

    }
    @Then("A newly created user is created in the database")
    public void a_newly_created_user_is_created_in_the_database() {
        db.addClient(newUser);
        System.out.println("Info: " + newUser.getFirstName() + ", " + newUser.getLastName());
//        final Long clientID = newUser.getClientID();

//        System.out.println("The new user's ID is: " + clientID);
//        assertTrue(db.checkIfClientExists(clientID));
    }

//      Deleting a user
    @Given("I want to delete a current user")
    public void i_want_to_delete_a_current_user() {
        db = DatabaseHandler.newInstance();
    }
    @When("I enter my client card as {long}")
    public void i_enter_my_client_card_as(final Long arg) {
        final boolean bool;

        deleteID = arg;
        bool = db.checkIfClientExists(deleteID);
        if (bool) {
            deletedUser = db.getClient(deleteID);
            db.deleteClient(deleteID);
        } else{
            System.out.println("com.allan.amca.Login.User does not exist in DB.. nothing was deleted");
        }
    }
    @Then("The database should delete my record")
    public void the_database_should_delete_my_record() {
        assertFalse(db.checkIfClientExists(deleteID));
    }

//      Updating a user
    @Given("I want to update a user's information")
    public void i_want_to_update_a_user_s_information() {
        db = DatabaseHandler.newInstance();
    }
    @When("I enter my client card as {long} and I enter my new first name as " +
            "{string} and my last name as {string} and my password as {string}")
    public void i_enter_my_client_card_as_and_i_enter_my_new_first_name_as_and_my_last_name_as_and_my_password_as
            (final Long id, final String firstName,
             final String lastName, final String password) {
        updatedUser = db.getClient(id);
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);
        updatedUser.setPassword(password);
    }
    @Then("The database should update my record")
    public void the_database_should_update_my_record() {
        db.updateClient(updatedUser);
    }

}
