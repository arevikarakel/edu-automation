package com.epam.pages.popup;

import com.epam.helpers.ErrorMessagesProvider;
import com.epam.helpers.SharedTestData;
import com.epam.helpers.UserDataProvider;
import com.epam.pages.common.CommonPopup;
import com.epam.pages.main.SuperAdminPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreatePopup extends CommonPopup {

    @FindBy(id = "name")
    protected WebElement nameInput;
    @FindBy(id = "surname")
    protected WebElement surnameInput;
    @FindBy(id = "email")
    protected WebElement emailInput;
    @FindBy(id = "password")
    protected WebElement passwordInput;
    @FindBy(id = "btn")
    protected WebElement generatePasswordButton;
    @FindBy(xpath = "//*[@id='email']/following-sibling::div/p")
    protected WebElement emailInvalidAndExistedErrorMessage;
    @FindBy(xpath = "//input[@type='text']/following-sibling::div[@class='error']")
    protected List<WebElement> errorMessagesOfBlankInputFields;
    @FindBy(xpath = "//input[@type='text' and not(@readonly) and not(@type='hidden')]/following-sibling::div[@class='error' and not(preceding-sibling::input[@id='date']) and not(preceding-sibling::*[self::select])]")
    protected List<WebElement> errorMessagesOfMoreSymbols;
    @FindBy(id = "popup-container")
    protected WebElement popupWindow;
    @FindBy(xpath = "//input[not(@readonly) and not(@type='hidden') and not(@type='file')]")
    protected List<WebElement> inputFields;

    public void fillName(String name) {
        logger.info("Fill name {}", name);
        uiHelper.sendKeys(nameInput, name);
    }

    public void fillExistedName() {
        String name = UserDataProvider.getExistedName();
        logger.info("Fill existed name {}", name);
        fillName(name);
    }

    public void fillExistedAdminNameAndSurname() {
        String[] nameAndSurname = new SuperAdminPage().getNameAndSurnameOfLastCreatedAdmin().split(" ");
        String name = nameAndSurname[0];
        String surname = nameAndSurname[1];
        logger.info("Fill existed name {} and surname {}", name, surname);
        fillName(name);
        fillSurname(surname);
    }

    public void fillSurname(String surname) {
        logger.info("Fill surname {}", surname);
        uiHelper.sendKeys(surnameInput, surname);
    }

    public void fillExistedSurname() {
        String surname = UserDataProvider.getExistedSurname();
        logger.info("Fill existed surname {}", surname);
        fillSurname(surname);
    }

    public void fillEmail(String email) {
        logger.info("Fill email {}", email);
        uiHelper.sendKeys(emailInput, email);
    }

    public void fillNonExistedEmail() {
        String email = String.format("%s@gmail.com", System.currentTimeMillis());
        logger.info("Fill non-existed email {}", email);
        fillEmail(email);
    }

    public void fillExistedEmail() {
        String existedEmail = UserDataProvider.getExistedEmail();
        logger.info("Fill existed email - {}", existedEmail);
        uiHelper.sendKeys(emailInput, existedEmail);
    }

    public void fillInputFieldsWithMoreSymbols() {
        String generatedString = RandomStringUtils.random(51, true, true);
        logger.info("50 symbols in name field are {}", generatedString);
        inputFields
                .forEach(fields -> uiHelper.sendKeys(fields, generatedString));
    }

    public void clearInputField(String field) {
        logger.info("Clear {} input field", field);
        switch (field) {
            case "name":
                nameInput.clear();
                break;
            case "surname":
                surnameInput.clear();
                break;
            case "email":
                emailInput.clear();
                break;
            case "all":
                inputFields.forEach(WebElement::clear);
                break;
        }
    }

    public void fillInputFieldsWithNotAllowedSymbols() {
        String generatedString = RandomStringUtils.random(10, 33, 39, false, false);
        logger.info("Fill text field with not allowed symbols {}", generatedString);
        inputFields
                .forEach(fields -> uiHelper.sendKeys(fields, generatedString));
    }

    public void fillNameSurnameEmail() {
        uiHelper.sendKeys(nameInput, UserDataProvider.getValidName());
        uiHelper.sendKeys(surnameInput, UserDataProvider.getValidSurname());
        fillNonExistedEmail();
    }

    public void clickOnGeneratePasswordButton() {
        uiHelper.clickOnWebElement(generatePasswordButton);
    }

    public void saveNameAndSurnameValue() {
        SharedTestData.setNameField(nameInput.getDomProperty("value"));
        SharedTestData.setSurnameField(surnameInput.getDomProperty("value"));
    }

    public void saveEmailValue() {
        SharedTestData.setLastEmail(emailInput.getDomProperty("value"));
    }

    public void savePasswordValue() {
        SharedTestData.setLastGeneratedPassword(passwordInput.getDomProperty("value"));
    }

    public void fillInputFieldsWithSpaces() {
        logger.info("Fill in all required fields only spaces");
        inputFields
                .forEach(fields -> uiHelper.sendKeys(fields, "        "));
    }

    public boolean checkAllFieldsArePresent() {
        logger.info("Check fields name, surname, email, password, save button," +
                "generate password button, X button are displayed in create popup");
        return uiHelper.checkElementsAreDisplayed(
                title,
                nameInput,
                surnameInput,
                emailInput,
                passwordInput,
                saveButton,
                generatePasswordButton,
                xButton
        );
    }

    public boolean checkAllFieldsArePresentOnProfilePage() {
        logger.info("Check fields name, surname, email, save button," +
                "X button are displayed in edit popup of profile page");
        return uiHelper.checkElementsAreDisplayed(
                title,
                nameInput,
                surnameInput,
                emailInput,
                saveButton,
                xButton
        );
    }

    public boolean checkAllInputFieldsAreEmpty() {
        logger.info("Check fields name, surname, email, password are displayed in create popup");
        return uiHelper.checkElementsAreEmpty(
                nameInput,
                surnameInput,
                emailInput,
                passwordInput
        );
    }

    public boolean checkGeneratedPasswordIsFilled() {
        logger.info("Check password field is filled");
        return !passwordInput.getDomProperty("value").isEmpty();
    }

    public boolean checkGeneratePasswordButtonIsEnabled() {
        logger.info("Check 'Generate password' button is active");
        return generatePasswordButton.isEnabled();
    }

    public boolean checkGeneratedPasswordStructure() {
        String generatedPassword = passwordInput.getDomProperty("value");
        logger.info("Check {} generated password structure", generatedPassword);
        return generatedPassword
                .matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[()`~@$?!\"'^#*:.,;<>%-_+=|/{}&])[A-Za-z\\d()`~@$?!\"'^#*:.,;<>%-_+=|/{}&]{9,50}");
    }

    public boolean checkThePasswordFieldIsDisabled() {
        logger.info("Check the password field is readonly");
        return Boolean.parseBoolean(passwordInput.getAttribute("readonly"));
    }

    public boolean passwordIsChanged() {
        logger.info("Check password is changed after generating a new password");
        return !passwordInput
                .getDomProperty("value")
                .equals(SharedTestData.getLastGeneratedPassword());
    }

    public boolean checkErrorMessagesOfBlankInputFields() {
        logger.info("Check error messages of blank input fields");
        return errorMessagesOfBlankInputFields
                .stream()
                .allMatch(errMessage -> errMessage.getText()
                        .equals(ErrorMessagesProvider.getBlankInputFieldsErrMessage()));
    }

    public boolean checkErrorMessagesOfMoreSymbolsFilledInputFields() {
        logger.info("Check error messages for inputting more than 50 symbols");
        return errorMessagesOfMoreSymbols
                .stream()
                .allMatch(errMessage -> errMessage.getText()
                        .equals(ErrorMessagesProvider.getMoreThan50SymbolsErrMessage()));
    }

    public boolean checkErrorMessagesOfNotAllowedSymbolsFilledInputFields() {
        logger.info("Check error messages for inputting not allowed symbols");
        return errorMessagesOfMoreSymbols
                .stream()
                .allMatch(errMessage -> errMessage.getText()
                        .equals(ErrorMessagesProvider.getNotAllowedSymbolsErrMessage()));
    }

    public boolean checkBlankInputFieldsErrorMessagesAreNotDisplayed() {
        logger.info("Check error messages are not displayed");
        return errorMessagesOfBlankInputFields.isEmpty();
    }

    public boolean popupIsClosed() {
        return !popupWindow.isDisplayed();
    }

    public String getEmailFieldErrorMessage() {
        logger.info("Get text of error message under email field - {}", emailInvalidAndExistedErrorMessage.getText());
        return emailInvalidAndExistedErrorMessage.getText();
    }
}
