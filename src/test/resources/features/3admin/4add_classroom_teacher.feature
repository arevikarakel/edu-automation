Feature: This feature will cover academic class page Classroom Teacher section,
  especially adding and editing classroom teachers for academic classes, checking validations and DB integration

  Background: Login as admin, navigate to classes section and select classroom teacher bar
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Classroom Teacher section in the dashboard

  @TC4.39.4 @Regression @Smoke
  Scenario: Check functionality of 'X' button on the "Add Classroom teacher" pop-up
    And Click on Add Classroom Teacher button
    And Click on classroomTeacher dropdown
    When Select the first item from classroomTeacher dropdown list
    And Click on 'X' button
    Then Check message for not having classroom teacher
    And Check classroom teacher for 1A class is not added in the DB

  @TC4.39.7 @Regression @Smoke
  Scenario: Check validation of "Classroom teacher" dropdown list
    When Select Teachers section in the dashboard
    And Save list options
    And Select Classroom Teacher section in the dashboard
    When Click on Add Classroom Teacher button
    And Click on classroomTeacher dropdown
    Then Check list items are the same as in select dropdown

  @TC4.39.5 @TC4.39.6 @Regression @Smoke
  Scenario: Check functionality of adding classroom teacher after selecting a teacher from dropdown list
    When Click on Add Classroom Teacher button
    And Click on 'Save' button
    Then Check error messages of blank selection fields
    And Click on classroomTeacher dropdown
    And Select the first item from classroomTeacher dropdown list
    And Save value of selected classroomTeacher item
    And Click on 'Save' button
    Then Popup is closed
    And Check classroomTeacher's name is displayed in the list
    And Check 'Edit' button appears in the screen
    And Check classroom teacher for 1A class is added in the DB

  @TC4.39.8 @Regression @Smoke
  Scenario: Check UI of "Edit Classroom Teacher' popup in the "Classroom teacher" section
    When Click on 'Edit' button
    Then Popup is opened
    And Check all elements are present in 'classroom teacher' popup

  @TC4.39.9 @Regression @Smoke
  Scenario: Check functionality of editing "Classroom teacher"
    When Save existed classroom teacher name and surname from the list
    And Click on 'Edit' button
    And Click on classroomTeacher dropdown
    And Select the first item from classroomTeacher dropdown list
    And Save value of selected classroomTeacher item
    Then Check classroom teacher has been changed
