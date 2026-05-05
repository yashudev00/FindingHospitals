package org.example.tests;

import org.example.base.BaseClass;
import org.example.utility.WaitUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC23_DropDownHandling extends BaseClass {

    @Test
    public void verifyDropdownFilters() {

        /* -------- Search Location: Bangalore -------- */
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

        /* -------- Gender → Male -------- */
        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//span[@data-qa-id='selected_dropdown_filter'])[1]")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//li[@aria-label='Male Doctor']")
        ).click();

        /* -------- Patient Stories → 40+ -------- */
        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//i[@class='u-transition--transform u-d-inlineblock icon-ic_dropdown u-f-right'])[2]")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//li[@aria-label='40+ Patient Stories']")
        ).click();

        /* -------- Experience → 10+ Years -------- */
        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//i[@class='u-transition--transform u-d-inlineblock icon-ic_dropdown u-f-right'])[3]")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("//li[@aria-label='10+ Years of experience']")
        ).click();

        /* -------- Sort By -------- */
        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//i[@class='u-transition--transform u-d-inlineblock icon-ic_dropdown u-f-right'])[4]")
        ).click();

        WaitUtils.waitForClickable(
                driver,
                By.xpath("(//li[@class='c-dropdown__list__item'])[11]")
        ).click();
    }
}