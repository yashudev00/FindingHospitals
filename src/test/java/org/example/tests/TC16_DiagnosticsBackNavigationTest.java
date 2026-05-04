package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.LabTestsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC16_DiagnosticsBackNavigationTest extends BaseClass {

    // Logger instance (same pattern as TC11)
    private static final Logger log =
            LoggerFactory.getLogger(TC16_DiagnosticsBackNavigationTest.class);

    @Test
    public void DiagnosticsBackNavigationTest_20() {

        log.info("Starting DiagnosticsBackNavigationTest_20");

        //Create Page Object
        LabTestsPage labTestsPage = new LabTestsPage(driver);

        //Step 1: Click Lab Tests
        log.info("Clicking on Lab Tests");
        labTestsPage.openLabTests();

        //Step 2: Validate Lab Tests page opened
        log.info("Validating Lab Tests page is opened");
        Assert.assertTrue(
                labTestsPage.isTopCitiesDisplayed(),
                "Lab Tests page did not open"
        );
        log.info("Lab Tests page opened successfully");

        //Step 3: Click Practo logo
        log.info("Clicking on Practo logo to navigate back to Home page");
        labTestsPage.clickPractoLogo();

        //Step 4: Validate navigation to Home page
        String currentUrl = labTestsPage.getCurrentUrl();
        log.info("Current URL after clicking Practo logo: {}", currentUrl);

        Assert.assertTrue(
                currentUrl.equals("https://www.practo.com/")
                        || currentUrl.equals("https://www.practo.com"),
                "Practo logo did not navigate to Home page"
        );

        log.info("DiagnosticsBackNavigationTest_20 completed successfully");
    }
}