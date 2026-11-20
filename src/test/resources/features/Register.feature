@All @register
Feature: User Registration on Parabank

  @positive
  Scenario: Success Register
    Given User is on the Parabank registration page
    When User fills registration form
    And User clicks Register button
    Then User redirected to welcome page

  @negative
  Scenario: Failed Register - Mismatch Password
    Given User is on the Parabank registration page
    When User fills registration form with mismatched password
    And User clicks Register button
    Then User should see error message