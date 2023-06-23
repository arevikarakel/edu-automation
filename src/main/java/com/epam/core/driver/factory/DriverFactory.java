package com.epam.core.driver.factory;

import com.epam.core.driver.providers.ChromeDriverProvider;
import com.epam.core.driver.providers.FirefoxDriverProvider;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static final FirefoxDriverProvider firefoxDriverProvider = new FirefoxDriverProvider();
    private static final ChromeDriverProvider chromeDriverProvider = new ChromeDriverProvider();

    public static WebDriver getDriver(String browserName) {
        if (browserName.equalsIgnoreCase("firefox")) {
            return firefoxDriverProvider.getDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            return chromeDriverProvider.getDriver();
        } else throw new IllegalArgumentException("No driver name found");
    }
}
