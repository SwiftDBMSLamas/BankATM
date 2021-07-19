Feature: Login

  Background:
    User navigates to ATM selection page Given
    I am on the ATM login page

    Scenario Outline: Login functionality for bank ATM
      Given User opens the ATM application
      When I enter username as "<username>"
      And I enter password as "<password>"
      Then Login should be successful
      Examples:
        | username   | password |
        | 1234567890 | test   |
        | 1234567891 | test1  |
        | 1234567892 | test2  |

#  Scenario:
#    When I enter username as "1234567890"
#    And I enter password as "test"
#    Then Login should be successful
#
  Scenario:
    When I enter username as "1234567890"
    And I enter password as "fail"
    Then Login should fail