Feature: UI of pages
  This feature checks all elements that must be present on certain web pages

  @TC1.7.1 @Regression
  Scenario: Check UI of Login page
    Then Check all elements are present in login page

  @TC1.7.7 @Regression
  Scenario: Check UI of super admin page
    Given Login as super admin
    Then Check all elements are present in super admin page

  @TC1.6.1 @Regression
  Scenario: Check UI of create popup in super admin dashboard
    Given Login as super admin
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup

  @TC1.8.1 @Regression
  Scenario: Check UI of 'create' popup in admin dashboard/ teacher section
    Given Login as admin
    When Select teachers section
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup

  @TC1.9.1
  Scenario: Check UI of Admin Page Parents section
    Given Login as admin
    When Select parents section
    Then Check all elements are present on the chosen section

  @TC1.9.2 @TC1.9.3
  Scenario: Check functionality of "Create" button in admin dashboard/ parents section
    Given Login as admin
    When Select parents section
    And Click on 'create' button and open popup
    Then Popup is opened
    Then Check all fields are present in create popup

  @TC1.10.1 @Regression
  Scenario: Check UI of Admin page Students section
    Given Login as admin
    When Select students section
    Then Check all elements are present on the chosen section

  @TC1.10.2 @TC1.10.3 @Regression
  Scenario: Check UI of 'create' button in admin dashboard/ students section
    Given Login as admin
    When Select students section
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup - students section

  @TC1.7.8 @Regression
  Scenario: Check UI of admin page
    Given Login as admin
    Then Check all elements are present on admin page

  @TC1.11.1 @TC1.11.2 @1.11.3 @Regression
  Scenario: Check UI of "Academic Classes" section and "Create" button on the "Academic Classes" section
    Given Login as admin
    When Select classes section
    Then Check all elements are present on the chosen section
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup - academic classes section

  @TC2.13.1 @Regression
  Scenario: Check UI of 'Create' button in 'Academic Year' section
    Given Login as admin
    When Select years section
    And Click on 'create' button and open popup
    Then Check all fields are present in create popup years section

  @TC2.14.1 @TC2.14.2 @TC2.14.3 @Regression
  Scenario: Check UI of 'Vacations' section and UI of 'Create' button in the given section
    Given Login as admin
    When Select vacations section
    Then Check all elements are present on the chosen section
    When Click on 'create' button and open popup
    Then Popup is opened
    Then Check all fields are present in create popup years section

  @TC2.12.1 @TC2.12.2 @TC2.12.3 @Regression
  Scenario: Check UI of admin dashboard / subjects section and create subject popup
    Given Login as admin
    When Select subjects section
    Then Check all elements are present on the chosen section
    And Click on 'create' button and open popup
    Then Popup is opened
    And Check all elements are present in create popup - subjects section

  @TC2.41.1 @Regression
  Scenario: Check UI of 'Add' popup in Subjects dashboard after selecting subject
    Given Login as admin
    When Select subjects section
    And Click on the Languages item in the list
    And Select Teachers section in the dashboard
    And Click on 'Add' button and open popup
    Then Check all elements are present in subject dashboard teachers section

  @TC3.21.1 @TC3.21.2 @TC3.21.3 @Regression @Smoke
  Scenario: Check UI of Admin Page Academic Course section and create popup
    Given Login as admin
    And Select courses section
    Then Check all elements are present on the chosen section
    And Click on 'create' button and open popup
    Then Popup is opened
    Then Check all fields are present in create popup - academic course section

  @TC3.21.12 @TC3.34.1 @TC3.35.1 @Regression @Smoke
  Scenario: Check UI of "Classes" section in the right navigation menu of academic course page
    Given Login as admin
    When Select courses section
    And Click on the English item in the list
    Then Check all elements are present in chosen section of academic course

  @TC3.35.2 @TC3.35.3 @Regression @Smoke
  Scenario: Check UI of "Add Class" pop-up elements in Academic course Classes section
    Given Login as admin
    When Select courses section
    And Click on the English item in the list
    And Select Classes section in the dashboard
    And Click on 'Add' button and open popup
    Then Popup is opened
    And Check all elements are present in 'Add Class' popup Classes section

  @TC3.34.2 @TC3.34.3 @Regression @Smoke
  Scenario: Check UI of "Add Class" pop-up elements in Academic course Classes section
    Given Login as admin
    When Select courses section
    And Click on the English item in the list
    And Select Teachers section in the dashboard
    And Click on 'Add' button and open popup
    Then Popup is opened
    And Check all elements are present in 'Add Teacher' popup Teachers section

  @TC4.38.1 @TC4.38.2 @TC4.38.3 @TC4.38.4 @TC4.38.5 @Regression
  Scenario: Check UI of "Timetable" section in the right navigation menu of academic class page
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Timetable section in the dashboard
    Then Check all elements are present in timetable section
    When Click on Create Timetable button in timetable page
    Then Check all elements are present in opened timetable page
    When Save day of week - Monday
    When Click on '+' button
    Then  Check popup for chosen day of week is opened
    And Check all elements are present in 'Add lesson' popup
    Then Click on 'X' button
    And Check popup for chosen day of week is closed
    When Select classes section
    Then Check alert is present
    And Accept alert and leave the page

  @TC3.36.1 @TC3.36.2 @TC3.36.3 @TC3.36.4 @Regression @Smoke
  Scenario: Check UI of "Courses" section and UI of "New Course" popup in Academic Classes page
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Courses section in the dashboard
    Then Check all elements are present in chosen subsection of academic class
    When Click on 'Add' button and open popup
    Then Popup is opened
    And Check all elements are present in 'New Course' popup Classes section

  @TC4.39.1 @TC4.39.2 @TC4.39.3 @Regression @Smoke
  Scenario: Check UI of "Classroom teacher" subsection and "Add Classroom Teacher" popup of academic class page
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Classroom Teacher section in the dashboard
    Then Check all elements are present in classroom teacher subsection
    And Click on Add Classroom Teacher button
    Then Popup is opened
    And Check all elements are present in 'classroom teacher' popup

  @TC4.37.1 @TC4.37.2 @TC4.37.3 @Regression @Smoke
  Scenario: Check UI of "Students" subsection and UI of "New Student" popup in Academic Classes page
    Given Login as admin
    When Select classes section
    And Click on the 1A item in the list
    And Select Students section in the dashboard
    Then Check all elements are present in chosen subsection of academic class
    When Click on 'Add' button and open popup
    Then Popup is opened
    And Check all elements are present in 'New Students' popup Classes section

  @TC6.119.1 @TC6.119.2 @TC6.119.3 @TC6.126.1 @TC6.126.2 @TC6.126.3 @Regression @Smoke
  Scenario Outline: Check UI of teacher's and student's profile
    Given Login as admin
    When Select <section> section
    And Click on the first item in the list
    And Select Profile section in the dashboard
    Then Check all elements are present in <user>'s profile section
    When Click on 'create' button and open popup
    Then Popup is opened

    Examples:
      | section  | user     |
      | teachers | teacher  |
      | students | student |
      | parents  | parent  |

  @TC6.119.4 @Regression @Smoke
  Scenario Outline: Check UI of edit popup of teacher's profile
    Given Login as admin
    When Select <section> section
    And Click on the first item in the list
    And Select Profile section in the dashboard
    When Click on 'create' button and open popup
    Then Check all elements are present in <user>'s profile edit popup

    Examples:
      | section  | user     |
      | teachers | teacher  |
      | students | student |
      | parents  | parent  |