Feature: Withdrawal

  Background: User withdraws money given they are at the ATM machine

    Scenario: Client withdraws money from their account from the ATM machine
      Given I want to withdraw money
      When I enter my client card as 4519011123012000 and the amount to withdraw as 200.0
      Then Withdraw should be successful

    Scenario: Client enters invalid amount to withdraw from the ATM machine
      Given I want to withdraw money
      When I enter my client card as 4519011123012000 and the amount to withdraw as -1.0
      Then Withdraw should fail

    Scenario: Client enters an amount that is more than their current account balance
      Given I want to withdraw money
      When I enter my client card as 4519011123012000 and the amount to withdraw as 400.0
      Then Withdraw should fail