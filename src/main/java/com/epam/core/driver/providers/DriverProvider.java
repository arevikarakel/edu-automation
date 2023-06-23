package com.epam.core.driver.providers;

import org.openqa.selenium.WebDriver;

public abstract class DriverProvider {

    protected static WebDriver driver;

    public abstract WebDriver getDriver();
}
