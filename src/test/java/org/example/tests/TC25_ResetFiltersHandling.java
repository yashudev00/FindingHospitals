package org.example.tests;

import org.example.base.BaseClass;
import org.example.utility.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC25_ResetFiltersHandling extends BaseClass {

    @Test
    public void verifyResetFiltersUsingCorrectAssertions() {

        /* ================= SEARCH LOCATION : BANGALORE ================= */

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//input[@data-qa-id='omni-searchbox-locality']")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//i[@class='icon-ic_cross_solid']")
        ).click();

        driver.findElement(
                By.xpath("//input[@data-qa-id='omni-searchbox-locality']")
        ).sendKeys("Mumbai");

        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//div[@class='c-omni-suggestion-item__content__title'])[1]")
        ).click();

        /* ================= SEARCH DIETITIAN ================= */

        driver.findElement(
                By.xpath("//input[@data-qa-id='omni-searchbox-keyword']")
        ).clear();

        driver.findElement(
                By.xpath("//input[@data-qa-id='omni-searchbox-keyword']")
        ).sendKeys("Dietitian");

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//div[@data-qa-id='omni-suggestion-main' and normalize-space()='Dietitian/nutritionist']")
        ).click();

        /* ================= APPLY FILTERS ================= */

        // Gender → Male
        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//span[@data-qa-id='selected_dropdown_filter'])[1]")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//li[@aria-label='Male Doctor']")
        ).click();

        // Patient Stories → 40+
        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//i[@class='u-transition--transform u-d-inlineblock icon-ic_dropdown u-f-right'])[2]")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//li[@aria-label='40+ Patient Stories']")
        ).click();

        // Experience → 10+ Years
        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//i[@class='u-transition--transform u-d-inlineblock icon-ic_dropdown u-f-right'])[3]")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//li[@aria-label='10+ Years of experience']")
        ).click();

        /* ================= ALL FILTERS (RADIO BUTTONS) ================= */

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//i[@data-qa-id='all_filters_icon']")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//div[@data-qa-id='Fees_radio'])[3]")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//div[@data-qa-id='Availability_radio'])[2]")
        ).click();

        /* ================= RESET FILTERS ================= */

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//button[@class='c-btn--unstyled  u-spacer--right-thin']")
        ).click();

        /* ================= ASSERTIONS ================= */

        // ✅ Gender reset
        String gender =
                driver.findElement(
                        By.xpath("(//span[@data-qa-id='selected_dropdown_filter'])[1]")
                ).getText();

        Assert.assertNotEquals(
                gender,
                "Male Doctor",
                "Gender filter was NOT reset"
        );

        // ✅ Patient Stories reset
        String patientStories =
                driver.findElement(
                        By.xpath("(//span[@data-qa-id='selected_dropdown_filter'])[2]")
                ).getText();

        Assert.assertFalse(
                patientStories.contains("+"),
                "Patient Stories filter was NOT reset"
        );

        // ✅ Experience reset
        String experience =
                driver.findElement(
                        By.xpath("(//span[@data-qa-id='selected_dropdown_filter'])[3]")
                ).getText();

        Assert.assertFalse(
                experience.contains("Years"),
                "Experience filter was NOT reset"
        );
    }
}
