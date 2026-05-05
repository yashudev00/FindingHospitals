package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC07VerifyTopCitiesSectionDisplayedTest extends BaseClass {

    @Test
    public void verifyTopCitiesSectionIsDisplayed() {

        new HomePage(driver, wait).searchHospitalInDefaultCity();
        HospitalSearchPage searchPage = new HospitalSearchPage(driver);

        boolean isDisplayed = searchPage.isTopCitiesSectionDisplayed();

        Assert.assertTrue(
                isDisplayed,
                "Top Cities section is NOT displayed on Hospitals page"
        );

        log.info("Top Cities section is displayed");
    }
}