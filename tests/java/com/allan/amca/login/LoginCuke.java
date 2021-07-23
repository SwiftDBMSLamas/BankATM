package com.allan.amca.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;


public class LoginCuke {
    Login login = Login.getInstance();
    String clientC, password;

    @Given("com.allan.amca.Login.User opens the ATM application")
    public void user_opens_the_atm_application() {
        System.err.println("Not implemented, yet");
    }
    @When("I enter username {string} and I enter password as {string}")
    public void i_enter_username_and_password_as(final String clientCard, final String password) {
        clientC = clientCard;
        this.password = password;
    }
    @Then("com.allan.amca.Login should be successful")
    public void login_should_be_successful() {
        assertTrue(login.login(clientC, password));
    }

    @Then("com.allan.amca.Login should fail")
    public void login_should_fail() {
        assertFalse(login.login(clientC, password));
    }
}
