package com.epam.pages.main;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import com.epam.pages.common.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.List;

public class TimetablePage extends CommonPage {

    @FindBy(className = "url-path")
    private WebElement urlPath;
    @FindBy(id = "create-btn")
    private WebElement createTimetableButton;
    @FindBy(className = "timetable")
    private WebElement timetable;
    @FindBy(id = "startDate")
    private WebElement startDate;
    @FindBy(id = "endDate")
    private WebElement endDate;
    @FindBy(id = "create")
    private WebElement createButton;
    @FindBy(className = "button")
    private WebElement editButton;
    @FindBy(className = "successMsg")
    private WebElement successMsg;
    @FindBy(className = "date_error")
    private WebElement noCourseSelectionError;
    @FindBy(xpath = "//td[contains(@class, 'disabled') and not(contains(@class, 'other-month'))]")
    private List<WebElement> disabledDates;
    @FindBy(className = "error")
    private List<WebElement> dateErrorList;
    @FindBy(xpath = "//div[@class='dayHeader']/a")
    private List<WebElement> daysOfWeek;
    @FindBy(xpath = "//div[@class='add']/img[@class='plus']")
    private List<WebElement> plusButtonsForEveryDayOfWeek;
    @FindBy(xpath = "//label[@class='close-btn']")
    private WebElement closeButtonOfSelectedCourses;
    @FindBy(className = "ui-datepicker-year")
    private WebElement yearToSelect;

    public boolean checkAllElementsArePresentInTimetableSection() {
        logger.info("Check elements are present in timetable section - create button, url-path");
        return uiHelper.checkElementsAreDisplayed(
                createTimetableButton,
                urlPath
        );
    }

    public boolean checkAllElementsArePresentInOpenedTimetablePage() {
        logger.info("Check elements are present in timetable page - timetable, create button, url-path, start and" +
                " and dates, '+' under each column for adding course, table with columns for every day of week");
        return uiHelper.checkElementsAreDisplayed(
                timetable,
                urlPath,
                startDate,
                endDate,
                createButton
        ) &&
                uiHelper.checkElementsAreDisplayed(daysOfWeek)
                && uiHelper.checkElementsAreDisplayed(plusButtonsForEveryDayOfWeek);
    }

    public void clickOnPlusButton() {
        logger.info("Click on plus button of certain day of week");
        String plus = String.format("//div[@id='%s']//following-sibling::div/img[@class='plus']",
                SharedTestData.getValueOfItem().toLowerCase());
        uiHelper.clickOnWebElement(driver.findElement(By.xpath(plus)));
    }

    private List<WebElement> getListOfCoursesForDayOfWeek(String dayOfWeek) {
        logger.info("Get list of courses for {} day of week", dayOfWeek);
        String xpath = String.format("//div[@id='%s']//following-sibling::div//div[@class='single_item']//p[@class='title']",
                dayOfWeek.toLowerCase());
        return driver.findElements(By.xpath(xpath));
    }

    public void saveListSizeAndDayOfWeek(String dayOfWeek) {
        logger.info("Save list size and day of the week to shared test data");
        SharedTestData.setListSize(getListOfCoursesForDayOfWeek(dayOfWeek).size());
        SharedTestData.setValueOfItem(dayOfWeek.toLowerCase());
    }

    public boolean checkListSizeOfTimetable(String dayOfWeek) {
        logger.info("Check whether list size for {} day of week has changed", dayOfWeek);
        return SharedTestData.getListSize() == getListOfCoursesForDayOfWeek(dayOfWeek).size();
    }

    public boolean checkCourseIsNotAddedInTimetableForDayOfWeek() {
        logger.info("Check course is not added in the timetable for certain day of week");
        return SharedTestData.getListSize()
                <= getListOfCoursesForDayOfWeek(SharedTestData.getValueOfItem()).size();
    }

    public void saveSelectedCourseValueByGivenName(String courseName) {
        logger.info("Save selected course name to shared test data {}", courseName);
        SharedTestData.setLastCreatedItemName(courseName);
    }

    public boolean checkCourseIsAddedInTimetablePage() {
        logger.info("Check course is added in timetable page at the end of the list");
        List<WebElement> listOfCoursesForDayOfWeek = getListOfCoursesForDayOfWeek(SharedTestData.getValueOfItem());
        return listOfCoursesForDayOfWeek
                .get(listOfCoursesForDayOfWeek.size() - 1)
                .getText()
                .equals(SharedTestData.getSelectedValueOfPopup());
    }
    public void clickOnButton(String button) {
        switch (button) {
            case "Edit":
            case "Save":
                uiHelper.clickOnWebElement(editButton);
                break;
            case "Create Timetable":
                uiHelper.clickOnWebElement(createTimetableButton);
                break;
            case "Create":
                uiHelper.clickOnWebElement(createButton);
                break;
        }
    }

    public String getSuccessMsgText() {
        logger.info("Get success message text from popup");
        return successMsg.getText();
    }

    public String getErrorMsgTextForNotSelectingCourse() {
        logger.info("Get error message text for not selecting courses in timetable");
        return noCourseSelectionError.getText();
    }

    public boolean checkErrorMessagesOfBlankDates() {
        logger.info("Check error messages of blank dates");
        return dateErrorList.stream().allMatch(element -> element.getText()
                .equals(ErrorMessagesProvider.getErrMessageOfBlankDates()));
    }

    public void refreshPage() {
        logger.info("Refresh the page");
        driver.navigate().refresh();
    }



    public void clickOnXButtonOfLastSelectedCourse() {
        logger.info("Click on xButton of last selected course");
        uiHelper.clickOnWebElement(closeButtonOfSelectedCourses);
    }
}
