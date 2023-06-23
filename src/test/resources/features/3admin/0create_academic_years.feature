Feature: This feature will cover academic years section of admin page, especially creating new academic years,
  checking validations according the given requirements and database integration.

  Background: Login as admin and select years section
    Given Login as admin
    When Select years section
    And Click on 'create' button and open popup

  @TC2.13.2 @Regression @Smoke
  Scenario: Check functionality of 'X' button
    When Fill 02 Dec 2024 date in start date field
    When Fill 12 Nov 2025 date in end date field
    And Save date values
    And Click on 'X' button
    Then Popup is closed
    When Click on 'create' button and open popup
    Then Check date fields are empty in create popup
    And New academic year is not added to the DB

  @TC2.13.4 @Regression
  Scenario: Check validations for inputted dates being in the future
    Then Check that you can't select start date before today - the day of selection
    When Click on 'Save' button
    And Check that you can't select end date before today - the day of selection

  @TC2.13.5 @Regression @Smoke
  Scenario: Check validation for Start Date > End Date
    When Fill 10 Dec 2025 date in start date field
    When Fill 10 Dec 2024 date in end date field
    And Click on 'Save' button
    Then Check error message of wrong selected dates

  @TC2.13.6 @Regression @Smoke
  Scenario: Check validation for maximum 10 year length of academic year not counting months
    When Fill 10 Nov 2024 date in start date field
    And Save start date values
    Then Check that 2043 year is greater from start date by more than ten years
    And Check that 2043 year doesn't exist in the list of selection

  @TC2.13.7 @Regression @Smoke
  Scenario: Check validation for minimum 30 days length of academic year
    When Fill 10 Nov 2023 date in start date field
    When Fill 12 Nov 2023 date in end date field
    And Click on 'Save' button
    Then Check error message for filling less than 30 days

  @TC2.13.8 @Regression @Smoke
  Scenario: Check functionality to create New Academic year with valid data
    When Fill 12 Dec 2026 date in start date field
    When Fill 01 Nov 2027 date in end date field
    And Save date values
    And Click on 'Save' button
    Then Popup is closed
    Then Check dates are displayed in the list
    And New academic year is added to the DB