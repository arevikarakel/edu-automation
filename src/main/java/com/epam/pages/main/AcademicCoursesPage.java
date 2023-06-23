package com.epam.pages.main;

import com.epam.pages.common.CommonPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AcademicCoursesPage extends CommonPage {

    @FindBy(xpath = "//div[@class='url-path']/a")
    private List<WebElement> topBreadcrumbsElements;
    @FindBy(xpath = " //div[@id='sidebar2']/a[text()='Teachers']")
    private WebElement teachersSection;
    @FindBy(xpath = " //div[@id='sidebar2']/a[text()='Classes']")
    private WebElement classesSection;

    public boolean checkAllElementsAreDisplayedInSection() {
        return uiHelper.checkElementsAreDisplayed(topBreadcrumbsElements)
                && uiHelper.checkElementsAreDisplayed(
                createButton,
                list,
                teachersSection,
                classesSection);
    }


}
