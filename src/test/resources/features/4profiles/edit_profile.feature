Feature: This feature will cover teacher's, student's, parent's profiles from corresponding section of admin page, especially
  checking personal information of users to be displayed correctly, DB integration, editing user's profile and checking validations

  Background: Login as admin
    Given Login as admin

  @TC6.119.1 @TC6.119.2 @TC6.119.3 @TC6.126.1 @TC6.126.2 @TC6.126.3 @TC6.128.1 @TC6.128.2  @TC6.128.3
    @Regression @Smoke
  Scenario Outline: Check UI of teacher's and student's profile
    When Select <section> section
    And Click on the second item in the list
    And Select Profile section in the dashboard
    Then Check all elements are present in <user>'s profile section
    When Click on 'create' button and open popup
    Then Popup is opened

    Examples:
      | section  | user     |
      | teachers | teacher  |
      | students | student |
      | parents  | parent   |

  @TC6.119.4 @TC6.126.4 @TC6.128.4 @Regression @Smoke
  Scenario Outline: Check UI of edit popup of teacher's profile
    When Select <section> section
    And Click on the second item in the list
    And Select Profile section in the dashboard
    When Click on 'create' button and open popup
    Then Check all elements are present in <user>'s profile edit popup

    Examples:
      | section  | user     |
      | teachers | teacher  |
      | students | student |
      | parents  | parent   |

  @TC6.119.5 @TC6.119.6 @TC6.126.5 @6.126.6 @TC6.128.5 @6.128.6 @Regression @Smoke
  Scenario Outline: Check validations/error messages for input fields of different profiles in edit popup
    When Select <section> section
    And Click on the second item in the list
    And Select Profile section in the dashboard
    When Click on 'create' button and open popup
    And Clear all input fields
    And Click on 'Save' button
    Then Check error messages of blank input fields
    And Fill in all required fields with only spaces
    And Click on 'Save' button
    Then Check error messages of blank input fields
    And Clear all input fields
    And Fill in input fields more than 50 symbols
    And Click on 'Save' button
    Then Check error messages of more symbols filled input fields

    Examples:
      | section  |
      | teachers |
      | students |
      | parents  |

  @TC6.119.8 @TC6.119.9 @TC6.126.8 @6.126.9 @TC6.128.8 @6.128.9 @Regression @Smoke
  Scenario Outline: Check validations for email fields in popups of profiles
    When Select <section> section
    And Click on the second item in the list
    And Select Profile section in the dashboard
    When Click on 'create' button and open popup
    And Clear email input fields
    And Fill existed email
    And Click on 'Save' button
    Then Check error message of existed email
    And Clear email input fields
    When Fill email <invalidEmail>
    And Click on 'Save' button
    Then Check invalid email error message

    Examples:
      | section  | invalidEmail        |
      | teachers | !!invalid@gmail.com |
      | students | !!invalid@gmail.com |
      | parents  | !!invalid@gmail.com |

  @TC6.119.7 @TC6.119.10 @TC6.119.11 @TC6.126.7 @TC6.126.10 @TC6.126.11 @TC6.128.7 @TC6.128.11 @TC6.128.12
    @Regression @Smoke
  Scenario Outline: Check the possibility of editing a user with an existing "Name" and "Surname"
    When Select <section> section
    And Click on the second item in the list
    And Select Profile section in the dashboard
    And Save name and surname value
    When Click on 'create' button and open popup
    And Clear name input fields
    And Clear surname input fields
    And Fill existed name and surname
    Then Click on 'Save' button
    And Check name and surname values are updated on profile page
    And Check updated values are also updated in the <user>'s DB

    Examples:
      | section  | user     |
      | teachers | teacher  |
      | parents  | parent   |
      | students | student |

  @TC6.119.12 @TC6.128.12 @TC6.126.13 @Regression @Smoke
  Scenario Outline: Check functionality of 'X' icon on the "Edit"  pop-up
    When Select <section> section
    And Click on the second item in the list
    And Select Profile section in the dashboard
    When Click on 'create' button and open popup
    And Fill valid name and surname
    And Save name and surname value from DOM
    Then Click on 'X' button
    And Check name and surname values are not updated on profile page
    And Check updated values are not updated in the <user>'s DB

    Examples:
      | section  | user     |
      | teachers | teacher  |
      | parents  | parent   |
      | students | student |

    @TC6.126.10 @Regression @Smoke
    Scenario: Check functionality of "Birth date" field on the edit student pop-up
      When Select students section
      And Click on the second item in the list
      And Select Profile section in the dashboard
      When Click on 'create' button and open popup
      Given Click on 'Birth Date' field
      Then Check 'Birth Date' calendar is opened
      And User is able to select dates between the given interval provided by documentation