package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalDetailsPage;
import org.example.pages.HospitalSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03_RatingValidationTest extends BaseClass {

    @Test
    public void verifyHospitalRatingAbove35() {

        new HomePage(driver, wait).searchHospitalDirectly();
        HospitalSearchPage searchPage = new HospitalSearchPage(driver);

        boolean found = false;
        int limit = searchPage.getHospitalCount();

        for (int i = 0; i < limit; i++) {
            searchPage.openHospitalByIndex(i);
            HospitalDetailsPage details =
                    new HospitalDetailsPage(driver, wait);

            double rating = details.getRating();
            if (rating > 3.5) {
                log.info("Hospital with rating > 3.5 found: {} | Rating: {}",
                        details.getHospitalName(), rating);
                found = true;
                searchPage.closeAndReturnToList();
                break;
            }
            searchPage.closeAndReturnToList();
        }

        Assert.assertTrue(found,
                "No hospital found with rating greater than 3.5");
    }
}