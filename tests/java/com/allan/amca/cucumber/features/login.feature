Feature: Login

  Background:
    User is redirected to the ATM main menu given I am on the ATM login page

    Scenario Outline: Login functionality for bank ATM
      Given User opens the ATM application
      When I enter username <username> and I enter PIN as <pin>
      Then Login should be successful
      Examples:
      | username | pin |

    Scenario Outline: Failed login for bank ATM
      Given User opens the ATM application
      When I enter username <username> and I enter the incorrect PIN as <pin>
      Then Login should fail
      Examples:
      | username | pin |