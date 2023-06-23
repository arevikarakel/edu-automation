package com.epam.steps.admin;

import com.epam.steps.BaseSteps;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateParentSteps extends BaseSteps {

    @Then("Check the parent password is hashed in the DB")
    public void checkTheParentPasswordIsHashedInTheDB() {
        assertThat(dbHelper.isParentPasswordHashed())
                .withFailMessage("Password was not hashed in the DB.")
                .isTrue();
    }
}
