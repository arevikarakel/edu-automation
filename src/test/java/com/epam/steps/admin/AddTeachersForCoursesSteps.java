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

public class AddTeachersForCoursesSteps extends BaseSteps {

    private AcademicCoursePopup coursesPopup;
    private AdminPage adminPage;

    @Before
    public void initPages() {
        coursesPopup = new AcademicCoursePopup();
        adminPage = new AdminPage();
    }

    @When("Select {} from dropdown list")
    public void selectItemFromSelectTeachersDropdownList(String item) {
        coursesPopup.selectTheFirstItem(item);
    }

    @And("Save linked {} count for {} course from DB and list size from section")
    public void saveLinkedTeachersCountForCourseFromDBAndListSizeFromSection(String item, String courseName) {
        adminPage.setListSize();
        coursesPopup.saveCountOfSelectedItemsInTheBox(item);
        int dbCount = dbHelper.findCountOfTeachersAddedToTheCourse(courseName);
        logger.info("Save {} count '{}' linked to '{}' course to shared test data",
                item, dbCount, courseName);
        SharedTestData.setCountLinkedToItem(dbCount);
    }

    @And("Check multi-select drop-down list is opened for {}")
    public void checkMultiSelectDropDownListIsOpened(String item) {
        assertThat(coursesPopup.checkDropDownListIsOpened(item))
                .withFailMessage("Drop down list is not opened, but it should be")
                .isTrue();
    }

    @And("Check teacher for {} course is added in the DB")
    public void checkTeacherForEnglishCourseIsAddedInTheDB(String courseName) {
        logger.info("Check teacher for {} course is added in the DB", courseName);
        assertThat(isTeacherForCourseAddedToTheDB(courseName))
                .withFailMessage("Teacher for course is not added to the DB, but should be")
                .isFalse();
    }

    @And("Check teacher for {} course is not added in the DB")
    public void checkTeacherForAcademicCourseIsNotAddedInTheDB(String courseName) {
        logger.info("Check teacher for {} course is not added in the DB", courseName);
        assertThat(isTeacherForCourseAddedToTheDB(courseName))
                .withFailMessage("Teacher for course is added to the DB, but should not be")
                .isTrue();
    }

    private boolean isTeacherForCourseAddedToTheDB(String courseName) {
        int dbCount = dbHelper.findCountOfTeachersAddedToTheCourse(courseName);
        int sharedCount = SharedTestData.getCountLinkedToItem();
        logger.info("Count of teachers linked to {} academic course in the DB is {}, count of teachers in the list is {}," +
                "count of selected teachers is {}", courseName, dbCount, sharedCount, coursesPopup.getCountOfSelectedItems());
        return (dbCount - sharedCount) == coursesPopup.getCountOfSelectedItems();
    }

    @Then("Check only teachers who are added for subject linked to {} course is displayed and can be selected")
    public void checkOnlyTeachersWhoAreAddedForSubjectLinkedToEnglishCourseIsDisplayedAndCanBeSelected(String courseName) {
        logger.info("Check only teachers who are added for subject linked to {} course is displayed and can be selected",
                courseName);
        assertThat(coursesPopup.listContainsNames(
                dbHelper.findTeachersNameAndSurnameByAcademicCourseName(courseName)))
                .withFailMessage("not only teachers who are added for subject and is linked to course" +
                        " are displayed and can be selected")
                .isTrue();
    }

    @When("Add teacher for academic courses")
    public void addTeacherForAcademicCourses() {
        logger.info("Insert into academic courses teacher");
        int count = dbHelper.countOfAcademicCoursesInTheDB();
        if (count <= 10) {
            for (int i = 1; i < dbHelper.countOfAcademicCoursesInTheDB(); i++) {
                dbHelper.insertIntoAcademicCourseTeachersByCourseID(i);
            }
        } else {
            for (int i = 1; i < 11; i++) {
                dbHelper.insertIntoAcademicCourseTeachersByCourseID(i);
            }
        }
    }
}
