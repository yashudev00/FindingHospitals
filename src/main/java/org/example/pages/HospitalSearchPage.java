package org.example.pages;

import org.example.utility.WaitUtils;
import org.example.utility.WindowHandler;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HospitalSearchPage {

    private WebDriver driver;
    private String parentWindow;

    @FindBy(xpath = "//div[@class='c-estb-info']//h2")
    private List<WebElement> hospitals;

    @FindBy(xpath = "(//div[@data-qa-id='sidebar_hospital_block'])[1]")
    private List<WebElement> topCitiesSection;

    @FindBy(xpath = "(//div[@data-qa-id='sidebar_hospital_block'])[1]//a")
    private List<WebElement> topCitiesLinks;

    public HospitalSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getHospitalCount() {
        WaitUtils.waitForElementsVisible(driver, hospitals);
        return hospitals.size();
    }

    public void openHospitalByIndex(int index) {
        WaitUtils.waitForMinimumElements(driver, hospitals, index + 1);
        try {
            hospitals.get(index).click();
        } catch (StaleElementReferenceException e) {
            hospitals.get(index).click(); // retry once
        }
        parentWindow = WindowHandler.switchToChildWindow(driver);
    }

    public void closeAndReturnToList() {
        driver.close();
        WindowHandler.switchToParent(driver, parentWindow);
        WaitUtils.waitForElementsVisible(driver, hospitals);
    }

    public List<String> getTopCities() {
        List<String> cities = new ArrayList<>();
        for (WebElement city : topCitiesLinks) {
            cities.add(
                    city.getText()
                            .replace("Hospitals in", "")
                            .trim()
            );
        }
        return cities;
    }

    public boolean isTopCitiesSectionDisplayed() {
        return !topCitiesSection.isEmpty();
    }
}
