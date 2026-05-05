package org.example.pages;

import org.example.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LabTestsPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='product-tab__title'][text()='Lab Tests']")
    private WebElement labTestsTab;

    @FindBy(xpath = "//div[@class='u-margint--standard o-f-color--primary']")
    private List<WebElement> allCities;

    @FindBy(xpath = "(//i[@class='practo_logo_new'])[3]")
    private WebElement practoLogo;

    @FindBy(xpath = "//div[text()='Skin']")
    private WebElement skinCategory;

    @FindBy(xpath = "//div[@class='u-bold']")
    private List<WebElement> skinRelatedTests;

    @FindBy(xpath = "(//div[text()='ADD TO CART'][@class='o-f-color--plight'])[1]")
    private List<WebElement> clickAddToCart;

    @FindBy(xpath = "//span[@class='u-padr--less u-font-bold']/following-sibling::span")
    private WebElement cartItem;

    public LabTestsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLabTests() {
        labTestsTab.click();
    }

    public boolean isTopCitiesDisplayed() {
        WaitUtils.waitForElementsVisible(driver, allCities);
        return !allCities.isEmpty();
    }

    public List<String> getAllCityNames() {
        List<String> cityNames = new ArrayList<>();
        for (WebElement city : allCities) {
            cityNames.add(city.getText().trim());
        }
        return cityNames;
    }

    public boolean isFirstCityClickable() {
        WebElement firstCity = allCities.get(0);
        return firstCity.isDisplayed() && firstCity.isEnabled();
    }

    public void clickFirstCity() {
        allCities.get(0).click();
    }

    public void clickPractoLogo() {
        practoLogo.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void addToCart() {
        int clickedCount = 0;
        for (WebElement button : clickAddToCart) {
            button.click();
            clickedCount++;
        }
        int cartCount = getCartItemCount();
        if (cartCount != clickedCount) {
            throw new AssertionError(
                    "Cart count mismatch! Expected: "
                            + clickedCount
                            + " but found: "
                            + cartCount
            );
        }
    }

    public int getCartItemCount() {
        String countText = cartItem.getText().replaceAll("[^0-9]", "");
        return countText.isEmpty() ? 0 : Integer.parseInt(countText);
    }
}