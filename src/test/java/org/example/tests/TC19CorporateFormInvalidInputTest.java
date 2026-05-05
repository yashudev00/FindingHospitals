package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.CorporateWellnessPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC19CorporateFormInvalidInputTest extends BaseClass {

    private static final Logger log =
            LoggerFactory.getLogger(TC19CorporateFormInvalidInputTest.class);

    @Test
    public void CorporateFormInvalidInputTest_23() {

        log.info("Starting CorporateFormInvalidInputTest_23");

        CorporateWellnessPage corporatePage =
                new CorporateWellnessPage(driver);

        log.info("Clicking on Corporate menu");
        corporatePage.clickCorporateMenu();

        log.info("Clicking on Health & Wellness Plans");
        corporatePage.clickHealthAndWellnessPlans();

        log.info("Entering invalid form details");
        corporatePage.enterInvalidFormDetails();

        boolean isClickable = corporatePage.isScheduleDemoClickable();
        log.info("Is Schedule Demo Clickable: {}", isClickable);

        Assert.assertFalse(
                isClickable,
                "Schedule Demo button should NOT be clickable for invalid input"
        );
        log.info("CorporateFormInvalidInputTest_23 completed successfully");
    }
}