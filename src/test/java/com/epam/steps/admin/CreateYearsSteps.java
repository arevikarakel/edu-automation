package com.epam.steps.admin;

import com.epam.helpers.SharedTestData;
import com.epam.pages.main.AdminPage;
import com.epam.pages.popup.AcademicYearsAndVacationPopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateYearsSteps extends BaseSteps {

    private AcademicYearsAndVacationPopup yearsAndVacationPopup;
    private AdminPage adminPage;

    @Before
    public void initPages() {
        yearsAndVacationPopup = new AcademicYearsAndVacationPopup();
        adminPage = new AdminPage();
    }

    @Then("Check date fields are empty in create popup")
    public void checkDateFieldsAreEmptyInCreatePopup() {
        assertThat(yearsAndVacationPopup.areDatesSelected())
                .withFailMessage("Dates are selected, but they shouldn't be.")
                .isFalse();
    }

    @And("New academic year is not added to the DB")
    public void newAcademicYearIsNotAddedToTheDB() {
        assertThat(dbHelper.isAcademicYearAddedToTheDB(SharedTestData.getStartDate(), SharedTestData.getEndDate()))
                .withFailMessage("Academic year is added to the db, but it should not be.")
                .isFalse();
    }

    @When("Fill {int} {} {int} date in {} field")
    public void fillDateInFieldAndSaveDateValue(int day, String month, int year, String field) {
        yearsAndVacationPopup.selectDate(day, month, year, field);
    }

    @And("Check end days can't be more than 12 months after today's date")
    public void checkEndDaysCanTBeMoreThanMonthsAfterTodaySDate() {
        logger.info("Check end days can't be more than 12 months after today's date");
        assertThat(yearsAndVacationPopup.checkEndDaysCanTBeMoreThan12MonthsAfterTodaySDate())
                .withFailMessage("You can select date after 12 months from today, " +
                        "but it should have been limited by frontend")
                .isTrue();
    }

    @And("Save date values")
    public void saveDateValues() {
        yearsAndVacationPopup.saveStartDateValue();
        yearsAndVacationPopup.saveEndDateValue();
    }

    @Then("Check that you can't select {} date before today - the day of selection")
    public void checkThatYouCanTSelectDateBeforeTodayTheDayOfSelection(String date) {
        assertThat(yearsAndVacationPopup.isDateBeforeTodayEnabled(date))
                .withFailMessage("Days before today are not disabled.")
                .isTrue();
    }


    @When("Fill today's date in start and end date fields")
    public void fillTodaySDateInStartAndEndDateFields() {
        yearsAndVacationPopup.fillTodayDateInStartAndEndDateFields();
    }

    @Then("Check error message of wrong selected dates")
    public void checkErrorMessageOfWrongSelectedDates() {
        assertThat(yearsAndVacationPopup.checkErrorMessageForWrongSelectedDates())
                .withFailMessage("Error message for wrong selected dates was not shown, but it should be")
                .isTrue();
    }
    @Then("Check that {int} year is greater from start date by more than ten years")
    public void checkThatYearIsGraterFromNowByMoreThanTenYears(int year) {
        assertThat(yearsAndVacationPopup.isYearGraterThan10Years(year))
                .withFailMessage("Year is less than 10 years from the moment of selection")
                .isTrue();
    }

    @And("Save start date values")
    public void saveStartDateValues() {
        yearsAndVacationPopup.saveStartDateValue();
    }

    @Then("Check error message for filling less than 30 days")
    public void checkErrorMessageForFillingLessThanDays() {
        assertThat(yearsAndVacationPopup.checkErrorMessageForForFillingLessThan30Days())
                .withFailMessage("end date - start date > 30 but should be less")
                .isTrue();
    }

    @And("New academic year is added to the DB")
    public void newAcademicYearIsAddedToTheDB() {
        assertThat(dbHelper.isAcademicYearAddedToTheDB(SharedTestData.getStartDate(), SharedTestData.getEndDate()))
                .withFailMessage("Academic year is not added to the db, but it should be.")
                .isTrue();
    }

    @Then("Check dates are displayed in the list")
    public void checkDatesAreDisplayedInTheList() {
        assertThat(adminPage.checkAcademicYearIsDisplayedInTheList())
                .withFailMessage("Academic year is not displayed in the list")
                .isTrue();
    }

    @And("Check that {int} year doesn't exist in the list of selection")
    public void checkThatYearDoesnTExistInTheListOfSelection(int year) {
        assertThat(yearsAndVacationPopup.checkIfYearIsPresentInTheSelectList(year))
                .withFailMessage("Year is present in the list, but it should not be.")
                .isFalse();
    }

    @And("New Vacation is not added to the DB")
    public void newVacationIsNotAddedToTheDB() {
        assertThat(dbHelper.isVacationAddedToTheDB(SharedTestData.getStartDate(), SharedTestData.getEndDate()))
                .withFailMessage("Vacation is added to the db, but it should not be.")
                .isFalse();
    }

    @And("New Vacation is added to the DB")
    public void newVacationIsAddedToTheDB() {
        assertThat(dbHelper.isVacationAddedToTheDB(SharedTestData.getStartDate(), SharedTestData.getEndDate()))
                .withFailMessage("Vacation is not added to the db, but it should be.")
                .isTrue();
    }

    @Then("Check vacation is displayed in the list")
    public void checkVacationIsDisplayedInTheList() {
        assertThat(adminPage.checkVacationIsDisplayedInTheList())
                .withFailMessage("Vacation is not displayed in the list")
                .isTrue();
    }
}
