package com.epam.core.driver;

import com.epam.config.PropertiesReader;
import com.epam.core.driver.factory.DriverFactory;
import org.openqa.selenium.WebDriver;

public class Driver {

    private static final String browserName = PropertiesReader
            .getInstance("config.properties")
            .getProperty("browser");

    public static WebDriver getDriver() {
        return DriverFactory.getDriver(browserName);
    }

    public static void quitDriver() {
        getDriver().quit();
    }
}
