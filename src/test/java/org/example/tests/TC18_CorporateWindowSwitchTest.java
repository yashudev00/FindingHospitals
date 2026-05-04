package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.CorporateWellnessPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TC18_CorporateWindowSwitchTest extends BaseClass {

    //Logger instance (same pattern as TC11, TC12, TC13)
    private static final Logger log =
            LoggerFactory.getLogger(TC18_CorporateWindowSwitchTest.class);

    @Test
    public void CorporateWindowSwitchTest_22() {

        log.info("Starting CorporateWindowSwitchTest_22");

        //Create Page Object
        CorporateWellnessPage corporateWellnessPage =
                new CorporateWellnessPage(driver);

        //Step 1: Click on Corporate
        log.info("Clicking on Corporate menu");
        corporateWellnessPage.clickCorporateMenu();

        //Step 2: Click on Health & Wellness Plans
        log.info("Clicking on Health & Wellness Plans");
        corporateWellnessPage.clickHealthAndWellnessPlans();

        //Step 3: Capture page header text
        String headerText = corporateWellnessPage.getPageHeaderText();
        log.info("Corporate Wellness Page Header: {}", headerText);

        //Step 4: Validate page header
        Assert.assertTrue(
                headerText.toLowerCase().contains("wellness")
                        || headerText.toLowerCase().contains("corporate"),
                "Corporate Wellness page header is not displayed correctly"
        );

        log.info("CorporateWindowSwitchTest_22 completed successfully");
    }
}