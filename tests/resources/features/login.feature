Feature: Login

  Background:
    User navigates to ATM selection page Given
    I am on the ATM login page

    Scenario Outline: Login functionality for bank ATM
      Given User opens the ATM application
      When I enter username "<username>" and I enter password as "<password>"
      Then Login should be successful
      Examples:
        | username         | password    |
        | 4519011123012324 | password1   |
        | 4519011123012325 | password2   |
        | 4519011123012326 | password3   |

#  Scenario:
#    When I enter username as "1234567890"
#    And I enter password as "test"
#    Then Login should be successful
#
  Scenario:
    When I enter username "1234567890" and I enter password as "fail"
    Then Login should fail