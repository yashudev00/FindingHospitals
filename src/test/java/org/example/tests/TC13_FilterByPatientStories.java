package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.DoctorsHomePage;
import org.example.pages.FindDoctorsPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TC13_FilterByPatientStories extends BaseClass {

    @Test
    public void filterByPatientStories() {

        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Navigating to Mumbai Doctors page");
        new DoctorsHomePage(driver).navigateToMumbaiDoctors();

        FindDoctorsPage doctorsPage = new FindDoctorsPage(driver);

        log.info("Applying filters: Gender Male, Patient Stories 20+");
        doctorsPage.selectGenderMale();
        doctorsPage.selectPatientStories20Plus();

        wait.until(ExpectedConditions.visibilityOf(
                doctorsPage.getResultElement()
        ));

        log.info("Validating result count text is present");
        softAssert.assertNotNull(
                doctorsPage.getResultCountText(),
                "Result count text is null"
        );

        softAssert.assertAll();
    }
}