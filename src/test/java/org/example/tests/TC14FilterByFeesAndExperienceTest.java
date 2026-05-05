package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.DoctorsHomePage;
import org.example.pages.FindDoctorsPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TC14FilterByFeesAndExperienceTest extends BaseClass {

    @Test
    public void filterByFeesAndExperience() {

        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Navigating to Mumbai Doctors page");
        new DoctorsHomePage(driver).navigateToMumbaiDoctors();

        FindDoctorsPage doctorsPage = new FindDoctorsPage(driver);

        log.info("Applying filters: Gender, Patient Stories, Experience, Fees");
        doctorsPage.selectGenderMale();
        doctorsPage.selectPatientStories20Plus();
        doctorsPage.selectExperience5Plus();
        doctorsPage.applyFeesFilter();

        wait.until(ExpectedConditions.visibilityOf(
                doctorsPage.getResultElement()
        ));

        log.info("Validating doctors are shown after filters");
        softAssert.assertFalse(
                doctorsPage.getResultCountText().isEmpty(),
                "No results after applying fees and experience filters"
        );
        softAssert.assertAll();
    }
}