package com.epam.pages.popup;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import com.epam.helpers.UserDataProvider;
import com.epam.pages.common.CommonPopup;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AcademicClassPopup extends CommonPopup {
    @FindBy(id = "classNumber")
    private WebElement classNumber;
    @FindBy(xpath = "//input/following-sibling::div[@class='error']")
    private WebElement errorMessagesOfExistedClass;
    @FindBy(id = "course")
    private WebElement academicCourseSelect;
    @FindBy(id = "teacher")
    private WebElement teacherSelect;
    @FindBy(xpath = "//span[@role='combobox']")
    private WebElement studentsBox;
    @FindBy(id = "classroomTeacher")
    private WebElement classroomTeacher;
    public boolean checkUIOfCreatePopupClassesSection() {
        logger.info("Check UI of create popup in academic classes section");
        return uiHelper.checkElementsAreDisplayed(
                classNumber,
                xButton,
                saveButton,
                title
        );
    }

    public boolean checkUIOfAddClassroomTeacherPopupClassesSection() {
        logger.info("Check UI of 'Add/Edit Classroom Teacher' popup in academic classes " +
                "'Classroom Teacher' subsection");
        return uiHelper.checkElementsAreDisplayed(
                classroomTeacher,
                xButton,
                saveButton,
                title
        );
    }

    public boolean checkAllElementsArePresentInNewCoursePopupClassesSection() {
        logger.info("Check elements are present in New Course popup academic classes section - " +
                "title, teachers and courses selects, xButton and save button");
        return uiHelper.checkElementsAreDisplayed(
                title,
                teacherSelect,
                academicCourseSelect,
                xButton,
                saveButton
        );
    }

    public boolean checkAllElementsArePresentInNewStudentsPopupClassesSection() {
        logger.info("Check elements are present in New Students popup academic classes section - " +
                "title, students select, xButton and save button");
        return uiHelper.checkElementsAreDisplayed(
                title,
                xButton,
                saveButton,
                studentsBox
        );
    }

    public void fillExistedAcademicClass() {
        logger.info("Fill existed academic class from list.");
            uiHelper.sendKeys(classNumber, UserDataProvider.getExistedClass());
    }

    public boolean checkExistedClassErrorMessage() {
        logger.info("Get error message of existed class");
        return errorMessagesOfExistedClass.getText()
                .equals(ErrorMessagesProvider.getExistedAcademicClassErrMessage());
    }

    public void fillAcademicClass() {
        String generateClass = RandomStringUtils.random(15,true,true);
        logger.info("Fill academic class with value - {}", generateClass);
        uiHelper.sendKeys(classNumber, generateClass);
    }

    public void saveAcademicClassValue() {
        SharedTestData.setValueOfItem(classNumber.getDomProperty("value"));
    }
}
