Feature: Admin page/ Parents section functionality
  This feature will cover parents section of admin page, especially: creating new parents,
  adding new created parents to database, checking functionality of different buttons on the given page.

  Background: Login as admin and select students section
    Given Login as admin
    When Select parents section
    And Click on 'create' button and open popup

  @TC1.9.4
  Scenario: Check the creation of the new parent after filling in all the required fields with valid data
    Given Fill in name, surname, email fields and click on 'Generate password' button
    And Save values from name, surname, password and email fields
    When Click on 'Save' button
    Then Popup is closed
    And User is displayed in the list
    And Check user is added in the DB

  @TC1.9.7
  Scenario: Check the possibility of creating new parent with an existing "Parent Name" and "Parent Surname"
    Given Fill existed name and surname
    And Fill non-existed email
    And Click on 'Generate password' button
    And Save values from name, surname, password and email fields
    When Click on 'Save' button
    Then Check user is added in the DB
    And User is displayed in the list

  @TC1.9.9
  Scenario: Check uniqueness of parent email
    Given Fill existed email
    When Click on 'Save' button
    Then Check error message of existed email

  @TC1.9.15
  Scenario: Check functionality of 'X' button of the New Parent pop-up
    Given Fill in name, surname, email fields and click on 'Generate password' button
    And Save value from email input field
    And Save value from password input field
    When Click on 'X' button
    Then Popup is closed
    And Click on 'create' button and open popup
    And Check all input fields are empty in create popup
    And Check user is not added in the DB
    And User is not able to login using current credentials

  @TC1.9.17
  Scenario: Check how Parent password is kept in DB
    Given Fill in name, surname, email fields and click on 'Generate password' button
    And Save value from email input field
    And Save value from password input field
    When Click on 'Save' button
    Then Check the parent password is hashed in the DB
