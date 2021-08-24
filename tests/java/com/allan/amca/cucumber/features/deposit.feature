Feature: Deposit

  Background: Client deposits money given they are at the ATM machine

    Scenario: Client deposits money into their account at the ATM machine
      Given I want to deposit money
      When I enter my client card as 4519011123012000 and the amount to deposit as 200.0
      Then Deposit should be successful

    Scenario: Client deposits invalid amount into their account at the ATM machine
      Given I want to deposit money
      When I enter my client card as 4519011123012000 and the amount to deposit as -1.0
      Then Deposit should fail