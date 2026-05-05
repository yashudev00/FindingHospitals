package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.HomePage;
import org.example.pages.HospitalDetailsPage;
import org.example.pages.HospitalSearchPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC10FindEligibleHospitalsInTopCitiesTest extends BaseClass {

    @Test
    public void findEligibleHospitalsInTopCities() {

        SoftAssert softAssert = new SoftAssert();
        log.info("Starting TC10: City-wise hospital validation");

        HomePage homePage = new HomePage(driver, wait);
        homePage.searchHospitalDirectly();

        HospitalSearchPage initialSearchPage =
                new HospitalSearchPage(driver);

        List<String> cities = initialSearchPage.getTopCities();
        softAssert.assertFalse(
                cities.isEmpty(),
                "Top cities list is empty"
        );

        for (String city : cities) {

            log.info("Processing city: {}", city);

            homePage.searchHospitalInSpecificCity(city);
            HospitalSearchPage searchPage =
                    new HospitalSearchPage(driver);

            boolean foundEligible = false;

            int hospitalCount = searchPage.getHospitalCount();
            int limit = Math.min(hospitalCount, 3);

            for (int i = 0; i < limit; i++) {
                searchPage.openHospitalByIndex(i);

                HospitalDetailsPage details =
                        new HospitalDetailsPage(driver, wait);

                if (details.isOpen24x7()
                        && details.hasParking()
                        && details.getRating() > 3.5) {

                    log.info(
                            "Eligible hospital found | City: {} | Name: {} | Rating: {}",
                            city,
                            details.getHospitalName(),
                            details.getRating()
                    );

                    foundEligible = true;
                    searchPage.closeAndReturnToList();
                    break;
                }
                searchPage.closeAndReturnToList();
            }
            if (!foundEligible) {
                log.warn(
                        "No eligible hospital found for city: {} (continuing test)",
                        city
                );
            }
        }
        log.info("TC10 execution completed for all cities");
        softAssert.assertAll();
    }
}