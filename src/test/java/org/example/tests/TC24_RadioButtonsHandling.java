package org.example.tests;
import org.example.base.BaseClass;
import org.example.utility.WaitUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC24_RadioButtonsHandling extends BaseClass {

    @Test
    public void verifyDropdownAndRadioFilters() {

        /* -------- Search Location -------- */
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

        /* -------- All Filters -------- */
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
    }
}
