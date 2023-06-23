package com.epam.steps.admin;

import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AdminPage;
import com.epam.pages.main.SubjectPage;
import com.epam.pages.popup.SubjectPopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class AddTeacherToSubjectSteps extends BaseSteps {
    private SubjectPage subjectPage;
    private SubjectPopup subjectPopup;
    private AdminPage adminPage;

    @Before
    public void initPages() {
        subjectPage = new SubjectPage();
        subjectPopup = new SubjectPopup();
        adminPage = new AdminPage();
    }

    @And("Click on the {} item in the list")
    public void clickOnTheSubjectInTheList(String itemName) {
        subjectPage.clickOnItemFromTheList(itemName);
    }

    @And("Click on 'Add' button and open popup")
    public void clickOnAddButtonAndOpenPopup() {
        subjectPage.clickOnAddButton();
    }

    @Then("Check all elements are present in subject dashboard teachers section")
    public void checkAllElementsArePresentInSubjectDashboardTeachersSection() {
        assertThat(subjectPopup.checkUIofAddTeachersPopup())
                .withFailMessage("Elements are not displayed")
                .isTrue();
    }

    @And("Select {} section in the dashboard")
    public void selectSectionInDashboard(String section) {
        subjectPage.clickOnSectionByText(section);
    }

    @And("Check item is not displayed on the list")
    public void checkItemIsNotDisplayedOnTheList() {
        assertThat(adminPage.getListSize())
                .withFailMessage("Teacher for item is displayed on the list, but should not be")
                .isEqualTo(SharedTestData.getListSize());
    }

    @And("Check item is displayed on the list")
    public void checkTeacherForItemIsDisplayedInTheList() {
        assertThat(adminPage.getListSize())
                .withFailMessage(adminPage.getListSize() +
                        " is not greater than " + SharedTestData.getListSize())
                .isGreaterThan(SharedTestData.getListSize());
    }

    @And("Check Teacher for {} subject is not added to the DB")
    public void checkTeacherForSubjectIsNotAddedToTheDB(String subjectName) {
        assertThat(isTeacherForSubjectAddedToTheDB(subjectName))
                .withFailMessage("Teacher for subject is added to the DB, but should not be")
                .isFalse();
    }

    @And("Check Teacher for {} subject is added to the DB")
    public void checkTeacherForSubjectIsAddedToTheDB(String subjectName) {
        assertThat(isTeacherForSubjectAddedToTheDB(subjectName))
                .withFailMessage("Teacher for subject is not added to the DB, but should be")
                .isTrue();
    }

    @Then("Check error messages of blank selections")
    public void checkErrorMessagesOfBlankSelections() {
        assertThat(subjectPopup.checkErrorMessagesOfBlankSelection())
                .withFailMessage("Selection field is not blank, but it should be")
                .isTrue();
    }

    @And("Save quantity of matched items result and clear search box")
    public void saveQuantityOfMatchedItemsResultAndClearSearchBox() {
        subjectPopup.clearTeacherSelectField();
        subjectPopup.saveQuantityOfMatchedItemsResult();
    }

    @Then("Check result quantity is the same as was in the previous search")
    public void checkResultQuantityIsTheSameAsWasInThePreviousSearch() {
        assertThat(subjectPopup.checkResultQuantityIsTheSameAsWasInThePreviousSearch())
                .withFailMessage("Result quantity is different from the previous search")
                .isTrue();
    }

    @And("Save linked teachers count for {} subject from DB and list size from subject section")
    public void saveLinkedTeachersCountForSubjectFromDBAndListSizeFromSubjectSection(String subjectName) {
        adminPage.setListSize();
        subjectPopup.saveCountOfSelectedTeachersInTheBox();
        int dbCount = dbHelper.findCountOfTeachersAddedToTheSubject(subjectName);
        logger.info("Save teachers count '{}' linked to '{}' subject to shared test data",
                dbCount, subjectName);
        SharedTestData.setCountLinkedToItem(dbCount);
    }

    private boolean isTeacherForSubjectAddedToTheDB(String subjectName) {
        int dbCount = dbHelper.findCountOfTeachersAddedToTheSubject(subjectName);
        int sharedCount = SharedTestData.getCountLinkedToItem();
        logger.info("Count of teachers linked to subject in the DB is {}, count of teachers in the list is {}," +
                "count of selected teachers is {}", dbCount, sharedCount, subjectPopup.getCountOfSelectedTeachers());
        return (dbCount - sharedCount) == subjectPopup.getCountOfSelectedTeachers();
    }
}
