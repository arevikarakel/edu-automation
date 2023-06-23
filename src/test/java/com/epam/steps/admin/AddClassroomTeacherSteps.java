package com.epam.steps.admin;

import com.epam.helpers.MessageProvider;
import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AcademicClassPage;
import com.epam.pages.popup.AcademicClassPopup;
import com.epam.pages.popup.AcademicCoursePopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class AddClassroomTeacherSteps extends BaseSteps {

    private AcademicClassPage academicClassPage;
    private AcademicClassPopup academicClassPopup;
    private AcademicCoursePopup academicCoursePopup;

    @Before
    public void initPages() {
        academicClassPage = new AcademicClassPage();
        academicClassPopup = new AcademicClassPopup();
        academicCoursePopup = new AcademicCoursePopup();
    }

    @Then("Check all elements are present in classroom teacher subsection")
    public void checkAllElementsArePresentInSubsection() {
        assertThat(academicClassPage.checkAllElementsArePresentInClassroomTeacherSection())
                .withFailMessage("Not all elements are present in 'Classroom teacher' subsection")
                .isTrue();
    }

    @And("Click on Add Classroom Teacher button")
    public void clickOnAddClassroomTeacherButton() {
        academicClassPage.clickOnAddClassroomTeacherBtn();
    }

    @And("Check all elements are present in 'classroom teacher' popup")
    public void checkAllElementsArePresentInAddClassroomTeacherPopup() {
        assertThat(academicClassPopup.checkUIOfAddClassroomTeacherPopupClassesSection())
                .withFailMessage("Not all elements are present in 'Add Classroom Teacher' popup")
                .isTrue();
    }

    @Then("Check message for not having classroom teacher")
    public void checkMessageForNotHavingClassroomTeacher() {
        logger.info("Check message for not having classroom teacher");
        assertThat(academicClassPage.getTextForClassroomTeacher())
                .isEqualTo(MessageProvider.getMsgForNotHavingClassroomTeacher());
    }

    @And("Check classroomTeacher's name is displayed in the list")
    public void checkClassroomTeacherSNameIsDisplayedInTheList() {
        logger.info("Check classroomTeacher's name is displayed in the list");
        assertThat(academicClassPage.getDisplayedClassroomTeacherName())
                .isEqualTo(SharedTestData.getValueOfItem());
    }

    @And("Check 'Edit' button appears in the screen")
    public void checkEditButtonAppearsInTheScreen() {
        logger.info("Check 'Edit' button appears in the screen");
        assertThat(academicClassPage.checkEditButtonIsDisplayed())
                .withFailMessage("'Edit' button is not displayed, but should be")
                .isTrue();
    }

    @Then("Check list items are the same as in select dropdown")
    public void checkListItemsAreTheSameAsInSelectDropdown() {
        logger.info("Check list items are the same as in select dropdown");
        assertThat(academicCoursePopup.listContainsNames(academicClassPage.listOfOptions()))
                .withFailMessage("list items are not the same as in select dropdown, but should be")
                .isTrue();
    }

    @And("Save list options")
    public void saveListOptions() {
        logger.info("Save list of options");
        academicClassPage.listOfOptions();
    }


    @When("Click on 'Edit' button")
    public void clickOnEditButton() {
        logger.info("Click on 'Edit' button and open 'Edit classroom teacher' popup");
        academicClassPage.clickOnEditButton();
    }

    @When("Save existed classroom teacher name and surname from the list")
    public void saveExistedClassroomTeacherNameAndSurnameFromTheList() {
        logger.info("Save existed classroom teacher name and surname from the list");
        academicClassPage.saveClassroomTeacherNameAndSurname();
    }

    @Then("Check classroom teacher has been changed")
    public void checkClassroomTeacherHasBeenChanged() {
        logger.info("Check classroom teacher has been changed");
        assertThat(SharedTestData.getNameField()).isNotEqualTo(SharedTestData.getValueOfItem());
    }

    @And("Check classroom teacher for {} class is not added in the DB")
    public void checkClassroomTeacherIsNotAddedInTheDB(String classNumber) {
        logger.info("Check classroom teacher for {} class is not added in the DB", classNumber);
        assertThat(dbHelper.isClassroomTeacherAddedToTheDB(classNumber))
                .withFailMessage("Check classroom teacher for {} class is added in the DB, but shouldn't be",
                        classNumber)
                .isFalse();
    }

    @And("Check classroom teacher for {} class is added in the DB")
    public void checkClassroomTeacherIsAddedInTheDB(String classNumber) {
        logger.info("Check classroom teacher for {} class is not added in the DB", classNumber);
        assertThat(dbHelper.isClassroomTeacherAddedToTheDB(classNumber))
                .withFailMessage("Check classroom teacher for {} class is not added in the DB, but should be",
                        classNumber)
                .isTrue();
    }
}
