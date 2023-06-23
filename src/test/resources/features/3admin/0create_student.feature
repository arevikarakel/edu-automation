Feature: Admin page/ Students section functionality
  This feature will cover students section of admin page, especially: creating new students,
  adding new created students to database, checking functionality of different buttons on the given page.

  Background: Login as admin and select students section
    Given Login as admin
    When Select students section
    Then Click on 'create' button and open popup

  @TC1.10.4 @Regression @Smoke
  Scenario: Check functionality of 'X' button in create popup
    Given Fill in all required fields and click on 'Generate password' button in students section create popup
    And Save value from email input field
    When Click on 'X' button
    And Click on 'create' button and open popup
    Then Check all input fields are empty in students section create popup
    And Check user is not added in the DB

  @TC1.10.5 @Regression @Smoke
  Scenario: Check the creation of the new student after filling in all the required fields with valid data
    Given Fill in all required fields and click on 'Generate password' button in students section create popup
    And Save values from name, surname, password and email fields
    When Click on 'Save' button
    Then Popup is closed
    And Check user is added in the DB
    And User is displayed in the list

  @TC1.10.7 @Regression @Smoke
  Scenario: Check the possibility of creating new student with an existing "Student Name" and "Student Surname"
    When Fill in existed name, surname, all other required fields besides email on the student section create popup
    And Fill non-existed email
    And Save values from name, surname, password and email fields
    And Click on 'Generate password' button
    When Click on 'Save' button
    Then User is displayed in the list
    And Check user is added in the DB

  @TC1.10.8 @Regression @Smoke
  Scenario: Check uniqueness of student email
    When Fill in existed name, surname, all other required fields besides email on the student section create popup
    And Fill existed email
    When Click on 'Save' button
    Then Check error message of existed email

  @TC1.10.10 @Regression @Smoke
  Scenario: Check functionality of "Birth date" field
    Given Click on 'Birth Date' field
    Then Check 'Birth Date' calendar is opened
    And User is able to select dates between the given interval provided by documentation

  @TC1.10.11 @TC1.10.16 @TC1.10.17 @TC1.10.18 @Regression @Smoke
  Scenario Outline: Check functionality of select fields drop-down on the New Student popup
    When Click on <field> field having drop-down list and select <value> from the list
    Then The user choice is displayed in the <field> field

    Examples:
      | field         | value           |
      | academicClass | 1A              |
      | bloodGroup    | B-              |
      | parent        | Naira Bogdanyan |
      | gender        | Female          |

  @TC1.10.19 @Regression
  Scenario: Check the possibility of creating new student without selecting "Linked parent" and "Linked Class" optional fields
    When Fill in all required fields and click on 'Generate password' button in students section create popup
    Then Check that 'Linked Parent' and 'Linked Class' fields are not selected
    And Save values from name, surname, password and email fields
    And Click on 'Save' button
    Then User is displayed in the list
    And Check user is added in the DB

  @TC1.10.21 @Regression @Smoke
  Scenario: Check how student password is kept in DB
    And Fill in all required fields and click on 'Generate password' button in students section create popup
    And Click on 'Generate password' button
    And Save value from email input field
    And Save value from password input field
    And Click on 'Save' button
    Then Check the student password is hashed in the DB
