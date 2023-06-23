Feature: Login as different users
  User will be able to login as different users (for example as super admin, admin and so on), also in this
  feature will be covered unsuccessfully login attempts and error messages

  @TC1.7.2 @Regression @Smoke
  Scenario: Check super admin logs in successfully
    Given Login as super admin
    Then The user is on super admin page

  @TC1.7.3 @TC1.7.4 @Regression
  Scenario Outline: Checking displayed error messages while using incorrect credentials during login
    Given Fill <email> and <password> fields and click on 'Login' button
    Then Check error message
    Examples:
      | email           | password      |
      | wrongEmail      | wrongPassword |
      |                 | wrongPassword |
      | wrongEmail      |               |
      |                 |               |
      |                 | Sa1234567+    |
      | super@gmail.com |               |

  @TC1.7.6 @Regression
  Scenario: Check ability to login as an admin using last generated password after creating new admin
    Given Login as super admin
    And Click on 'create' button and open popup
    And Fill in name, surname, email fields and click on 'Generate password' button
    And Save values from name, surname, password and email fields
    And Click on 'Save' button
    Then Sign in as admin with generated password
