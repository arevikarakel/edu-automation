package com.epam.pages.profile;

import com.epam.helpers.SharedTestData;
import com.epam.pages.popup.CreatePopup;

public class UserProfilePopup extends CreatePopup {

    public void saveNameAndSurnameValueFromDomProperty() {
        SharedTestData.setValueOfItem(nameInput.getDomProperty("value") + " "
        + surnameInput.getDomProperty("value"));
        logger.info("Save name and surname of the user from DOM property to Shared test data - {}", SharedTestData.getValueOfItem());
    }
}
