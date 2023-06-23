package com.epam.pages.common;

import com.epam.helpers.SharedTestData;
import com.epam.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPopup extends BasePage {

    @FindBy(id = "submit")
    protected WebElement saveButton;
    @FindBy(className = "close-btn")
    protected WebElement xButton;
    @FindBy(tagName = "h1")
    protected WebElement title;

    public void clickOnSaveButton() {
        uiHelper.clickOnWebElement(saveButton);
    }

    public void clickOnXButton() {
        uiHelper.clickOnWebElement(xButton);
    }
}
