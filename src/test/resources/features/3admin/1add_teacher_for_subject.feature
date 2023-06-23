Feature: This feature will cover subject section of admin page, especially adding new teachers for subjects,
  checking validations and DB integration

  Background: Login as admin, go to subject section and select one of the existed subjects
    Given Login as admin
    When Select subjects section
    And Click on the Languages item in the list
    And Select Teachers section in the dashboard
    And Click on 'Add' button and open popup

  @TC2.41.2 @Regression @Smoke
  Scenario: Check functionality of 'X' icon
    When Select a teacher - Mikhail Lermontov
    And Save linked teachers count for Languages subject from DB and list size from subject section
    And Click on 'X' button
    Then Popup is closed
    When Click on 'Add' button and open popup
    Then Check there is no selected item
    And Check item is not displayed on the list
    And Check Teacher for Languages subject is not added to the DB

  @TC2.41.3 @Regression @Smoke
  Scenario: Check mandatoriness of teachers input filed
    When Click on 'Save' button
    Then Check error messages of blank selections

  @TC2.41.4 @TC2.41.5 @Regression @Smoke
  Scenario: Check search functionality and case-sensitivity of items in the "Teachers" Multi-select drop-list
    Given Click on the search field and open drop-down list
    And Fill name of the item ikh
    Then Check matched items appeared below the Search line
    And Save quantity of matched items result and clear search box
    When Fill name of the item IKH
    And  Check matched items appeared below the Search line
    Then Check result quantity is the same as was in the previous search

  @TC2.41.6 @TC2.41.7 @Regression @Smoke
  Scenario: Check 'Select item' and 'Clear all selected items' functionality in the "Teachers" Multi-select drop-list
    Given Click on the search field and open drop-down list
    When Fill name of the item gay
    And Select teacher
    Then Check selected teacher items are shown with the 'x' icon
    And Click on 'X' button of the whole list
    And Check selected items are deleted from drop-list fragment

  @TC2.41.8 @Regression @Smoke
  Scenario: Check "Remove item" functionality in the "Teachers" Multi-select drop-list
    Given Click on the search field and open drop-down list
    When Select teacher
    Then Check selected teacher items are shown with the 'x' icon
    And Click on 'X' button of the selected item
    And Check selected items are deleted from drop-list fragment

  @TC2.41.9 @Regression @Smoke
  Scenario: Check functionality to add new teacher in the current subject
    Given Click on the search field and open drop-down list
    When Fill name of the item Mikhail
    And Select a teacher - Mikhail Lermontov
    And Save linked teachers count for Languages subject from DB and list size from subject section
    And Click on 'Save' button
    Then Popup is closed
    Then Check item is displayed on the list
    And Check Teacher for Languages subject is added to the DB