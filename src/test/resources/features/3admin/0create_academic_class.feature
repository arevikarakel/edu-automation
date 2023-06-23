Feature: This feature will cover academic class section of admin page, especially creating new classes,
  adding courses to the created classes, checking validations according the given requirements and database integration.

  Background: Login as admin and select classes section
    Given Login as admin
    When Select classes section
    And Click on 'create' button and open popup

  @TC1.11.5 @Regression @Smoke
  Scenario: Check uniqueness of "Academic Class Number" field
    When Fill existed academic class
    And Click on 'Save' button
    Then Check error message of existed academic class

  @Regression @Smoke
  Scenario: Check possibility of creating new Academic class with valid data
    When Fill in unique academic class
    And Save academic class value
    And Click on 'Save' button
    Then Academic class is created and displayed in the list
    And Academic class is added to the DB
