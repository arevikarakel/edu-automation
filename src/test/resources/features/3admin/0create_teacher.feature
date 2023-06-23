Feature: Admin page/ Teacher section functionality
  This feature will cover teacher section of admin page, especially: creating new teachers,
  adding new created teachers to database, checking functionality of different buttons on the given page.

  Background: Login as admin, select teachers section and click on create button
    Given Login as admin
    When Select teachers section
    Then Click on 'create' button and open popup

  @TC1.8.2 @Regression @Smoke
  Scenario: Check functionality of 'X' button in create popup
    Given Fill in name, surname, email fields and click on 'Generate password' button
    And Save value from email input field
    When Click on 'X' button
    And Click on 'create' button and open popup
    Then Check all input fields are empty in create popup
    And Check user is not added in the DB

  @TC1.8.9 @Regression @Smoke
  Scenario: Check uniqueness of the Teacher email
    Given Fill valid name and surname
    And Fill existed email
    And Click on 'Generate password' button
    When Click on 'Save' button
    Then Check error message of existed email

  @TC1.8.11 @Regression @Smoke
  Scenario: Check functionality to create New Teacher using valid credentials
    Given Fill in name, surname, email fields and click on 'Generate password' button
    And Save values from name, surname, password and email fields
    When Click on 'Save' button
    Then Popup is closed
    Then Check user is added in the DB
    And User is displayed in the list

  @TC1.8.12 @Regression @Smoke
  Scenario: Check the possibility of creating new teacher with an existing "Teacher Name" and "Teacher Surname"
    When Fill valid name and surname
    And Fill non-existed email
    And Click on 'Generate password' button
    And Save values from name, surname, password and email fields
    When Click on 'Save' button
    Then Check user is added in the DB
    And User is displayed in the list

  @TC1.8.13 @Regression @Smoke
  Scenario: Check how password is kept in DB
    Given Fill in name, surname, email fields and click on 'Generate password' button
    And Save value from email input field
    And Save value from password input field
    When Click on 'Save' button
    Then Check the teacher password is hashed in the DB
