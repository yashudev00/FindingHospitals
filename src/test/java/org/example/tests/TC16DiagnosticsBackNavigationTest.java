package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.LabTestsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC16DiagnosticsBackNavigationTest extends BaseClass {

    // Logger instance (same pattern as TC11)
    private static final Logger log =
            LoggerFactory.getLogger(TC16DiagnosticsBackNavigationTest.class);

    @Test
    public void DiagnosticsBackNavigationTest_20() {

        log.info("Starting DiagnosticsBackNavigationTest_20");
        LabTestsPage labTestsPage = new LabTestsPage(driver);

        log.info("Clicking on Lab Tests");
        labTestsPage.openLabTests();

        log.info("Validating Lab Tests page is opened");
        Assert.assertTrue(
                labTestsPage.isTopCitiesDisplayed(),
                "Lab Tests page did not open"
        );
        log.info("Lab Tests page opened successfully");

        log.info("Clicking on Practo logo to navigate back to Home page");
        labTestsPage.clickPractoLogo();

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