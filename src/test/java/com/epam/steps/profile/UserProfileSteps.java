package com.epam.steps.profile;

import com.epam.pages.profile.UserProfile;
import com.epam.pages.profile.UserProfilePopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class UserProfileSteps extends BaseSteps {

    private UserProfile userProfile;
    private UserProfilePopup userProfilePopup;

    @Before
    public void initPages() {
        userProfile = new UserProfile();
        userProfilePopup = new UserProfilePopup();
    }

    @Then("Check all elements are present in {}'s profile section")
    public void checkAllElementsArePresentInProfileSection(String user) {
        logger.info("Check all elements are present in {}'s profile section", user);
        assertThat(userProfile.checkAllElementsArePresentInProfilePage(user))
                .withFailMessage("Not all elements are present on profile section")
                .isTrue();
    }


    @And("Save name and surname {}")
    public void saveNameAndSurname(String text) {
        logger.info("Save name and surname {}", text);
        if (text.contains("DOM")) {
            userProfilePopup.saveNameAndSurnameValueFromDomProperty();
        } else {
            userProfile.saveUserNameAndSurname();
        }
    }

    @And("Check name and surname values {} updated on profile page")
    public void checkNameAndSurnameValuesAreUpdated(String text) {
        logger.info("Check name and surname values {} updated in profile page", text);
            assertThat(userProfile.checkNameAndSurnameAreUpdated())
                    .withFailMessage("Something went wrong during name and surname fields updates.")
                    .isTrue();
    }

    @And("Check updated values are {} updated in the {}'s DB")
    public void checkUpdatedValuesAreAlsoUpdatedInTheDB(String text, String user) {
        logger.info("Check updated values are {} updated in the {}'s DB", text, user);
            assertThat(userProfile.checkPersonalInfoByIdAndRemainingElements(user))
                    .withFailMessage("Something went wrong during updating process of user's personal info" +
                            " and saving it in the DB.")
                    .isTrue();
    }
}
