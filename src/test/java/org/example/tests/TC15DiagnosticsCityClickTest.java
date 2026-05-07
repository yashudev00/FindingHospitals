package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.LabTestsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC15DiagnosticsCityClickTest extends BaseClass {

    @Test
    public void DiagnosticsCityClickTest_19() {

        log.info("Starting DiagnosticsCityClickTest_19");

        LabTestsPage labTestsPage = new LabTestsPage(driver);

        log.info("Opening Lab Tests page");
        labTestsPage.openLabTests();

        List<String> cityNames = labTestsPage.getAllCityNames();

        Assert.assertTrue(cityNames.size() > 0,
                "Top Cities list is empty");

        log.info("Top Cities Available:");
        for (String city : cityNames) {
            log.info("City Name: {}", city);
        }

        log.info("Verifying first city is clickable");
        Assert.assertTrue(
                labTestsPage.isFirstCityClickable(),
                "First city is not clickable"
        );

        String selectedCity = cityNames.get(0);
        log.info("Clicking first city: {}", selectedCity);
        labTestsPage.clickFirstCity();

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