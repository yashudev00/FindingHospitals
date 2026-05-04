package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalDetailsPage;
import org.example.pages.HospitalSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TC06_VerifyMultipleEligibleHospitalsTest extends BaseClass {

    @Test
    public void verifyMultipleEligibleHospitals() {

        new HomePage(driver, wait).searchHospitalDirectly();
        HospitalSearchPage searchPage = new HospitalSearchPage(driver);

        int limit = Math.min(searchPage.getHospitalCount(), 10);
        List<String> eligibleHospitals = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            searchPage.openHospitalByIndex(i);
            HospitalDetailsPage details =
                    new HospitalDetailsPage(driver, wait);

            if (details.isOpen24x7()
                    && details.hasParking()
                    && details.getRating() > 3.5) {

                eligibleHospitals.add(
                        details.getHospitalName() +
                                " | Rating: " + details.getRating()
                );
            }
            searchPage.closeAndReturnToList();
        }

        eligibleHospitals.forEach(
                h -> log.info("✅ Eligible Hospital: {}", h)
        );

        Assert.assertTrue(
                eligibleHospitals.size() >= 2,
                "Less than 2 hospitals met all eligibility criteria"
        );
    }
}