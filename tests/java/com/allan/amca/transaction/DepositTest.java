package com.allan.amca.transaction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DepositTest {

    @Given("I want to deposit money")
    public void i_want_to_deposit_money() {
        System.out.println("Not implemented yet");
    }
    @When("I enter my client card as {long} and the amount to deposit as {double}")
    public void i_enter_my_client_card_as_and_the_amount_to_deposit_as(final Long clientID,
                                                                       final Double amtToDeposit) {
    }
    @Then("Deposit should be successful")
    public void deposit_should_be_successful() {
        System.out.println("Deposit done");
    }
}
