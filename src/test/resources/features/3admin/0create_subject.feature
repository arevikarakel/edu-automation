Feature: Admin page/ Subjects section functionality
  This feature will cover subjects section of admin page, especially: creating new subjects,
  adding new created subjects to database, adding teacher for subject and checking functionality of different buttons on the given page.

  Background:
    Given Login as admin
    When Select subjects section
    Then Click on 'create' button and open popup

  @TC2.12.4 @Regression
  Scenario: Check functionality of 'X' button on the "New Subjects" pop-up
    Given Fill subject name
    And Save value from subject name field
    When Click on 'X' button
    Then Popup is closed
    And Click on 'create' button and open popup
    And Check all fields and drop down list are empty in subjects section create popup
    And Check blank input fields error messages are not displayed
    And Check subject is not added in the DB

  @TC2.12.5 @Regression @Smoke
  Scenario: Check the creation of the new subject after inputted valid data
  in the mandatory field "Subject Name" and the optional field "Teachers".
    Given Fill subject name
    And Click on the search field and open drop-down list
    And Select teacher
    And Save value from subject name field
    When Click on 'Save' button
    Then Popup is closed
    And Check subject is displayed in the list
    And Check subject is added in the DB

  @TC2.12.6 @Regression @Smoke
  Scenario: Check the creation of the new subject after inputting valid data in the mandatory
  field "Subject Name" and skip the optional field "Teachers".
    Given Fill subject name
    And Save value from subject name field
    When Click on 'Save' button
    Then Popup is closed
    And Check subject is displayed in the list
    And Check subject is added in the DB

  @TC2.12.8 @Regression
  Scenario: Check the uniques of the subject name
    Given Fill existed subject name
    When Click on 'Save' button
    Then Check error message of existed subject name

  @TC2.12.10 @Regression
  Scenario: Check functionality of the "Teachers" Multi-select drop-list
    Given Click on the search field and open drop-down list
    Then Check the search line placeholder for teachers
    And Check there is no selected item

  @TC2.12.11 @TC2.12.12 @TC2.12.13 @TC2.12.14 @TC2.12.15 @Regression @Smoke
  Scenario: Check "Clear all selected item" functionality in the  "Teachers" Multi-select drop-list
    Given Click on the search field and open drop-down list
    And Fill name of the item gay
    Then Check matched items appeared below the Search line
    When Select a teacher - Gayane Hovhannisyan
    And Select a teacher - Gayush Poghosyan
    Then Check selected teacher items are shown with the 'x' icon
    And Click on 'X' button of the selected item
    And Click on 'X' button of the whole list
    And Check selected items are deleted from drop-list fragment
