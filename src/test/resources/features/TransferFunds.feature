@All @transfer
Feature: Open New Account and Transfer Funds on Parabank

  @positive
  Scenario: Open New Account and Transfer Funds Successfully
    Given User is logged in to Parabank account
    When User opens a new Savings account
    And User navigates to Transfer Funds page
    And User enters transfer amount "100"
    And User selects source account and the newly created destination account
    And User clicks Transfer button
    Then Transfer should be completed successfully to the new account

  @negative
  Scenario: Failed Transfer Funds with Empty Amount
    Given User is logged in to Parabank account
    When User opens a new Savings account
    And User navigates to Transfer Funds page
    And User enters transfer amount ""
    And User selects source account and the newly created destination account
    And User clicks Transfer button
    Then User should see transfer error message