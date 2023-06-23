package com.epam.steps.common;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AdminPage;
import com.epam.pages.main.LoginPage;
import com.epam.pages.main.SuperAdminPage;
import com.epam.steps.BaseSteps;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends BaseSteps {
    private LoginPage loginPage;
    private AdminPage adminPage;
    private SuperAdminPage superAdminPage;

    @BeforeAll
    public static void setupDriver() {
        setup();
    }

    @AfterAll
    public static void tearDown() {
        quitDriver();
    }

    @Before
    public void initPages() {
        loginPage = new LoginPage();
        adminPage = new AdminPage();
        superAdminPage = new SuperAdminPage();
        loginPage.goToPage();
    }

    @Given("Fill {} and {} fields and click on 'Login' button")
    public void fillEmailAndPassword(String email, String password) {
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Given("Login as {}")
    public void loginByRole(String role) {
        logger.info("Login as {}", role);
        loginPage.loginByRole(role);
    }

    @Then("The user is on {} page")
    public void theUserIsOnRequestedPage(String roleName) {
        logger.info("Check the user is on the {} page", roleName);
        assertThat(roleName)
                .withFailMessage("The user is not on demanded page as role name is different from expected.")
                .isEqualToIgnoringCase(superAdminPage.getRoleName());
    }

    @Then("Check error message")
    public void checkErrorMessage() {
        logger.info("Check error message on the login page");
        assertThat(loginPage.getErrorMessage())
                .withFailMessage("Error message for incorrect login/or password was different from expected one.")
                .isEqualTo(ErrorMessagesProvider.getIncorrectLoginOrPasswordErrMessage());
    }

    @Then("Sign in as admin with generated password")
    public void signInAsAdminWithGeneratedPassword() {
        loginPage.goToPage();
        loginPage.loginWithLastSavedCredentials();
        assertThat(adminPage.getNameAndSurname())
                .withFailMessage("Last created admin's name and surname is not equal to the signed in admin's name and surname.")
                .contains(SharedTestData.getNameField(), SharedTestData.getSurnameField());
    }

    @Then("User is not able to login using current credentials")
    public void userIsNotAbleToLoginUsingCurrentCredentials() {
        loginPage.goToPage();
        loginPage.loginWithLastSavedCredentials();
        assertThat(loginPage.getErrorMessage())
                .withFailMessage("Error message for incorrect login/or password was different from expected one.")
                .isEqualTo(ErrorMessagesProvider.getIncorrectLoginOrPasswordErrMessage());
    }
}
