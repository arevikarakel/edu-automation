package com.epam.pages.popup;

import com.epam.helpers.SharedTestData;
import com.epam.helpers.WaitHelper;
import com.epam.pages.common.CommonPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TimetablePopup extends CommonPopup {

    private final WaitHelper waitHelper = new WaitHelper();
    @FindBy(id = "academicCourse")
    private WebElement courseDropDown;
    @FindBy(className = "button-save")
    private WebElement saveButton;
    @FindBy(className = "delete-popup-delete-button-save")
    private WebElement deleteButton;
    @FindBy(className = "delete-popup-cancel-button-save")
    private WebElement cancelButton;
    @FindBy(id = "popup-wrapper")
    private WebElement deleteCoursePopup;
    @FindBy(xpath = "//select[@id='academicCourse']/option")
    private List<WebElement> listOfCoursesOptionsInDropDown;

    public boolean checkAllElementsArePresentInAddLessonPopup() {
        logger.info("Check elements are present in 'Add lesson' popup - title, drop-down field, xButton," +
                " save button");
        return uiHelper.checkElementsAreDisplayed(
                title,
                xButton,
                saveButton,
                courseDropDown
        );
    }

    private WebElement getElementByDayOfWeek() {
        String dayOfWeek = SharedTestData.getValueOfItem().toLowerCase();
        logger.info("Find element by day of week {}", dayOfWeek);
        String xpath = String.format("//div[@id='popup-container-%s']//select[@id='academicCourse']",
                dayOfWeek);
        return driver.findElement(By.xpath(xpath));
    }

    public void selectCourseForDayOfWeek() {
        logger.info("Select course from drop-down list");
        new Select(getElementByDayOfWeek()).selectByIndex(1);
    }

    public void clickOnButton(String button) {
        if (button.equals("delete")) {
            uiHelper.clickOnWebElement(deleteButton);
        } else if (button.equals("cancel")) {
            uiHelper.clickOnWebElement(cancelButton);
        }
    }

    public void saveSelectedCourseNameFromSelectField() {
        logger.info("Save selected course name into shared test data");
        SharedTestData.setSelectedValueOfPopup(new Select(getElementByDayOfWeek())
                .getFirstSelectedOption()
                .getText());
    }

    public boolean checkPopupForDayOfWeekIsOpened() {
        String dayOfWeek = SharedTestData.getValueOfItem();
        logger.info("Check popup for {} is opened", dayOfWeek);
        String id = String.format("popup-container-%s", dayOfWeek.toLowerCase());
        return driver.findElement(By.id(id)).isDisplayed();
    }

    public boolean checkItemsAreNotSelected() {
        logger.info("Check if elements are selected");
        return !uiHelper.areElementsSelected(courseDropDown);
    }

    public boolean checkAlertIsPresent() {
        logger.info("Wait for alert to be present");
        return waitHelper.waitAlertToBePresent();
    }

    public void acceptAlert() {
        logger.info("Accept alert and leave the page");
        driver.switchTo().alert().accept();
    }

    public List<String> getListOfSelectionOptionsInAddCoursesPopup() {
        logger.info("Get list of selection options");
        List<String> options = new ArrayList<>();
        listOfCoursesOptionsInDropDown.forEach(element -> options.add(element.getText()));
        return options;
    }

    public boolean listContainsNames(List<String> list) {
        logger.info("Check if list contains another list's options");
        List<String> listOfOptions = getListOfSelectionOptionsInAddCoursesPopup();
        listOfOptions.remove(0);
        return new HashSet<>(listOfOptions).containsAll(list);
    }

    public void clickOnSaveButtonInAddLessonPopup() {
        uiHelper.clickOnWebElement(saveButton);
    }

    public void clickOnSelectCourseInAddLessonPopup() {
        uiHelper.clickOnWebElement(courseDropDown);
    }
}
