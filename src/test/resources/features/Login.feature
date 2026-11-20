@All @login
Feature: User Login and Logout on Parabank

  @positive
  Scenario: Success Login and Logout
    Given User is on the Parabank page
    When User fills Login forms
    And User clicks Login button
    Then User redirected to Accounts Overview
    When User clicks Logout
    Then User back to Parabank page

  @negative
  Scenario: Failed to Login - Wrong Password
    Given User is on the Parabank page
    When User fills Login forms with wrong password
    And User clicks Login button
    Then User should see login error message