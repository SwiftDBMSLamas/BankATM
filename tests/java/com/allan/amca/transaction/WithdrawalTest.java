package com.allan.amca.transaction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WithdrawalTest {

    @Given("I want to withdraw money")
    public void i_want_to_deposit_money() {
        System.out.println("Not implemented yet");
    }
    @When("I enter my client card as {long} and the amount to withdraw as {double}")
    public void i_enter_my_client_card_as_and_the_amount_to_deposit_as(final Long clientID,
                                                                       final Double amtToDeposit) {
        Transactional t = new Withdrawal(clientID);
        t.performTransaction(TransactionType.WITHDRAWAL, clientID, amtToDeposit);
    }
    @Then("Withdraw should be successful")
    public void deposit_should_be_successful() {
        System.out.println("Withdraw done");
    }

}
