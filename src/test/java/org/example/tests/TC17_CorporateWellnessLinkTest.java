package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.CorporateWellnessPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TC17_CorporateWellnessLinkTest extends BaseClass {

    //Logger instance (same style as other test cases)
    private static final Logger log =
            LoggerFactory.getLogger(TC17_CorporateWellnessLinkTest.class);

    @Test
    public void CorporateWellnessLinkTest_21() {

        log.info("Starting CorporateWellnessLinkTest_21");

        //Create Page Object
        CorporateWellnessPage corporatePage =
                new CorporateWellnessPage(driver);

        //Step 1: Capture parent window
        String parentWindow = driver.getWindowHandle();
        log.info("Captured parent window handle: {}", parentWindow);

        //Step 2: Click Corporate menu
        log.info("Clicking on Corporate menu");
        corporatePage.clickCorporateMenu();

        //Step 3: Click Health & Wellness Plans
        log.info("Clicking on Health & Wellness Plans");
        corporatePage.clickHealthAndWellnessPlans();

        //Step 4: Fetch window handles
        Set<String> allWindows = driver.getWindowHandles();
        log.info("Total windows opened: {}", allWindows.size());

        //Step 5: Assert same window is used
        Assert.assertEquals(
                allWindows.size(),
                1,
                "Unexpected new window opened"
        );
        log.info("Verified that no new window was opened");

        //Step 6: Capture window title
        String windowTitle = driver.getTitle();
        log.info("Window Name (Title): {}", windowTitle);

        //Step 7: Validate navigation to Wellness page
        boolean navigationStatus =
                driver.getCurrentUrl().toLowerCase().contains("corporate")
                        || driver.getCurrentUrl().toLowerCase().contains("wellness")
                        || windowTitle.toLowerCase().contains("wellness");

        log.info("Navigation validation result: {}", navigationStatus);

        Assert.assertTrue(
                navigationStatus,
                "Health & Wellness page did not open"
        );

        //Step 8: Ensure still on parent window
        Assert.assertEquals(
                driver.getWindowHandle(),
                parentWindow,
                "Navigation did not remain in the same window"
        );
        log.info("Verified navigation remained in the parent window");

        log.info("CorporateWellnessLinkTest_21 completed successfully");
    }
}