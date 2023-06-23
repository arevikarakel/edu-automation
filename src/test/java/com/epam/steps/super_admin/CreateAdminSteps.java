package com.epam.steps.super_admin;

import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.CreatePopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAdminSteps extends BaseSteps {
    private AdminPage adminPage;
    private CreatePopup createPopup;

    @Before
    public void initPages() {
        adminPage = new AdminPage();
        createPopup = new CreatePopup();
    }
    @Then("Check the admin password is hashed in the DB")
    public void checkPasswordIsHashedInTheDB() {
        assertThat(dbHelper.isAdminPasswordHashed())
                .withFailMessage("Password is not hashed in DB")
                .isTrue();
    }

    @And("User is displayed in the list on super admin page")
    public void userIsDisplayedInTheListOnSuperAdminPage() {
        assertThat(adminPage.checkNewAdminIsDisplayedOnSuperAdminPage())
                .withFailMessage("Last created admin is not displayed in the list, but it should be.")
                .isTrue();
    }

    @Given("Fill existed admin's name and surname")
    public void fillExistedAdminNameAndSurname() {
        createPopup.fillExistedAdminNameAndSurname();
    }
}
