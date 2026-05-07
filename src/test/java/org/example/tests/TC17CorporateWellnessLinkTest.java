package org.example.tests;

import org.example.base.BaseClass;
import org.example.pages.CorporateWellnessPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TC17CorporateWellnessLinkTest extends BaseClass {

    @Test
    public void CorporateWellnessLinkTest_21() {

        log.info("Starting CorporateWellnessLinkTest_21");

        CorporateWellnessPage corporatePage =
                new CorporateWellnessPage(driver);

        String parentWindow = driver.getWindowHandle();
        log.info("Captured parent window handle: {}", parentWindow);

        log.info("Clicking on Corporate menu");
        corporatePage.clickCorporateMenu();

        log.info("Clicking on Health & Wellness Plans");
        corporatePage.clickHealthAndWellnessPlans();

        Set<String> allWindows = driver.getWindowHandles();
        log.info("Total windows opened: {}", allWindows.size());

        Assert.assertEquals(
                allWindows.size(),
                1,
                "Unexpected new window opened"
        );
        log.info("Verified that no new window was opened");

        String windowTitle = driver.getTitle();
        log.info("Window Name (Title): {}", windowTitle);

        boolean navigationStatus =
                driver.getCurrentUrl().toLowerCase().contains("corporate")
                        || driver.getCurrentUrl().toLowerCase().contains("wellness")
                        || windowTitle.toLowerCase().contains("wellness");

        log.info("Navigation validation result: {}", navigationStatus);

        Assert.assertTrue(
                navigationStatus,
                "Health & Wellness page did not open"
        );

        Assert.assertEquals(
                driver.getWindowHandle(),
                parentWindow,
                "Navigation did not remain in the same window"
        );
        log.info("Verified navigation remained in the parent window");
        log.info("CorporateWellnessLinkTest_21 completed successfully");
    }
}