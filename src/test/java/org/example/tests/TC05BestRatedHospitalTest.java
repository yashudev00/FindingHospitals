package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalDetailsPage;
import org.example.pages.HospitalSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05BestRatedHospitalTest extends BaseClass {

    @Test
    public void findBestRatedEligibleHospital() {

        new HomePage(driver, wait).searchHospitalDirectly();
        HospitalSearchPage searchPage = new HospitalSearchPage(driver);

        String bestHospital = null;
        double maxRating = 0.0;
        int limit = Math.min(searchPage.getHospitalCount(), 5);

        for (int i = 0; i < limit; i++) {
            searchPage.openHospitalByIndex(i);
            HospitalDetailsPage details =
                    new HospitalDetailsPage(driver, wait);

            if (details.isOpen24x7()
                    && details.hasParking()
                    && details.getRating() > 3.5) {

                if (details.getRating() > maxRating) {
                    maxRating = details.getRating();
                    bestHospital = details.getHospitalName();
                }
            }
            searchPage.closeAndReturnToList();
        }
        Assert.assertNotNull(bestHospital,
                "No best rated hospital found");

        log.info("Best rated hospital: {} | Rating: {}",
                bestHospital, maxRating);
    }
}