package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.CorporateWellnessPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC19_CorporateFormInvalidInputTest extends BaseClass {

    //Logger instance
    private static final Logger log =
            LoggerFactory.getLogger(TC19_CorporateFormInvalidInputTest.class);

    @Test
    public void CorporateFormInvalidInputTest_23() {

        log.info("Starting CorporateFormInvalidInputTest_23");

        //Create Page Object
        CorporateWellnessPage corporatePage =
                new CorporateWellnessPage(driver);

        //Step 1: Open Corporate → Health & Wellness Plans
        log.info("Clicking on Corporate menu");
        corporatePage.clickCorporateMenu();

        log.info("Clicking on Health & Wellness Plans");
        corporatePage.clickHealthAndWellnessPlans();

        //Step 2: Enter INVALID form details
        log.info("Entering invalid form details");
        corporatePage.enterInvalidFormDetails();

        //Step 3: Check Schedule Demo button status
        boolean isClickable = corporatePage.isScheduleDemoClickable();
        log.info("Is Schedule Demo Clickable: {}", isClickable);

        //Step 4: Assertion
        Assert.assertFalse(
                isClickable,
                "Schedule Demo button should NOT be clickable for invalid input"
        );

        log.info("CorporateFormInvalidInputTest_23 completed successfully");
    }
}