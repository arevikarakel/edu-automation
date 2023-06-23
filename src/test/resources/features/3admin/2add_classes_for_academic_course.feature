Feature: This feature will cover academic course section of admin page, especially adding new classes for courses,
  checking validations and DB integration

  Background: Login as admin, go to academic course section and select one of the existed courses
    Given Login as admin
    When Select courses section
    And Click on the English item in the list
    And Select Classes section in the dashboard
    And Click on 'Add' button and open popup

  @TC3.35.4 @TC3.35.5 @Regression @Smoke
  Scenario Outline: Check functionality of "Class" and "Teachers" drop-down on the "Add Class" pop-up
    When Click on <fieldName> dropdown
    Then Check the list of created <fieldName> in the dropdown list
    When Select the first item from <fieldName> dropdown list
    And Save value of selected <fieldName> item
    Then Check the user choice is displayed in the <fieldName> field
    And Click on 'X' button

    Examples:
      | fieldName     |
      | academicClass |
      | teachers      |

  @TC3.35.6 @Regression @Smoke
  Scenario: Check the adding of a new academic class after selecting a Class and Teacher
    When Select the first item from academicClass dropdown list
    And Select the first item from teachers dropdown list
    And Save value of selected academicClass item
    And Save list size from section
    And Click on 'Save' button
    Then Popup is closed
    And Check created item is displayed in the list
    And Check class for academic course is added in the DB

  @TC3.35.7 @TC3.35.8 @TC3.35.9 @Regression @Smoke
  Scenario: Check validations for 'class' and 'teacher' fields
    When Click on 'Save' button
    Then Check error messages of blank selection fields

  @TC3.35.8 @TC3.35.9 @Regression @Smoke
  Scenario Outline: Check validations for filling one required field 'class' or 'teacher' fields
    When Select the first item from <fieldName> dropdown list
    And Click on 'Save' button
    Then Check error messages of blank selection fields

    Examples:
      | fieldName     |
      | academicClass |
      | teachers      |
