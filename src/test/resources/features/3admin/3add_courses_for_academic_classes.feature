Feature: This feature will cover academic class page Courses section,
  especially adding new courses for academic classes, checking validations and DB integration

  Background: Login as admin, go to academic class section, select courses section
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Courses section in the dashboard
    And Click on 'Add' button and open popup

  @TC3.36.5 @TC3.36.6 @TC3.36.7 @Regression @Smoke
  Scenario: Check functionality of "Course" and "Teachers" drop-down on the "New Course" pop-up
    When Save list size from section
    And Click on academicCourse dropdown
    Then Check the list of created academicCourse in the dropdown list
    When Select the first item from academicCourse dropdown list
    And Save value of selected academicCourse item
    Then Check the user choice is displayed in the academicCourse field
    When Click on teachers dropdown
    Then Check the list of created teachers in the dropdown list
    When Select the first item from teachers dropdown list
    And Save value of selected teachers item
    Then Check the user choice is displayed in the teachers field
    And Click on 'Save' button
    And Check created item is displayed in the list

  @TC3.36.8 @TC3.36.10 @Regression @Smoke
  Scenario: Check the adding of new course without selecting a academic course and/or teacher
    When Click on 'Save' button
    Then Check error messages of blank selection fields
    And Click on academicCourse dropdown
    When Select the first item from academicCourse dropdown list
    And Click on 'Save' button
    Then Check error messages of blank selection fields