package com.epam.steps.admin;

import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.AcademicCoursePopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;

import static org.assertj.core.api.Assertions.assertThat;

public class AddStudentsForClasses extends BaseSteps {

    private AcademicCoursePopup coursesPopup;
    private AdminPage adminPage;

    @Before
    public void initPages() {
        coursesPopup = new AcademicCoursePopup();
        adminPage = new AdminPage();
    }
    @And("Save linked {} count for {} class from DB and list size from section")
    public void saveLinkedStudentsCountForAClassFromDBAndListSizeFromSection(String item, String className) {
        adminPage.setListSize();
        coursesPopup.saveCountOfSelectedItemsInTheBox(item);
        int dbCount = dbHelper.findCountOfStudentsAddedToTheClass(className);
        logger.info("Save {} count '{}' linked to '{}' class to shared test data",
                item, dbCount, className);
        SharedTestData.setCountLinkedToItem(dbCount);
    }

    @And("Check student for {} class is not added in the DB")
    public void checkStudentForAClassIsNotAddedInTheDB(String className) {
        logger.info("Check student for {} class is not added in the DB", className);
        assertThat(isStudentsForClassesAddedToTheDB(className))
                .withFailMessage("Teacher for course is added to the DB, but should not be")
                .isTrue();
    }

    @And("Check student for {} class is added in the DB")
    public void checkStudentForAClassIsAddedInTheDB(String className) {
        logger.info("Check student for {} class is not added in the DB", className);
        assertThat(isStudentsForClassesAddedToTheDB(className))
                .withFailMessage("Teacher for course is not added to the DB, but should be")
                .isFalse();
    }

    private boolean isStudentsForClassesAddedToTheDB(String className) {
        int dbCount = dbHelper.findCountOfStudentsAddedToTheClass(className);
        int sharedCount = SharedTestData.getCountLinkedToItem();
        logger.info("Count of students linked to {} academic class in the DB is {}, count of students in the list is {}," +
                "count of selected students is {}", className, dbCount, sharedCount, coursesPopup.getCountOfSelectedItems());
        return (dbCount - sharedCount) == coursesPopup.getCountOfSelectedItems();
    }

    @And("Save the first item value from the list")
    public void saveTheFirstItemValueFromTheList() {
        adminPage.saveTheFirstItemFromTheList();
        logger.info("Save the first item value from the list - {}", SharedTestData.getValueOfItem());
    }
}
