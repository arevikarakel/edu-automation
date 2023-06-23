package com.epam.steps.profile;

import com.epam.helpers.MessageProvider;
import com.epam.pages.profile.UserProfile;
import com.epam.pages.profile.UserProfilePopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class UploadImageSteps extends BaseSteps {
    private UserProfile userProfile;
    private UserProfilePopup userProfilePopup;

    @Before
    public void initPages() {
        userProfile = new UserProfile();
        userProfilePopup = new UserProfilePopup();
    }

    @Then("Upload {} image from desktop to 'Image Upload' section")
    public void uploadAnImageFromImageUploadSection(String imageName) {
        logger.info("Upload {} image from 'Image Upload' section", imageName);
        userProfile.uploadImage(imageName);
    }

    @And("Check image is uploaded")
    public void checkImageIsUploaded() {
        logger.info("Check image is uploaded");
        assertThat(userProfile.checkImageIsUploaded())
                .withFailMessage("Image was not uploaded, but should be")
                .isTrue();
    }

    @Then("Check message for uploading images with {}")
    public void checkMessageForUploadingImagesWith(String text) {
        logger.info("Check message for uploading images with {}", text);
        if (text.equals("wrong formats")) {
            assertThat(userProfile.getWrongImgFormatMSG().equals(MessageProvider.getWrongImgFormatMSG()))
                    .withFailMessage("Message for uploading images with wrong formats is incorrect")
                    .isTrue();
        } else if (text.equals("big sizes")) {
            assertThat(userProfile.getWrongImgSizeMSG().equals(MessageProvider.getBigSizeImgMSG()))
                    .withFailMessage("Message for uploading images with big sizes is incorrect")
                    .isTrue();
        }
    }

    @And("Click on 'OK' button and accept {} message")
    public void clickOnOKButton(String message) {
        logger.info("Click on 'OK' button and accept {} message", message);
        userProfile.clickOnOKButton(message);
    }
}
