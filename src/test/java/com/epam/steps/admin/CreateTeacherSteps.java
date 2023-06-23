package com.epam.steps.admin;

import com.epam.steps.BaseSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTeacherSteps extends BaseSteps {

    @Then("Check the teacher password is hashed in the DB")
    public void checkPasswordIsHashedInTheDB() {
        assertThat(dbHelper.isTeacherPasswordHashed())
                .withFailMessage("Password was not hashed in the DB.")
                .isTrue();
    }
}
