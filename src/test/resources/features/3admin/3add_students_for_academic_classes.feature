Feature: This feature will cover academic class page Students section,
  especially adding new students for academic classes, checking validations and DB integration

  Background: Login as admin, go to academic class section, select courses section
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Students section in the dashboard
    And Click on 'Add' button and open popup

  @TC4.37.4 @Regression
  Scenario: Check functionality of 'X' button on the "Add Students"  pop-up
    Given Click on the search field and open drop-down list
    When Select student from dropdown list
    And Save linked students count for 1A class from DB and list size from section
    And Click on 'X' button
    Then Popup is closed
    When Click on 'Add' button and open popup
    Then Check there is no selected item
    And Check item is not displayed on the list
    And Check student for 1A class is not added in the DB

  @TC4.37.5 @TC4.37.6 @Regression
  Scenario: Check functionality of "Students" Multi-selected drop-list
    Given Click on the search field and open drop-down list
    Then Check the search line placeholder for students
    And Check multi-select drop-down list is opened for student
    When Fill name of the item ik
    Then Check matched items appeared below the Search line
    And Save quantity of matched items result and clear search box
    Given Click on the search field and open drop-down list
    When Fill name of the item IK
    Then Check matched items appeared below the Search line
    Then Check result quantity is the same as was in the previous search

  @TC3.37.7 @Regression @Smoke
  Scenario: Check that one student can be involved/added only in one class
    When Click on 'X' button
    And Save the first item value from the list
    When Select classes section
    And Click on the second item in the list
    And Select Students section in the dashboard
    And Click on 'Add' button and open popup
    And Click on the search field and open drop-down list
    When Fill name of the item that was saved before
    Then Check matched items don't appear below the Search line

  @TC3.37.8 @Regression @Smoke
  Scenario: Check functionality of 'remove' students from "Add Students" Multi-selected drop-list(one by one)
    Given Click on the search field and open drop-down list
    When Select student from dropdown list
    Then Check selected student items are shown with the 'x' icon
    And Click on 'X' button of the selected item
    And Check selected items are deleted from drop-list fragment

  @TC3.37.9 @Regression @Smoke
  Scenario: Check functionality of 'X' button (clear all selected items)
    Given Click on the search field and open drop-down list
    When Select student from dropdown list
    And Click on 'X' button of the whole list
    And Check selected items are deleted from drop-list fragment

  @TC3.38.10 @Regression @Smoke
  Scenario: Check functionality of adding students in Academic Class
    Given Click on the search field and open drop-down list
    When Select student from dropdown list
    And Save linked students count for 1A class from DB and list size from section
    And Click on 'Save' button
    Then Popup is closed
    And Check item is displayed on the list
    And Check student for 1A class is added in the DB

  @TC3.37.11 @Regression @Smoke
  Scenario: Check functionality of add students in Academic Class without any selection
    Then Popup is opened
    When Click on 'Save' button
    Then Check error messages of blank selections
