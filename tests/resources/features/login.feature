Feature: Login

  Background:
    User navigates to ATM selection page Given
    I am on the ATM login page

  Scenario Outline: Login functionality for bank ATM
    Given User opens the ATM application
    When I enter username <username> and I enter password as "<password>"
    Then Login should be successful
    Examples:
      | username         | password      |
      | 4519011123012346 | password134   |
      | 4519011123012356 | password134   |
      | 4519011123012366 | password134   |

  Scenario:
    When I enter username 4519011123012350 and I enter password as "password100"
    Then Login should fail