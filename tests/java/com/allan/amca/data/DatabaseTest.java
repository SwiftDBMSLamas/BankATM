package com.allan.amca.data;

import com.allan.amca.user.Client;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {
    UserDaoImpl db;
    Client newUser;
    Client updatedUser;
    long deleteID;


    @Given("I want to create a new user")
    public void i_want_to_create_a_new_user() {
        db = UserDaoImpl.newInstance();
    }
    @When("I enter my first name as {string}, my last name as {string}, and my password as {string}")
    public void i_enter_my_first_name_as_my_last_name_as_and_my_password_as(final String firstName,
                                                                            final String lastName,
                                                                            final String password) {
//        newUser = UserFactoryGenerator.CreateUser(firstName, lastName, password);
        newUser = new Client(firstName, lastName, password);
    }
    @Then("A newly created user is created in the database")
    public void a_newly_created_user_is_created_in_the_database() {
        assertTrue(db.addClient(newUser));
    }

    @Given("I want to delete a current user")
    public void i_want_to_delete_a_current_user() {
        db = UserDaoImpl.newInstance();
    }
    @When("I enter my client card as {long}")
    public void i_enter_my_client_card_as(final long idToDelete) {
        final boolean bool;
        deleteID = idToDelete;
        bool = db.checkIfClientExists(idToDelete);
        if (bool) {
            db.deleteClient(idToDelete);
        } else {
            System.out.printf("User with client ID: %d does not exist in the DB... nothing was deleted \n", idToDelete);
        }
    }
    @Then("The database should delete my record")
    public void the_database_should_delete_my_record() {
        assertFalse(db.checkIfClientExists(deleteID));
    }

    /**
     * UPDATING USER INFORMATION
     */
    @Given("I want to update a user's information")
    public void i_want_to_update_a_user_s_information() {
        db = UserDaoImpl.newInstance();
    }
    @When("I enter my client card as {long} and I enter my new first name as " +
            "{string} and my last name as {string} and my password as {string}")
    public void i_enter_my_client_card_as_and_i_enter_my_new_first_name_as_and_my_last_name_as_and_my_password_as
            (final long idToUpdate, final String firstName,
             final String lastName, final String password) {
        updatedUser = db.getClient(idToUpdate);
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);
        updatedUser.setPassword(password);
    }
    @Then("The database should update my record")
    public void the_database_should_update_my_record() {
        assertTrue(db.updateClient(updatedUser));
    }

}
