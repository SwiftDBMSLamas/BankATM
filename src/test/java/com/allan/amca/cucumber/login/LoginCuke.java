package test.java.com.allan.amca.cucumber.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import main.com.allan.amca.login.LoginViewModel;


public class LoginCuke {
    LoginViewModel loginViewModel = LoginViewModel.getInstance();
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
    @Then("LoginViewModel should be successful")
    public void login_should_be_successful() {
//        assertTrue(loginViewModel.loginViewModel(clientC, password));
    }

    @Then("LoginViewModel should fail")
    public void login_should_fail() {
//        assertFalse(loginViewModel.loginViewModel(clientC, password));
    }
}
