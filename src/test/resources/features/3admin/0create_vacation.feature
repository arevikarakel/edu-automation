Feature: This feature will cover vacation section of admin page, especially creating new vacation,
  checking validations according the given requirements and database integration.

  Background: Login as admin and select vacation section
    Given Login as admin
    When Select vacations section
    And Click on 'create' button and open popup

  @TC2.14.4 @Regression @Smoke
  Scenario: Check functionality of 'X' button
    When Fill 12 Dec 2024 date in start date field
    When Fill 12 Nov 2025 date in end date field
    And Save date values
    And Click on 'X' button
    Then Popup is closed
    When Click on 'create' button and open popup
    Then Check date fields are empty in create popup
    And New Vacation is not added to the DB

  @TC2.14.5 @Regression @Smoke
  Scenario: Check functionality to create New Vacation with valid data
    When Fill 01 Dec 2026 date in start date field
    When Fill 26 Nov 2027 date in end date field
    And Save date values
    And Click on 'Save' button
    Then Popup is closed
    Then Check vacation is displayed in the list
    And New Vacation is added to the DB

  @TC2.14.7 @Regression
  Scenario: Check validations for inputted dates being in the future
    Then Check that you can't select start date before today - the day of selection

  @TC2.14.8 @Regression @Smoke
  Scenario: Check validation for Start Date > End Date
    When Fill 10 Dec 2025 date in start date field
    When Fill 10 Dec 2024 date in end date field
    And Click on 'Save' button
    Then Check error message of wrong selected dates
