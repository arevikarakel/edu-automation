package com.epam.pages.main;

import com.epam.pages.common.CommonPage;

public class SubjectPage extends CommonPage {

    public void clickOnAddButton() {
        uiHelper.clickOnWebElement(createButton);
    }

    public void clickOnItemFromTheList(String itemName) {
        logger.info("Click on item from the list using item name and index");
        uiHelper.clickOnWebElement(listItemsHref.get(getIndexOfItemFromList(itemName)));
    }

    private int getIndexOfItemFromList(String subjectName) {
        logger.info("Get index of subject from the list");
        switch (subjectName) {
            case "first":
                return 0;
            case "second":
                return 1;
            case "third":
                return 2;
        }
        for (int i = 0; i < listItemsHref.size(); i++) {
            if (listItemsHref.get(i).getText().equals(subjectName)) {
                return i;
            }
        }
        return -1;
    }
}
