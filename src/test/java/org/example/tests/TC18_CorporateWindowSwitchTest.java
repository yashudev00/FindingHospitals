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

        CorporateWellnessPage corporateWellnessPage =
                new CorporateWellnessPage(driver);

        log.info("Clicking on Corporate menu");
        corporateWellnessPage.clickCorporateMenu();

        log.info("Clicking on Health & Wellness Plans");
        corporateWellnessPage.clickHealthAndWellnessPlans();

        String headerText = corporateWellnessPage.getPageHeaderText();
        log.info("Corporate Wellness Page Header: {}", headerText);

        Assert.assertTrue(
                headerText.toLowerCase().contains("wellness")
                        || headerText.toLowerCase().contains("corporate"),
                "Corporate Wellness page header is not displayed correctly"
        );

        log.info("CorporateWindowSwitchTest_22 completed successfully");
    }
}