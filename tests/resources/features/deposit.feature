Feature: Deposit

  Background: User deposits money given they are at the ATM machine

    Scenario:
      Given I want to deposit money
      When I enter my client card as 4519011123012380 and the amount to deposit as 1000.0
      Then Deposit should be successful