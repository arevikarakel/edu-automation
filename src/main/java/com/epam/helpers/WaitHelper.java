package com.epam.helpers;

import com.epam.core.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final WebDriver driver = Driver.getDriver();
    private final Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void waitElementToBeClickable(final WebElement target) {
        wait.until(ExpectedConditions.elementToBeClickable(target));
    }

    public void waitElementToBeVisible(final WebElement target) {
        wait.until(ExpectedConditions.visibilityOf(target));
    }

    public boolean waitAlertToBePresent() {
       return wait.until(ExpectedConditions.alertIsPresent()) != null;
    }
}
