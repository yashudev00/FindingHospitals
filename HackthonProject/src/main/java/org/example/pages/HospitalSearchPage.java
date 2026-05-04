package org.example.pages;

import org.example.utility.WaitUtils;
import org.example.utility.WindowHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HospitalSearchPage {

    WebDriver driver;
    String parentWindow;

    // ✅ Use locator, NOT WebElement
    private final By hospitalsLocator = By.xpath("//h2[@class='line-1']");

    public HospitalSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getHospitalCount() {
        // ✅ wait until hospitals are loaded
        WaitUtils.waitForPresence(driver, hospitalsLocator);
        return driver.findElements(hospitalsLocator).size();
    }
    public void openHospitalByIndex(int index) {

        // ✅ Wait until required number of hospitals are present
        WaitUtils.waitForNumberOfElements(
                driver,
                hospitalsLocator,
                index + 1
        );

        try {
            // ✅ Always fetch element fresh before clicking
            WebElement hospital =
                    driver.findElements(hospitalsLocator).get(index);

            hospital.click();

        } catch (StaleElementReferenceException e) {
            // ✅ Re-fetch and retry once (DOM was refreshed)
            driver.findElements(hospitalsLocator)
                    .get(index)
                    .click();
        }

        parentWindow = WindowHandler.switchToChildWindow(driver);
    }

    // ✅ UPDATED: sync after returning from child window
    public void closeAndReturnToList() {
        driver.close();
        WindowHandler.switchToParent(driver, parentWindow);

        // ✅ VERY IMPORTANT:
        // Wait for hospital list to re-render after DOM refresh
        WaitUtils.waitForPresence(driver, hospitalsLocator);
    }

    // ✅ Capture Top Cities
    public List<String> getTopCities() {

        List<String> cities = new ArrayList<>();

        driver.findElements(
                By.xpath("(//div[@data-qa-id='sidebar_hospital_block'])[1]//a")
        ).forEach(el ->
                cities.add(
                        el.getText()
                                .replace("Hospitals in", "")
                                .trim()
                )
        );

        return cities;
    }

    // ✅ UI validation for Top Cities section
    public boolean isTopCitiesSectionDisplayed() {
        return driver.findElements(
                By.xpath("(//div[@data-qa-id='sidebar_hospital_block'])[1]")
        ).size() > 0;
    }
}