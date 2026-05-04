package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.DoctorsHomePage;
import org.example.pages.FindDoctorsPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TC12_FilterByGender extends BaseClass {

    @Test
    public void filterByGenderMale() {

        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Navigating to Mumbai Doctors page");
        DoctorsHomePage doctorsHome = new DoctorsHomePage(driver);
        doctorsHome.navigateToMumbaiDoctors();

        FindDoctorsPage doctorsPage = new FindDoctorsPage(driver);

        log.info("Applying Gender filter: Male");
        doctorsPage.selectGenderMale();

        wait.until(ExpectedConditions.visibilityOf(
                doctorsPage.getResultElement()
        ));

        log.info("Verifying doctors list is shown");
        softAssert.assertTrue(
                doctorsPage.getResultCountText().toLowerCase().contains("doctors"),
                "Doctors not displayed after gender filter"
        );

        softAssert.assertAll();
    }
}