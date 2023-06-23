package com.epam.steps.admin;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import com.epam.helpers.MessageProvider;
import com.epam.pages.main.TimetablePage;
import com.epam.pages.popup.TimetablePopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class AddTimetableForClassesSteps extends BaseSteps {

    private TimetablePage timetablePage;
    private TimetablePopup timetablePopup;

    @Before
    public void initPages() {
        timetablePage = new TimetablePage();
        timetablePopup = new TimetablePopup();
    }

    @Then("Check all elements are present in timetable section")
    public void checkAllElementsArePresentInTimetableSection() {
        logger.info("Check all elements are present in timetable section");
        assertThat(timetablePage.checkAllElementsArePresentInTimetableSection())
                .withFailMessage("Elements are not present in timetable section")
                .isTrue();
    }

    @Then("Check all elements are present in opened timetable page")
    public void checkAllElementsArePresentInOpenedTimetablePage() {
        logger.info("Check all elements are present in opened timetable page");
        assertThat(timetablePage.checkAllElementsArePresentInOpenedTimetablePage())
                .withFailMessage("Elements are not present in opened timetable page")
                .isTrue();
    }

    @When("Click on '+' button")
    public void clickOnButton() {
        timetablePage.clickOnPlusButton();
    }

    @And("Check all elements are present in 'Add lesson' popup")
    public void checkAllElementsArePresentInAddLessonPopup() {
        logger.info("Check all elements are present in 'Add lesson' popup");
        assertThat(timetablePopup.checkAllElementsArePresentInAddLessonPopup())
                .withFailMessage("Elements are not present in 'Add lessons' popup")
                .isTrue();
    }

    @And("Select the first course from drop-down list")
    public void selectTheFirstCourse() {
        logger.info("Select the first course from drop-down list");
        timetablePopup.selectCourseForDayOfWeek();
    }

    @And("Check selection is empty")
    public void checkSelectionIsEmpty() {
        logger.info("Check selection is empty");
        assertThat(timetablePopup.checkItemsAreNotSelected())
                .withFailMessage("Element is selected, but it shouldn't be")
                .isTrue();
    }

    @And("Check course is not displayed in timetable")
    public void checkCourseIsNotDisplayedInTimetable() {
        assertThat(timetablePage.checkCourseIsNotAddedInTimetableForDayOfWeek())
                .withFailMessage("Course is displayed in timetable, but shouldn't be")
                .isTrue();

    }

    @And("Save list size for {} class, DB count and day of week - {}")
    public void saveListSizeForCoursesDBCountAndDayOfWeek(String courseName, String dayOfWeek) {
        logger.info("Save list size for lessons and day of week - {}", dayOfWeek);
        timetablePage.saveListSizeAndDayOfWeek(dayOfWeek);
        timetablePage.saveSelectedCourseValueByGivenName(courseName);
        SharedTestData.setCountLinkedToItem(dbHelper.findCountOfCoursesForTimetableByAcademicClassName(SharedTestData.getLastCreatedItemName()));
    }

    @Then("Check popup for chosen day of week is opened")
    public void checkPopupForChosenDayOfWeekIsOpened() {
        assertThat(timetablePopup.checkPopupForDayOfWeekIsOpened())
                .withFailMessage("Popup is not opened, but it should be")
                .isTrue();
    }

    @Then("Check popup for chosen day of week is closed")
    public void checkPopupForChosenDayOfWeekIsClosed() {
        assertThat(timetablePopup.checkPopupForDayOfWeekIsOpened())
                .withFailMessage("Popup is opened, but it shouldn't be")
                .isFalse();
    }

    @Then("Check alert is present")
    public void checkAlertIsPresent() {
        logger.info("Check alert is present");
        assertThat(timetablePopup.checkAlertIsPresent())
                .withFailMessage("Alert is not present")
                .isTrue();
    }

    @And("Accept alert and leave the page")
    public void acceptAlertAndLeaveThePage() {
        timetablePopup.acceptAlert();
    }

    @And("Check course for timetable is not added in the DB")
    public void checkCourseForTimetableIsNotAddedInTheDB() {
        logger.info("Check course for timetable is not added in the DB");
        assertThat(isCourseForTimetableAddedToTheDB())
                .withFailMessage("course for timetable is not added in the DB, but it should be")
                .isFalse();
    }

    private boolean isCourseForTimetableAddedToTheDB() {
        int dbCount = dbHelper.findCountOfCoursesForTimetableByAcademicClassName(SharedTestData.getLastCreatedItemName());
        int sharedCount = SharedTestData.getCountLinkedToItem();
        logger.info("Count of courses added to timetable in the DB now is {}, count of courses in the DB before was {}",
                dbCount, sharedCount);
        return (dbCount == sharedCount + 1);
    }

    @Then("Check course is added at the end of the timetable list")
    public void checkCourseIsAddedInTheTimetableList() {
        logger.info("Check course is added at the end of the timetable list");
        assertThat(timetablePage.checkCourseIsAddedInTimetablePage())
                .withFailMessage("Course is not added in timetable page")
                .isTrue();
    }

    @When("Save selected course name")
    public void saveSelectedCourseName() {
        logger.info("Save selected course name");
        timetablePopup.saveSelectedCourseNameFromSelectField();
    }

    @When("Click on 'Save' button in Add Lesson popup")
    public void clickOnSaveButtonInAddLessonPopup() {
        logger.info("Click on 'Save' button in Add Lesson popup");
        timetablePopup.clickOnSaveButtonInAddLessonPopup();
    }

    @Then("Check only courses which are added for {} class is displayed and can be selected")
    public void checkOnlyCoursesWhichAreAddedForClassIsDisplayedAndCanBeSelected(String academicClass) {
        logger.info("Check only courses which are added for class is displayed and can be selected");
        assertThat(timetablePopup.listContainsNames(
                dbHelper.findCourseNamesByIdsAndClassName(academicClass)))
                .withFailMessage("not only courses which are added for academic class " +
                        " are displayed and can be selected")
                .isTrue();
    }

    @When("Click on 'Course' drop-down")
    public void clickOnCourseDropDown() {
        logger.info("Click on 'Course' drop-down");
        timetablePopup.clickOnSelectCourseInAddLessonPopup();
    }

    @Then("Check success message for creating timetable")
    public void checkSuccessMessageForCreatingTimetable() {
        String successMsgText = timetablePage.getSuccessMsgText();
        String demandedMsgText = MessageProvider.getSuccessMsgForTimetableCreation();
        logger.info("Check success message for creating timetable -> '{}' is the same as demanded -> '{}'",
                successMsgText, demandedMsgText);
        assertThat(successMsgText.equals(demandedMsgText))
                .withFailMessage("Success message is not the same as required")
                .isTrue();
    }

    @Then("Check error message for not selecting courses for timetable")
    public void checkErrorMessageForNotSelectingCoursesForTimetable() {
        String message = timetablePage.getErrorMsgTextForNotSelectingCourse();
        String demandedMsgText = ErrorMessagesProvider.getErrMessageForNotSelectingCourses();
        logger.info("Check error message for not selecting courses for timetable -> {} is the same as demanded -> {}",
                message, demandedMsgText);
        assertThat(message.equals(demandedMsgText))
                .withFailMessage("Error message is not the same as required")
                .isTrue();
    }

    @When("Refresh the page")
    public void refreshThePage() {
        timetablePage.refreshPage();
    }

    @Then("Check error message for wrong dates in timetable")
    public void checkErrorMessageForWrongDatesInTimetable() {
        logger.info("Check error messages for leaving dates blank in timetable");
        assertThat(timetablePage.checkErrorMessagesOfBlankDates())
                .withFailMessage("Error messages for leaving dates blank is incorrect")
                .isTrue();
    }

    @And("Click on 'X' button of the selected course")
    public void clickOnXButtonOfTheSelectedCourse() {
        logger.info("Click on xButton of course in timetable page");
        timetablePage.clickOnXButtonOfLastSelectedCourse();
    }

    @Then("Click on {} button in popup")
    public void clickOnButtonInPopup(String button) {
        logger.info("Click on {} button in popup", button);
        timetablePopup.clickOnButton(button);
    }

    @And("Check academic course is {} displayed in the timetable list")
    public void checkAcademicCourseIsDisplayedInTheTimetableList(String message) {
        logger.info("Check academic course is {} displayed in the timetable list", message);
        if (message.equals("not")) {
            assertThat(timetablePage.checkListSizeOfTimetable(SharedTestData.getValueOfItem()))
                    .withFailMessage("List size has not been changed, but it should be")
                    .isFalse();
        } else {
            assertThat(timetablePage.checkListSizeOfTimetable(SharedTestData.getValueOfItem()))
                    .withFailMessage("List size has changed, but it shouldn't be")
                    .isTrue();
        }
    }

    @When("Click on {} button in timetable page")
    public void clickOnButtonButton(String button) {
        timetablePage.clickOnButton(button);
    }

    @Then("Delete timetable for {} class from DB")
    public void deleteTimetableFromDB(String academicClass) {
        logger.info("Delete timetable for {} class from DB", academicClass);
        dbHelper.deleteTimetableByAcademicClassId(academicClass);
    }

    @When("Save day of week - {}")
    public void saveDayOfWeek(String dayOfWeek) {
        SharedTestData.setValueOfItem(dayOfWeek);
    }
}
