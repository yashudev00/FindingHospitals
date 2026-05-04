package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.LabTestsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC15_DiagnosticsCityClickTest extends BaseClass {

    //Logger instance
    private static final Logger log =
            LoggerFactory.getLogger(TC15_DiagnosticsCityClickTest.class);

    @Test
    public void DiagnosticsCityClickTest_19() {

        log.info("Starting DiagnosticsCityClickTest_19");

        //Create Page Object
        LabTestsPage labTestsPage = new LabTestsPage(driver);

        //Step 1: Open Lab Tests
        log.info("Opening Lab Tests page");
        labTestsPage.openLabTests();

        //Step 2: Get all city names
        List<String> cityNames = labTestsPage.getAllCityNames();

        Assert.assertTrue(cityNames.size() > 0,
                "Top Cities list is empty");

        log.info("Top Cities Available:");
        for (String city : cityNames) {
            log.info("City Name: {}", city);
        }

        //Step 3: Verify first city is clickable
        log.info("Verifying first city is clickable");
        Assert.assertTrue(
                labTestsPage.isFirstCityClickable(),
                "First city is not clickable"
        );

        //Step 4: Click first city
        String selectedCity = cityNames.get(0);
        log.info("Clicking first city: {}", selectedCity);
        labTestsPage.clickFirstCity();

        //Step 5: Validate navigation
        boolean navigationStatus =
                driver.getCurrentUrl().toLowerCase().contains("lab")
                        || driver.getPageSource().contains(selectedCity);

        log.info("Navigation validation result: {}", navigationStatus);

        Assert.assertTrue(
                navigationStatus,
                "Navigation failed after clicking city"
        );

        log.info("DiagnosticsCityClickTest_19 completed successfully");
    }
}