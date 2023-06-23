package com.epam.pages.main;

import com.epam.helpers.SharedTestData;
import com.epam.pages.common.CommonPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class AcademicClassPage extends CommonPage {

    @FindBy(className = "item")
    private WebElement displayedClassroomTeacher;
    @FindBy(id = "vis-btn")
    private WebElement addClassroomTeacherBtn;
    @FindBy(className = "classroom-show-top")
    private WebElement textForNotHavingClassroomTeacher;
    @FindBy(xpath = "//select[@id='classroomTeacher']/option")
    private List<WebElement> listOfTeachersOptions;

    public boolean checkAllElementsArePresentInSection() {
        logger.info("Check elements are present in section - add button, url-path, list");
        return uiHelper.checkElementsAreDisplayed(
                createButton,
                urlPath,
                list
        );
    }

    public boolean checkAllElementsArePresentInClassroomTeacherSection() {
        logger.info("Check elements are present in classroom teacher section - add button, url-path");
        return uiHelper.checkElementsAreDisplayed(
                addClassroomTeacherBtn,
                urlPath
        );
    }

    public void clickOnAddClassroomTeacherBtn() {
        uiHelper.clickOnWebElement(addClassroomTeacherBtn);
    }

    public String getTextForClassroomTeacher() {
        logger.info("Get text for not having classroom teacher selected");
        return textForNotHavingClassroomTeacher.getText();
    }

    public String getDisplayedClassroomTeacherName() {
        logger.info("Get classroom teacher name that is displayed in the list");
        return displayedClassroomTeacher.getText();
    }

    public boolean checkEditButtonIsDisplayed() {
        return uiHelper.checkElementsAreDisplayed(createButton);
    }

    public void clickOnEditButton() {
        uiHelper.clickOnWebElement(createButton);
    }

    public List<String> listOfOptions() {
        logger.info("Get select options from the list");
        List<String> options = new ArrayList<>();
        for (WebElement listItem : listItems) {
            options.add(listItem.getText());
        }
        return options;
    }

    public void saveClassroomTeacherNameAndSurname() {
        logger.info("Existed classroom teacher name from the list is {}", displayedClassroomTeacher.getText());
        SharedTestData.setNameField(displayedClassroomTeacher.getText());
    }
}
