package com.epam.steps.admin;

import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.AcademicCoursePopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class AddClassesForCoursesSteps extends BaseSteps {
    private AcademicCoursePopup coursesPopup;
    private AdminPage adminPage;

    @Before
    public void initPages() {
        coursesPopup = new AcademicCoursePopup();
        adminPage = new AdminPage();
    }

    @When("Click on {} dropdown")
    public void clickOnDropdown(String dropdown) {
        logger.info("Click on {} dropdown list and open it", dropdown);
        coursesPopup.clickOnDropDown(dropdown);
    }

    @Then("Check the list of created {} in the dropdown list")
    public void checkTheListOfCreatedItemsInTheDropdownList(String name) {
        logger.info("Check the list of created {} in the dropdown list", name);
        assertThat(coursesPopup.areListOptionsPresent(name))
                .withFailMessage("Elements in dropdown list are not displayed")
                .isTrue();
    }

    @When("Select the first item from {} dropdown list")
    public void selectClassFromDropdownList(String dropdown) {
        logger.info("Select the first item from {} dropdown list", dropdown);
        coursesPopup.selectFirstItem(dropdown);
    }

    @And("Save value of selected {} item")
    public void saveValueOfSelectedItem(String value) {
        logger.info("Save value of selected {} item", value);
        coursesPopup.saveValueOfSelectedItem(value);
    }

    @Then("Check the user choice is displayed in the {} field")
    public void checkTheUserChoiceIsDisplayedInTheField(String field) {
        logger.info("Check the user choice is displayed in the {} field", field);
        assertThat(coursesPopup.checkTheUserChoiceIsDisplayedInTheField(field))
                .withFailMessage("User choose is not displayed, but should be")
                .isTrue();
    }

    @And("Check created item is displayed in the list")
    public void checkCreatedItemForAcademicCourseIsDisplayedInTheList() {
        logger.info("Check created item for academic course is displayed in the list");
        assertThat(adminPage.getListSize())
                .withFailMessage("Item is not displayed in the list, but should be")
                .isGreaterThan(SharedTestData.getListSize());
    }

    @And("Check class for academic course is added in the DB")
    public void checkClassForAcademicCourseIsAddedInTheDB() {
        logger.info("Check if academic class for academic course is added in the DB");
        assertThat(dbHelper.isAcademicClassAddedToAcademicCourse(SharedTestData.getValueOfItem()))
                .withFailMessage("Academic class is not linked to academic course and not added to the DB" +
                        ", but it should be")
                .isTrue();
    }

    @Then("Check error messages of blank selection fields")
    public void checkErrorMessagesOfBlankSelectionFields() {
        logger.info("Check error messages of blank selection fields");
        assertThat(coursesPopup.checkErrorMessagesOfBlankSelectRequiredFields())
                .withFailMessage("Error message is incorrect")
                .isTrue();
    }

    @And("Save list size from section")
    public void saveListSizeFromSection() {
        adminPage.setListSize();
    }
}
