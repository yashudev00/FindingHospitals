package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalDetailsPage;
import org.example.pages.HospitalSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04NearestHospitalTest extends BaseClass {

    @Test
    public void findNearestEligibleHospital() {

        new HomePage(driver, wait).searchHospitalInDefaultCity();
        HospitalSearchPage searchPage = new HospitalSearchPage(driver);

        String nearestHospital = null;
        int limit = searchPage.getHospitalCount();

        for (int i = 0; i < limit; i++) {
            searchPage.openHospitalByIndex(i);
            HospitalDetailsPage details =
                    new HospitalDetailsPage(driver, wait);

            if (details.isOpen24x7()
                    && details.hasParking()
                    && details.getRating() > 3.5) {

                nearestHospital = details.getHospitalName();
                log.info("Nearest eligible hospital: {}", nearestHospital);
                searchPage.closeAndReturnToList();
                break;
            }
            searchPage.closeAndReturnToList();
        }
        Assert.assertNotNull(nearestHospital,
                "No hospital matched eligibility criteria");
    }
}