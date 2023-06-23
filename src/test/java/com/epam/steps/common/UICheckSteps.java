package com.epam.steps.common;

import com.epam.pages.main.*;
import com.epam.pages.popup.AcademicClassPopup;
import com.epam.pages.popup.AcademicCoursePopup;
import com.epam.pages.popup.AcademicYearsAndVacationPopup;
import com.epam.steps.BaseSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class UICheckSteps extends BaseSteps {

    private LoginPage loginPage;
    private SuperAdminPage superAdminPage;
    private AdminPage adminPage;
    private AcademicYearsAndVacationPopup yearsAndVacationPopup;
    private AcademicCoursesPage coursesPage;
    private AcademicCoursePopup coursesPopup;
    private AcademicClassPage academicClassPage;
    private AcademicClassPopup academicClassPopup;

    @Before
    public void initPages() {
        loginPage = new LoginPage();
        superAdminPage = new SuperAdminPage();
        adminPage = new AdminPage();
        yearsAndVacationPopup = new AcademicYearsAndVacationPopup();
        coursesPage = new AcademicCoursesPage();
        coursesPopup = new AcademicCoursePopup();
        academicClassPage = new AcademicClassPage();
        academicClassPopup = new AcademicClassPopup();
    }

    @Then("Check all elements are present in login page")
    public void checkAllElementsArePresentInLoginPage() {
        assertThat(loginPage.checkAllElementsArePresent())
                .withFailMessage("All elements are present in login page")
                .isTrue();
    }

    @Then("Check all elements are present in super admin page")
    public void checkAllElementsArePresentInSuperAdminPage() {
        assertThat(superAdminPage.checkAllElementsArePresent())
                .withFailMessage("All elements are not present in super admin page.")
                .isTrue();
    }

    @Then("Check all elements are present on admin page")
    public void seeAllElementsArePresentOnAdminPage() {
        assertThat(adminPage.checkAllElementsArePresent())
                .withFailMessage("Elements in admin page are not present")
                .isTrue();
    }

    @Then("Check all elements are present on the chosen section")
    public void checkAllElementsArePresentInTheSection() {
        assertThat(adminPage.checkUIOfSection())
                .withFailMessage("Elements are not present in selected section")
                .isTrue();
    }

    @Then("Check all fields are present in create popup years section")
    public void checkAllFieldsArePresentInCreatePopupYearsSection() {
        assertThat(yearsAndVacationPopup.checkUIOfCreatePopupYearsSection())
                .withFailMessage("All required elements in years section are not displayed")
                .isTrue();
    }

    @Then("Check all elements are present in chosen section of academic course")
    public void checkAllElementsArePresentInClassesSectionOfAcademicCourse() {
        assertThat(coursesPage.checkAllElementsAreDisplayedInSection())
                .withFailMessage("Not all elements are present in chosen section of academic course")
                .isTrue();
    }

    @Then("Check all elements are present in 'Add Class' popup Classes section")
    public void checkAllElementsArePresentInAddClassPopupClassesSection() {
        assertThat(coursesPopup.checkAllElementsArePresentInAddClassPopupClassesSection())
                .withFailMessage("All required elements in 'Add Class' popup Classes section are not displayed")
                .isTrue();
    }

    @And("Check all elements are present in 'Add Teacher' popup Teachers section")
    public void checkAllElementsArePresentInAddClassPopupTeachersSection() {
        assertThat(coursesPopup.checkAllElementsArePresentInAddTeachersPopupClassesSection())
                .withFailMessage("All required elements in 'Add Teacher' popup Classes section are not displayed")
                .isTrue();
    }

    @Then("Check all elements are present in chosen subsection of academic class")
    public void checkAllElementsArePresentInCoursesSectionOfAcademicClass() {
        assertThat(academicClassPage.checkAllElementsArePresentInSection())
                .withFailMessage("Not all elements are displayed")
                .isTrue();
    }

    @And("Check all elements are present in 'New Course' popup Classes section")
    public void checkAllElementsArePresentInNewCoursePopupClassesSection() {
        assertThat(academicClassPopup.checkAllElementsArePresentInNewCoursePopupClassesSection())
                .withFailMessage("All required elements in 'New Courses' popup Classes section are not displayed")
                .isTrue();
    }

    @And("Check all elements are present in 'New Students' popup Classes section")
    public void checkAllElementsArePresentInNewStudentsPopupClassesSection() {
        assertThat(academicClassPopup.checkAllElementsArePresentInNewStudentsPopupClassesSection())
                .withFailMessage("Not all elements are displayed")
                .isTrue();
    }
}
