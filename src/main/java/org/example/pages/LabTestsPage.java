package org.example.pages;

import org.example.utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LabTestsPage {

    private WebDriver driver;

    // Lab Tests tab
    @FindBy(xpath = "//div[@class='product-tab__title'][text()='Lab Tests']")
    private WebElement labTestsTab;

    // Top Cities
    @FindBy(xpath = "//div[@class='u-margint--standard o-f-color--primary']")
    private List<WebElement> allCities;

    // Practo logo
    @FindBy(xpath = "(//i[@class='practo_logo_new'])[3]")
    private WebElement practoLogo;

    // Skin category
    @FindBy(xpath = "//div[text()='Skin']")
    private WebElement skinCategory;

    // All Skin related tests (example: Vitamin Profile)
    @FindBy(xpath = "//div[@class='u-bold']")
    private List<WebElement> skinRelatedTests;

    @FindBy(xpath = "(//div[text()='ADD TO CART'][@class='o-f-color--plight'])[1]")
    private List<WebElement> clickAddToCart;

    @FindBy(xpath = "//span[@class='o-font-size--12']")
    private WebElement cartItem;

    public LabTestsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Open Lab Tests
    public void openLabTests() {
        labTestsTab.click();
    }

    // Ensure Lab Tests page is loaded
    public boolean isTopCitiesDisplayed() {
        WaitUtils.waitForPresence(
                driver,
                By.xpath("//div[@class='u-margint--standard o-f-color--primary']")
        );
        return !allCities.isEmpty();
    }

    // Get all city names
    public List<String> getAllCityNames() {
        List<String> cityNames = new ArrayList<>();
        for (WebElement city : allCities) {
            cityNames.add(city.getText().trim());
        }
        return cityNames;
    }

    // CHECK IF FIRST CITY IS CLICKABLE
    public boolean isFirstCityClickable() {
        WebElement firstCity = allCities.get(0);
        return firstCity.isDisplayed() && firstCity.isEnabled();
    }

    // Click first city
    public void clickFirstCity() {
        allCities.get(0).click();
    }

    // Click Practo logo
    public void clickPractoLogo() {
        practoLogo.click();
    }

    // Get URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public void addToCart() {
        int clickedCount = 0;

        // Click each button
        for (WebElement button : clickAddToCart) {
            button.click();
            clickedCount++;
        }

        // Compare numerics only
        int cartCount = getCartItemCount();
        if (cartCount != clickedCount) {
            throw new AssertionError("Cart count mismatch! Expected: "
                    + clickedCount + " but found: " + cartCount);
        }
    }

    public int getCartItemCount() {
        String countText = cartItem.getText().replaceAll("[^0-9]", ""); // keep only digits
        return countText.isEmpty() ? 0 : Integer.parseInt(countText);
    }


}