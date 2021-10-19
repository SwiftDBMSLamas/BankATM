Feature: Withdrawal

  Background: User withdraws money given they are at the ATM machine

  Scenario:
    Given I want to withdraw money
    When I enter my client card as 4519011123012380 and the amount to withdraw as 100.0
    Then Withdraw should be successful
