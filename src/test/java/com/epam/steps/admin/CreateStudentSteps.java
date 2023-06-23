package com.epam.steps.admin;

import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.CreatePopup;
import com.epam.pages.popup.StudentsPopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateStudentSteps extends BaseSteps {
    private StudentsPopup studentsPopup;
    private CreatePopup createPopup;
    private AdminPage adminPage;

    @Before
    public void initPages() {
        studentsPopup = new StudentsPopup();
        createPopup = new CreatePopup();
        adminPage = new AdminPage();
    }

    @Given("Fill in all required fields and click on 'Generate password' button in students section create popup")
    public void fillInAllRequiredFieldsInStudentsSectionCreatePopup() {
        createPopup.fillNameSurnameEmail();
        createPopup.clickOnGeneratePasswordButton();
        studentsPopup.fillBloodGenderBirthdayAddress();
    }

    @When("Fill in existed name, surname, all other required fields besides email on the student section create popup")
    public void fillInExistedNameSurnameNonExistedMailAllOtherRequiredFields() {
        createPopup.fillExistedName();
        createPopup.fillExistedSurname();
        studentsPopup.fillBloodGenderBirthdayAddress();
    }

    @When("Click on {} field having drop-down list and select {} from the list")
    public void clickOnFieldAndSelectAcademicClassNameFromTheList(String fieldName, String valueToSelect) {
        studentsPopup.clickOnFieldAndSelectValue(fieldName, valueToSelect);
    }

    @And("Click on 'Birth Date' field")
    public void clickOnBirthDateField() {
        studentsPopup.clickOnBirthDateField();
    }

    @And("Check 'Birth Date' calendar is opened")
    public void checkBirthDateCalendarIsOpened() {
        logger.info("Check calendar is opened.");
        assertThat(studentsPopup.checkCalendarIsOpened())
                .withFailMessage("Calendar is not opened in create popup - students section")
                .isTrue();
    }

    @Then("User is able to select dates between the given interval provided by documentation")
    public void userIsAbleToSelectDatesBetweenTheGivenIntervalProvidedByDocumentation() {
        logger.info("User is able to select dates between interval 1900 and 3 years before moment of selection");
        assertThat(studentsPopup.isYearFromInterval())
                .withFailMessage("Selected year is not from the given interval provided by documentation")
                .isTrue();
    }

    @Then("The user choice is displayed in the {} field")
    public void theUserChoiceIsDisplayedInTheLinkedClassField(String fieldName) {
        assertThat(studentsPopup.checkValueOfSelectedField(fieldName))
                .withFailMessage("Saved value of selected fields is not the same with what user sees on screen")
                .isTrue();
    }

    @Then("Check that 'Linked Parent' and 'Linked Class' fields are not selected")
    public void checkThatLinkedParentAndLinkedClassFieldsAreNotSelected() {
        assertThat(studentsPopup.areLinkedClassAndParentSelected())
                .withFailMessage("'Linked Parent' and 'Linked Class' fields are selected, but they shouldn't be.")
                .isFalse();
    }

    @Then("Check all fields are present in create popup - students section")
    public void checkAllFieldsArePresentInCreatePopupStudentsSection() {
        assertThat(studentsPopup.checkUIOfCreatePopupStudentsSection())
                .withFailMessage("Elements are not present in create popup - student section")
                .isTrue();
    }

    @Then("Check all input fields are empty in students section create popup")
    public void checkAllInputFieldsAreEmptyInStudentsSectionCreatePopup() {
        assertThat(studentsPopup.checkAllFieldsAreEmptyInStudentsCreatePopup())
                .withFailMessage("All input fields are not empty in create popup - student section")
                .isTrue();
    }

    @Then("Check the student password is hashed in the DB")
    public void checkTheStudentPasswordIsHashedInTheDB() {
        assertThat(dbHelper.isStudentPasswordHashed())
                .withFailMessage("Password was not hashed in the DB.")
                .isTrue();
    }
}
