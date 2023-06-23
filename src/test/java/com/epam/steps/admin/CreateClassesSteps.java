package com.epam.steps.admin;

import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.AcademicClassPopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateClassesSteps extends BaseSteps {
    private AcademicClassPopup academicClassPopup;
    private AdminPage adminPage;

    @Before
    public void initPages() {
        academicClassPopup = new AcademicClassPopup();
        adminPage = new AdminPage();
    }

    @Then("Check all fields are present in create popup - academic classes section")
    public void checkAllFieldsArePresentInCreatePopupAcademicClassesSection() {
        assertThat(academicClassPopup.checkUIOfCreatePopupClassesSection())
                .withFailMessage("All required elements in academic classes section are not displayed")
                .isTrue();
    }

    @When("Fill existed academic class")
    public void fillExistedAcademicClass() {
        academicClassPopup.fillExistedAcademicClass();
    }

    @Then("Check error message of existed academic class")
    public void checkErrorMessageOfExistedAcademicClass() {
        assertThat(academicClassPopup.checkExistedClassErrorMessage())
                .withFailMessage("Error message of existed class is incorrect")
                .isTrue();
    }

    @When("Fill in unique academic class")
    public void fillAcademicClassValue() {
        academicClassPopup.fillAcademicClass();
    }

    @Then("Academic class is created and displayed in the list")
    public void academicClassIsCreatedAndDisplayedInTheList() {
        assertThat(adminPage.checkAcademicClassIsDisplayedInTheList())
                .withFailMessage("Last created academic class is not added to the list, but it should be")
                .isTrue();
    }

    @And("Save academic class value")
    public void saveAcademicClassValue() {
        academicClassPopup.saveAcademicClassValue();
    }

    @And("Academic class is added to the DB")
    public void academicClassIsAddedToTheDB() {
        assertThat(dbHelper.isClassAddedToTheDB(SharedTestData.getValueOfItem()))
                .withFailMessage("Academic class is not added to the DB, but it should be.")
                .isTrue();
    }
}
