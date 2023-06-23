Feature: This feature will cover creating and editing timetable section of academic classes,
  checking validations and DB integration

  Background: Login as admin, navigate to classes section and select timetable bar
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Timetable section in the dashboard

  @TC4.38.11 @Regression @Smoke
  Scenario: Check functionality of 'Create Timetable' button on the top of the screen
    When Click on Create Timetable button in timetable page
    And Save list size for 1A class, DB count and day of week - Monday
    And Click on '+' button
    Given Select the first course from drop-down list
    And Click on 'Save' button in Add Lesson popup
    When Fill 31 Dec 2022 date in start date field
    And Fill 10 Oct 2023 date in end date field
    And Click on Create button in timetable page
    Then Popup is opened
    Then Check success message for creating timetable

  @TC4.38.17 @Regression @Smoke
    Scenario: Check the functionality of editing an already created timetable
    When Click on Edit button in timetable page
    And Click on '+' button
    Given Select the first course from drop-down list
    And Click on 'Save' button in Add Lesson popup
    When Fill 25 Dec 2022 date in start date field
    And Fill 13 Oct 2023 date in end date field
    And Save list size for 1A class, DB count and day of week - Monday
    And Click on 'X' button of the selected course
    Then Click on cancel button in popup
    And Check academic course is still displayed in the timetable list
    When Click on 'X' button of the selected course
    And Click on delete button in popup
    And Check academic course is not displayed in the timetable list
    And Click on Save button in timetable page

    Scenario: Delete timetable from DB
      Then Delete timetable for 1A class from DB
