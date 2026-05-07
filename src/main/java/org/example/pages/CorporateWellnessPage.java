package org.example.pages;

import org.example.utility.ConfigReader;
import org.example.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CorporateWellnessPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[@class='nav-interact']")
    private WebElement corporateMenu;

    @FindBy(xpath = "//a[text()='Health & Wellness Plans']")
    private WebElement healthWellnessPlans;

    @FindBy(xpath = "(//h1[@class='ui header title'])[1]")
    private WebElement pageHeader;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "organizationName")
    private WebElement organizationNameInput;

    @FindBy(id = "contactNumber")
    private WebElement contactNumberInput;

    @FindBy(id = "officialEmailId")
    private WebElement emailInput;

    @FindBy(id = "organizationSize")
    private WebElement organizationSizeDropdown;

    @FindBy(xpath = "//button[contains(text(),'Schedule')]")
    private WebElement scheduleDemoButton;

    public CorporateWellnessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCorporateMenu() {
        corporateMenu.click();
    }

    public void clickHealthAndWellnessPlans() {
        healthWellnessPlans.click();
    }

    public void enterInvalidFormDetails() {
     WaitUtils.waitForElementsVisible(
                driver,
                java.util.Collections.singletonList(nameInput)
        );

        nameInput.sendKeys(ConfigReader.get("Name"));                 // 12345
        organizationNameInput.sendKeys(ConfigReader.get("OrganizationName")); // @@@
        contactNumberInput.sendKeys(ConfigReader.get("contact"));     // abcd123
        emailInput.sendKeys(ConfigReader.get("Email"));

        Select select = new Select(organizationSizeDropdown);
        select.selectByIndex(1);
    }

    public boolean isScheduleDemoClickable() {
        return scheduleDemoButton.isDisplayed()
                && scheduleDemoButton.isEnabled();
    }

    public String getPageHeaderText() {
        return pageHeader.getText().trim();
    }
}