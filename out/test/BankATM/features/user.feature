Feature: User Creation

  Background:
    A new user is created Given
    I am on the registration page

  Scenario Outline: Successful registration functionality for bank ATM
    Given User opens the ATM application
    And User opens create account
    When I enter client ID as "<clientID>" and I enter password as "<password>"
    Then Registration should be successful
    Examples:
      | clientID | password |
      | 4519011234567899 | testing1 |
      | 4519014321876599 | testing2 |

  Scenario Outline: Fail registration for bank ATM
    Given User opens the ATM application
    And User opens create account
    When I enter an invalid client ID as "<clientID>"
    And I enter an invalid password as "<password>"
    Then Registration should fail
    Examples:
      | clientID | password |
      | 4519011234567899 | testing2 |
      | 4519014321876599 | testing1 |

