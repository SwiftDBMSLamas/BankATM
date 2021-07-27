package com.allan.amca.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;


public class LoginCuke {
    Login login = Login.getInstance();
    Long clientC;
    String password;

    @Given("User opens the ATM application")
    public void user_opens_the_atm_application() {
        System.err.println("Not implemented, yet");
    }
    @When("I enter username {long} and I enter password as {string}")
    public void i_enter_username_and_password_as(final Long clientCard, final String password) {
        clientC = clientCard;
        this.password = password;
    }
    @Then("Login should be successful")
    public void login_should_be_successful() {
        assertTrue(login.login(clientC, password));
    }

    @Then("Login should fail")
    public void login_should_fail() {
        assertFalse(login.login(clientC, password));
    }
}
