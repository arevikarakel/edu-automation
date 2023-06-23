package com.epam.steps.common;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import com.epam.helpers.UserDataProvider;
import com.epam.pages.main.SuperAdminPage;
import com.epam.pages.popup.CreatePopup;
import com.epam.pages.popup.StudentsPopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserSteps extends BaseSteps {

    private SuperAdminPage superAdminPage;
    private CreatePopup createPopup;
    private StudentsPopup studentsPopup;

    @Before
    public void initPages() {
        superAdminPage = new SuperAdminPage();
        createPopup = new CreatePopup();
        studentsPopup = new StudentsPopup();
    }

    @Given("Fill valid name and surname")
    public void fillValidNameAndSurname() {
        createPopup.fillName(UserDataProvider.getValidName());
        createPopup.fillSurname(UserDataProvider.getValidSurname());
    }

    @And("Fill email {}")
    public void fillEmail(String email) {
        createPopup.fillEmail(email);
    }

    @And("Fill existed email")
    public void fillExistedEmail() {
        createPopup.fillExistedEmail();
    }

    @And("Fill non-existed email")
    public void fillNonExistedEmail() {
        createPopup.fillNonExistedEmail();
    }

    @Given("Fill existed name and surname")
    public void fillExistedNameAndSurname() {
        createPopup.fillExistedName();
        createPopup.fillExistedSurname();
    }

    @And("Fill in name, surname, email fields and click on 'Generate password' button")
    public void fillInAllRequiredFields() {
        createPopup.fillNameSurnameEmail();
        createPopup.clickOnGeneratePasswordButton();
    }

    @Given("Fill in input fields more than 50 symbols")
    public void fillInInputFieldsMoreThan50Symbols() {
        createPopup.fillInputFieldsWithMoreSymbols();
    }

    @Given("Clear {} input fields")
    public void clearField(String field) {
       createPopup.clearInputField(field);
    }

    @Given("Fill in input fields with not allowed symbols")
    public void fillInInputFieldsWithNotAllowedSymbols() {
        createPopup.fillInputFieldsWithNotAllowedSymbols();
    }

    @And("Click on 'Generate password' button")
    public void clickOnGeneratePasswordButton() {
        createPopup.clickOnGeneratePasswordButton();
    }

    @And("Click on 'Save' button")
    public void clickOnSaveButton() {
        createPopup.clickOnSaveButton();
    }

    @When("Click on 'X' button")
    public void clickOnXButton() {
        createPopup.clickOnXButton();
    }

    @And("Click on 'create' button and open popup")
    public void clickOnCreateButtonAndOpenPopup() {
        superAdminPage.clickOnCreateButton();
    }

    @And("Save value from email input field")
    public void getValueFromEmailInputField() {
        createPopup.saveEmailValue();
    }

    @And("Save value from password input field")
    public void getGeneratedPasswordFromInputField() {
        createPopup.savePasswordValue();
    }

    @And("Save values from name, surname, password and email fields")
    public void getAndSaveValuesFromRequiredFields() {
        createPopup.saveEmailValue();
        createPopup.savePasswordValue();
        createPopup.saveNameAndSurnameValue();
    }

    @And("Fill in all required fields with only spaces")
    public void fillInAllRequiredFieldsWithOnlySpaces() {
        createPopup.fillInputFieldsWithSpaces();
    }

    @Then("Popup is closed")
    public void checkPopupIsClosed() {
        logger.info("Check popup is closed");
        assertThat(createPopup.popupIsClosed())
                .withFailMessage("Popup is not closed, but it should be.")
                .isTrue();
    }

    @Then("Popup is opened")
    public void popupIsOpened() {
        logger.info("Check popup is opened");
        assertThat(createPopup.popupIsClosed())
                .withFailMessage("Popup is not opened, but it should be.")
                .isFalse();
    }

    @And("Check the generated password has been changed")
    public void passwordIsChanged() {
        assertThat(createPopup.passwordIsChanged())
                .withFailMessage("Password hasn't been changed, but it was meant to be.")
                .isTrue();
    }

    @Then("Check all input fields are empty in create popup")
    public void checkAllInputFieldsAreEmptyInCreatePopup() {
        assertThat(createPopup.checkAllInputFieldsAreEmpty())
                .withFailMessage("Input fields are not empty in create popup, but should be empty.")
                .isTrue();
    }

    @Then("Check all fields are present in create popup")
    public void checkAllFieldsArePresentInCreatePopup() {
        assertThat(createPopup.checkAllFieldsArePresent())
                .withFailMessage("All fields are not present in teachers section - create popup, but should be.")
                .isTrue();
    }

    @Then("'Password' field is filled with autogenerated password")
    public void passwordFieldIsFilledWithAutogeneratedPassword() {
        assertThat(createPopup.checkGeneratedPasswordIsFilled())
                .withFailMessage("'Password' field is not filled with autogenerated password, but should be.")
                .isTrue();
    }

    @Then("Check error messages of more symbols filled input fields")
    public void checkErrorMessagesOfMoreSymbolsFilledInputFields() {
        assertThat(createPopup.checkErrorMessagesOfMoreSymbolsFilledInputFields())
                .withFailMessage("Error message of more symbols is incorrect")
                .isTrue();
    }

    @Then("Check error message of filling not allowed symbols")
    public void checkErrorMessagesOfNotAllowedSymbolsFilledInputFields() {
        assertThat(createPopup.checkErrorMessagesOfNotAllowedSymbolsFilledInputFields())
                .withFailMessage("Error message of not allowed symbols is incorrect")
                .isTrue();
    }

    @Then("Check invalid email error message")
    public void checkInvalidErrorMessage() {
        logger.info("Check error message of invalid inputted email -> {}",
                ErrorMessagesProvider.getInvalidEmailErrMessage());
        assertThat(createPopup.getEmailFieldErrorMessage())
                .withFailMessage("Error message of invalid email is incorrect")
                .isEqualTo(ErrorMessagesProvider.getInvalidEmailErrMessage());
    }

    @Then("Check error messages of blank input fields")
    public void checkErrorMessagesOfBlankInputFields() {
        assertThat(createPopup.checkErrorMessagesOfBlankInputFields())
                .withFailMessage("Error message of blank input fields is incorrect")
                .isTrue();
    }

    @Then("Check the structure of generated password")
    public void checkTheStructureOfGeneratedPassword() {
        assertThat(createPopup.checkGeneratedPasswordStructure())
                .withFailMessage("Generated password structure doesn't comply with the given requirements.")
                .isTrue();
    }

    @Then("Check error message of existed email")
    public void checkErrorMessageOfExistedEmail() {
        logger.info("Check error message of existed email");
        assertThat(createPopup.getEmailFieldErrorMessage())
                .withFailMessage("Error message of existed email is incorrect")
                .isEqualTo(ErrorMessagesProvider.getExistedEmailErrMessage());
    }

    @Then("Check 'Generate password' button is active")
    public void checkGeneratePasswordButtonIsActive() {
        assertThat(createPopup.checkGeneratePasswordButtonIsEnabled())
                .withFailMessage("'Generate password' button is not active, but it should be.")
                .isTrue();
    }

    @Then("Check the user is not able to input any data in the password field")
    public void theUserIsNotAbleToInputAnyDataInPasswordField() {
        assertThat(createPopup.checkThePasswordFieldIsDisabled())
                .withFailMessage("User is able to input data in the password field, as it is not readonly.")
                .isTrue();
    }

    @Then("Check blank input fields error messages are not displayed")
    public void checkBlankInputFieldsErrorMessagesAreNotDisplayed() {
        assertThat(createPopup.checkBlankInputFieldsErrorMessagesAreNotDisplayed())
                .withFailMessage("Blank input fields error messages are displayed")
                .isTrue();
    }

    @Then("Check all elements are present in {}'s profile edit popup")
    public void checkAllElementsArePresentInProfileEditPopup(String user) {
        logger.info("Check all elements are present in {}'s profile edit popup", user);
        if (user.equals("student")) {
            assertThat(studentsPopup.checkUIOfEditPopupStudentsProfile())
                    .withFailMessage("Not all elements are present on profile page")
                    .isTrue();
        } else {
            assertThat(createPopup.checkAllFieldsArePresentOnProfilePage())
                    .withFailMessage("Not all elements are present on profile page")
                    .isTrue();
        }
    }
}
