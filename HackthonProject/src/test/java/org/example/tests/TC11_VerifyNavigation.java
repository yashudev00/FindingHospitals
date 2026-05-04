package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.DoctorsHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC11_VerifyNavigation extends BaseClass {

    @Test
    public void verifyNavigation() {

        SoftAssert softAssert = new SoftAssert();

        log.info("Navigating to Mumbai doctors page");
        DoctorsHomePage doctorsHome = new DoctorsHomePage(driver);
        doctorsHome.navigateToMumbaiDoctors();

        log.info("Verifying URL contains mumbai/doctors");
        softAssert.assertTrue(
                driver.getCurrentUrl().contains("mumbai/doctors"),
                "Navigation to Mumbai doctors failed"
        );

        softAssert.assertAll();
    }
}
