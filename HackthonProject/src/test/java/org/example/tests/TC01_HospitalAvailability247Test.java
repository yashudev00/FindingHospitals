package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalDetailsPage;
import org.example.pages.HospitalSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC01_HospitalAvailability247Test extends BaseClass {

    @Test
    public void verifyFirstHospitalOpen24x7() {

        new HomePage(driver, wait).searchHospitalDirectly();
        HospitalSearchPage searchPage = new HospitalSearchPage(driver);

        boolean found = false;
        int limit = searchPage.getHospitalCount();

        for (int i = 0; i < limit; i++) {
            searchPage.openHospitalByIndex(i);
            HospitalDetailsPage details =
                    new HospitalDetailsPage(driver, wait);

            if (details.isOpen24x7()) {
                log.info("Open 24x7 hospital found: {}", details.getHospitalName());
                found = true;
                searchPage.closeAndReturnToList();
                break;
            }
            searchPage.closeAndReturnToList();
        }

        Assert.assertTrue(found,
                "No hospital open 24x7 found in top results");
    }
}