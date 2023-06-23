package com.epam.pages.profile;

import com.epam.helpers.DBHelper;
import com.epam.helpers.SharedTestData;
import com.epam.jdbc.model.Parent;
import com.epam.jdbc.model.Student;
import com.epam.jdbc.model.Teacher;
import com.epam.pages.common.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfile extends CommonPage {

    private final DBHelper dbHelper = new DBHelper();
    @FindBy(id = "file-input")
    private WebElement imageUpload;
    @FindBy(xpath = "//div[@name='btn-div']/button[@id='submit-image']")
    private WebElement OKButtonSize;
    @FindBy(xpath = "//button[@id='submit-image-format']")
    private WebElement OKButtonFormat;
    @FindBy(className = "delete-img")
    private WebElement deleteImg;
    @FindBy(xpath = "//div[@id='popup-container-image-format']//p[@class='successText']")
    private WebElement wrongImgFormatMSG;
    @FindBy(xpath = "//div[@id='popup-container-image']//p[@class='successText']")
    private WebElement wrongImgSizeMSG;

    public boolean checkAllElementsArePresentInProfilePage(String user) {
        logger.info("Check teacher's profile page contains elements: url-path, edit button, subsections" +
                "and personal information about the teacher.");
        return uiHelper.checkElementsAreDisplayed(
                urlPath,
                imageUpload,
                createButton,
                getSubsectionElementByName("Profile")
        ) && checkPersonalInfoByIdAndRemainingElements(user);
    }

    public boolean checkPersonalInfoByIdAndRemainingElements(String user) {
        logger.info("Get {}'s personal information from UI (name, surname, email) and compare it with DB information", user);
        switch (user) {
            case "teacher":
                return checkTeacherPersonalInfo()
                        && uiHelper.checkElementsAreDisplayed(
                        getSubsectionElementByName("Subjects"),
                        getSubsectionElementByName("Academic Courses"),
                        getSubsectionElementByName("Classes")
                );
            case "parent":
                return checkParentPersonalInfo()
                        && uiHelper.checkElementsAreDisplayed(
                        getSubsectionElementByName("Linked students")
                );
            case "student":
                return checkStudentPersonalInfo()
                        && uiHelper.checkElementsAreDisplayed(
                        getSubsectionElementByName("Journal")
                );
            default:
                throw new IllegalArgumentException("No such user to show personal information. Please check user type");
        }
    }

    public void saveUserNameAndSurname() {
        SharedTestData.setValueOfItem(getProfileInformationByText("Name") +
                " " + getProfileInformationByText("Surname"));
        logger.info("Save name and surname of the user to Shared test data - {}", SharedTestData.getValueOfItem());
    }

    public boolean checkNameAndSurnameAreUpdated() {
        String nameAndSurname = getProfileInformationByText("Name") + " " + getProfileInformationByText("Surname");
        logger.info("Check updated name and surname from profile {} is equal or not to what was saved before {}", nameAndSurname, SharedTestData.getValueOfItem());
        return !nameAndSurname.equals(SharedTestData.getValueOfItem());
    }

    public void uploadImage(String imageName) {
        SharedTestData.setNameField(imageName);
        imageUpload.sendKeys("C:\\Users\\Heghine_Khachatryan\\Desktop\\" + imageName);
    }

    public void clickOnOKButton(String message) {
        if (message.contains("wrong format")) {
            uiHelper.clickOnWebElement(OKButtonFormat);
        } else {
            uiHelper.clickOnWebElement(OKButtonSize);
        }
    }

    public String getWrongImgFormatMSG() {
        logger.info("Get wrong image format message - {}", wrongImgFormatMSG.getText());
        return wrongImgFormatMSG.getText();
    }

    public String getWrongImgSizeMSG() {
        logger.info("Get wrong image size message - {}", wrongImgSizeMSG.getText());
        return wrongImgSizeMSG.getText();
    }

    public boolean checkImageIsUploaded() {
        String src = driver.findElement(By.xpath("//div[@class='container-img']/img")).getDomProperty("src");
        logger.info("Property of image src in DOM is {}. Check if it contains image name {}", src,
                SharedTestData.getNameField());
        return src.contains(SharedTestData.getNameField());
    }

    private boolean checkTeacherPersonalInfo() {
        Teacher teacher = dbHelper.getTeacherByID(getUserIDFromHTMLCode("teachers"));
        logger.info("Teacher name from DB is {}, surname is {}, email is {}", teacher.getName(),
                teacher.getSurname(), teacher.getEmail());
        return teacher.getName().equals(getProfileInformationByText("Name"))
                && teacher.getSurname().equals(getProfileInformationByText("Surname"))
                && teacher.getEmail().equals(getProfileInformationByText("Email"));
    }

    private boolean checkParentPersonalInfo() {
        Parent parent = dbHelper.getParentByID(getUserIDFromHTMLCode("parents"));
        return parent.getName().equals(getProfileInformationByText("Name"))
                && parent.getSurname().equals(getProfileInformationByText("Surname"))
                && parent.getEmail().equals(getProfileInformationByText("Email"));
    }

    private boolean checkStudentPersonalInfo() {
        Student student = dbHelper.getStudentByID(getUserIDFromHTMLCode("students"));
        return student.getName().equals(getProfileInformationByText("Name"))
                && student.getSurname().equals(getProfileInformationByText("Surname"))
                && student.getEmail().equals(getProfileInformationByText("Email"))
                && student.getAddress().equals(getProfileInformationByText("Address"))
                && student.getBloodGroup().equals(getProfileInformationByText("Blood group"))
                && student.getGender().equalsIgnoreCase(getProfileInformationByText("Gender"))
                && student.getBirthDay().toString().equals(getProfileInformationByText("Birth date"));
    }

    private String getProfileInformationByText(String text) {
        logger.info("Get profile information about user {}", text);
        String information = driver.findElement(By.xpath(String.format("//div[@class='personalInformation']/p[contains(text(), '%s')]"
                , text))).getText().split(": ")[1];
        logger.info("{} for user is -> {}", text, information);
        if (text.contains("Blood")) {
            if (information.contains("+")) {
                information = information.replaceAll("\\+", "_PLUS");
            } else if (information.contains("-")) {
                information = information.replaceAll("-", "_MINUS");
            }
        }
        return information;
    }

    private int getUserIDFromHTMLCode(String user) {
        logger.info("Get {} ID from HTML code.", user);
        String href = driver.findElement(By.xpath(String.format("//div[@class='url-path']//a[contains(@href, '/%s/')]", user))).getAttribute("href");
        return Integer.parseInt(String.valueOf(href.split(String.format("/%s/", user))[1].charAt(0)));
    }
}
