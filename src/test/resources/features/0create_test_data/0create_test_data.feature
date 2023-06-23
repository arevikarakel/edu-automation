Feature: This feature will create data for next test cases

  Background: Login as admin and select section
    Given Login as admin

    Scenario Outline: Select section and create data
      When Select <sectionName> section
      And Click on 'create' button and open popup
      When Fill valid name and surname
      And Fill non-existed email
      And Click on 'Generate password' button
      When Click on 'Save' button
      When Select <sectionName> section
      And Click on 'create' button and open popup
      When Fill valid name and surname
      And Fill non-existed email
      And Click on 'Generate password' button
      When Click on 'Save' button

      Examples:
      |sectionName|
      | teachers  |
      | parents   |

  Scenario: Create new students
    When Select students section
    Then Click on 'create' button and open popup
    Given Fill in all required fields and click on 'Generate password' button in students section create popup
    When Click on 'Save' button
    Then Click on 'create' button and open popup
    Given Fill in all required fields and click on 'Generate password' button in students section create popup
    When Click on 'Save' button
    Then Click on 'create' button and open popup
    Given Fill in all required fields and click on 'Generate password' button in students section create popup
    When Click on 'Save' button

  Scenario: Create new academic classes for the future tests
    When Select classes section
    And Click on 'create' button and open popup
    When Fill in unique academic class
    And Click on 'Save' button
    And Click on 'create' button and open popup
    When Fill in unique academic class
    And Click on 'Save' button
    And Click on 'create' button and open popup
    When Fill in unique academic class
    And Click on 'Save' button
    And Click on 'create' button and open popup
    When Fill in unique academic class
    And Click on 'Save' button

  Scenario: Create new academic courses for the future tests
    When Select courses section
    Then Click on 'create' button and open popup
    Given Fill academic course name
    When Select the first item from subject dropdown list
    Then Click on 'Save' button
    Then Click on 'create' button and open popup
    Given Fill academic course name
    When Select the first item from subject dropdown list
    Then Click on 'Save' button
    Then Click on 'create' button and open popup
    Given Fill academic course name
    When Select the first item from subject dropdown list
    Then Click on 'Save' button
    Then Click on 'create' button and open popup
    Given Fill academic course name
    When Select the first item from subject dropdown list
    Then Click on 'Save' button

  Scenario: Adding teachers to different Academic Courses
    When Select courses section
    And Click on the second item in the list
    And Select Teachers section in the dashboard
    And Click on 'Add' button and open popup
    Given Click on the search field and open drop-down list
    When Select teacher from dropdown list
    And Click on 'Save' button
    When Select courses section
    And Click on the third item in the list
    And Select Teachers section in the dashboard
    And Click on 'Add' button and open popup
    Given Click on the search field and open drop-down list
    When Select teacher from dropdown list
    And Click on 'Save' button

    Scenario: Add teacher for academic courses
      When Add teacher for academic courses