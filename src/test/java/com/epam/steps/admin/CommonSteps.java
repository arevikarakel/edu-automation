package com.epam.steps.admin;

import com.epam.pages.main.AdminPage;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps extends BaseSteps {

    private AdminPage adminPage;

    @Before
    public void initPages() {
        adminPage = new AdminPage();
    }

    @When("Select {} section")
    public void selectSection(String section) {
        adminPage.selectSection(section);
    }

    @Then("User is displayed in the list")
    public void userIsCreatedAndDisplayedInTheList() {
        assertThat(adminPage.checkNewUserIsDisplayedOnAdminsSection())
                .withFailMessage("Last created user is not displayed in the list, but it should be.")
                .isTrue();
    }

    @Then("Check user is not added in the DB")
    public void checkUserIsNotAddedInTheDB() {
        logger.info("Check user is not added in the DB");
        assertThat(dbHelper.isUserAddedInTheDB())
                .withFailMessage("Parent wasn't meant to be added in the DB, but was added.")
                .isTrue();
    }

    @Then("Check user is added in the DB")
    public void checkUserIsAddedInTheDB() {
        logger.info("Check user is added in the DB");
        assertThat(dbHelper.isUserAddedInTheDB())
                .withFailMessage("User was meant to be added in the DB, but wasn't added.")
                .isFalse();
    }
}
