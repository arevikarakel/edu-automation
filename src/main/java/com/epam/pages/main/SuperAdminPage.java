package com.epam.pages.main;

import com.epam.pages.common.CommonPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuperAdminPage extends CommonPage {

    @FindBy(linkText = "Admins")
    protected WebElement adminsSection;

    public boolean checkAllElementsArePresent() {
        logger.info("Check elements - list, role name, admins section, settings serction and create button are present");
        return uiHelper.checkElementsAreDisplayed(
                list,
                roleName,
                adminsSection,
                settingsSection,
                createButton
        );
    }
}
