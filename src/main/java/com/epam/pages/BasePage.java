package com.epam.pages;

import com.epam.core.driver.Driver;
import com.epam.helpers.UiHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final WebDriver driver;
    protected final UiHelper uiHelper = new UiHelper();

    public BasePage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void goToPage() {
        logger.info("Go to page - {}", pageUrl());
        driver.get(pageUrl());
    }

    protected String pageUrl() {
        return "";
    }
}
