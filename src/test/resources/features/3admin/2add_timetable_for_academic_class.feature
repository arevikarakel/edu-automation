Feature: This feature will cover timetable section of academic classes,
  checking validations and DB integration

  Background: Login as admin, navigate to classes section and select timetable bar
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Timetable section in the dashboard
    And Click on Create Timetable button in timetable page
    When Save list size for 1A class, DB count and day of week - Monday
    And Click on '+' button

  @TC4.38.6 @Regression @Smoke
  Scenario: Check functionality of 'X' button on the "Add Lesson" pop-up
    Given Select the first course from drop-down list
    When Click on 'X' button
    And Click on '+' button
    And Check selection is empty
    When Click on 'X' button
    Then Check course is not displayed in timetable
    And Check course for timetable is not added in the DB
    When Select classes section
    Then Check alert is present
    And Accept alert and leave the page

  @TC4.38.7 @TC4.38.8 @Regression @Smoke
  Scenario: Check adding a new course to the timetable
    When Click on 'Save' button in Add Lesson popup
    Then Check error messages of blank selections
    Given Select the first course from drop-down list
    When Save selected course name
    And Click on 'Save' button in Add Lesson popup
    Then Check course is added at the end of the timetable list
    When Select classes section
    And Accept alert and leave the page

  @TC4.38.9 @Regression @Smoke
  Scenario: Check validations of "Course" dropdown on the "Add Lesson" pop-up
    When Click on 'Course' drop-down
    Then Check only courses which are added for 1A class is displayed and can be selected
    And Click on 'X' button
    When Select classes section
    And Accept alert and leave the page

  @TC4.38.10 @Regression
  Scenario: Check the order of added courses
    Given Select the first course from drop-down list
    When Save selected course name
    And Click on 'Save' button in Add Lesson popup
    Then Check course is added at the end of the timetable list
    Given Click on '+' button
    When Select the first course from drop-down list
    And Save selected course name
    And Click on 'Save' button in Add Lesson popup
    Then Check course is added at the end of the timetable list
    When Select classes section
    And Accept alert and leave the page

  @TC4.38.12 @TC4.38.14 @Regression @Smoke
  Scenario: Check Timetable creation without course or dates selection
    Given Select the first course from drop-down list
    When Click on 'Save' button in Add Lesson popup
    And Click on Create button in timetable page
    Then Check error message for wrong dates in timetable
    When Refresh the page
    When Fill 31 Dec 2022 date in start date field
    And Fill 10 Oct 2023 date in end date field
    And Click on Create button in timetable page
    Then Check error message for not selecting courses for timetable
    When Select classes section
    And Accept alert and leave the page

  @TC4.38.13 @Regression @Smoke
  Scenario: Check validations of "Stat date" and "End date"
    Given Select the first course from drop-down list
    And Click on 'Save' button in Add Lesson popup
    Then Check that you can't select start date before today - the day of selection
    And Check that you can't select end date before today - the day of selection
    When Fill today's date in start and end date fields
    And Click on Create button in timetable page
    Then Check error message for wrong dates in timetable
    And Check end days can't be more than 12 months after today's date
    When Select classes section
    And Accept alert and leave the page

  @TC4.38.16 @Regression @Smoke
    Scenario: Check the functionality of deleting the selected course from the timetable
    Given Select the first course from drop-down list
    And Click on 'Save' button in Add Lesson popup
    And Save list size for 1A class, DB count and day of week - Monday
    And Click on 'X' button of the selected course
    Then Click on cancel button in popup
    And Check academic course is still displayed in the timetable list
    When Click on 'X' button of the selected course
    And Click on delete button in popup
    And Check academic course is not displayed in the timetable list
    When Select classes section
    And Accept alert and leave the page
